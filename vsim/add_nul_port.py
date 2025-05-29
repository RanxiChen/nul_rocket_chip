
import sys
from typing import List, Dict

if len(sys.argv) < 3:
    print("Usage: %s input.v output.v" % sys.argv[0])

ifile_path = sys.argv[1]
ofile_path = sys.argv[2]

raw_lines = []
lines = []
with open(ifile_path, "r") as ifile:
    raw_lines = ifile.readlines()
    for l in raw_lines.copy():
        l = l.replace("(", " ").replace(")", " ")
        words = [x for x in l.split() if x != ""]
        lines.append(words)



target_module_name = "Rocket"
top_module_name = "ExampleRocketSystem"

target_input_line = "io_nulrxd"
target_output_line = "io_nultxd"

fine_name = target_module_name
down_to_top_module_stack = []

infos = []

running = True
while running:
    cur = ""
    cur_line = 0
    for i in range(len(lines)):
        line : List[str] = lines[i]
        if len(line) == 0:
            continue
        if line[0] == "module":
            cur = line[1]
            cur_line = i
        if line[0] == target_module_name:
            print("Fine instance of Module %s: %s @Line %d, in Module %s @Line %d" % (target_module_name, line[1], i, cur, cur_line))
            infos.append((target_module_name, line[1], i, cur, cur_line))
            if cur == top_module_name:
                running = False
            target_module_name = cur
            break

addlines = {}

for (tgt_mod, tgt_inst, tgt_inst_line, mod, mod_line) in infos:
    addlines[mod_line] = ["  input %s," % target_input_line, "  output %s," % target_output_line]
    addlines[tgt_inst_line] = ["    .%s(%s)," % (target_input_line, target_input_line), "    .%s(%s)," % (target_output_line, target_output_line)]

for l in addlines.keys():
    for s in addlines[l]:
        print("Add after line %d: %s" % (l, s))

outlines = []
for i in range(len(raw_lines)):
    outlines.append(raw_lines[i])
    if i in addlines.keys():
        for s in addlines[i]:
            outlines.append(s + "\n")

with open(ofile_path, "w") as ofile:
    for l in outlines:
        ofile.write(l);
