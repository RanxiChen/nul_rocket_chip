// See LICENSE.SiFive for license details.
// See LICENSE.Berkeley for license details.

package freechips.rocketchip.system

import org.chipsalliance.cde.config.Config
import freechips.rocketchip.subsystem._

class WithJtagDTMSystem extends freechips.rocketchip.subsystem.WithJtagDTM
class WithDebugSBASystem extends freechips.rocketchip.subsystem.WithDebugSBA
class WithDebugAPB extends freechips.rocketchip.subsystem.WithDebugAPB

class BaseConfig extends Config(
  new WithDefaultMemPort ++
  new WithDefaultMMIOPort ++
  new WithDefaultSlavePort ++
  new WithTimebase(BigInt(1000000)) ++ // 1 MHz
  new WithDTS("freechips,rocketchip-unknown", Nil) ++
  new WithNExtTopInterrupts(2) ++
  new BaseSubsystemConfig
)

class DefaultConfig extends Config(new WithNBigCores(1) ++ new WithCoherentBusTopology ++ new BaseConfig)

class DefaultBufferlessConfig extends Config(new WithBufferlessBroadcastHub ++ new DefaultConfig)
class DefaultSmallConfig extends Config(new WithNSmallCores(1) ++ new WithCoherentBusTopology ++ new BaseConfig)
class DefaultRV32Config extends Config(new WithRV32 ++ new DefaultConfig)
class DefaultFP16Config extends Config(new WithFP16 ++ new DefaultConfig)

class BitManipCryptoConfig extends Config(new WithBitManip ++ new WithCryptoNIST ++ new WithCryptoSM ++ new DefaultConfig)
class BitManipCrypto32Config extends Config(new WithBitManip ++ new WithCryptoNIST ++ new WithCryptoSM ++ new DefaultRV32Config)

class DualBankConfig extends Config(new WithNBanks(2) ++ new DefaultConfig)
class DualCoreConfig extends Config(new WithNBigCores(2) ++ new WithCoherentBusTopology ++ new BaseConfig)
class DualChannelConfig extends Config(new WithNMemoryChannels(2) ++ new DefaultConfig)
class EightChannelConfig extends Config(new WithNMemoryChannels(8) ++ new DefaultConfig)

class DualChannelDualBankConfig extends Config(
  new WithNMemoryChannels(2) ++
  new WithNBanks(4) ++ new DefaultConfig
)

class RoccExampleConfig extends Config(new WithRoccExample ++ new DefaultConfig)

class HeterogeneousTileExampleConfig extends Config(
  new WithNBigCores(n = 1) ++
  new WithNMedCores(n = 1) ++
  new WithNSmallCores(n = 1) ++
  new WithCoherentBusTopology ++
  new BaseConfig
)

class Edge128BitConfig extends Config(
  new WithEdgeDataBits(128) ++ new DefaultConfig
)
class Edge32BitConfig extends Config(
  new WithEdgeDataBits(32) ++ new DefaultConfig
)

class SingleChannelBenchmarkConfig extends Config(new DefaultConfig)
class DualChannelBenchmarkConfig extends Config(new WithNMemoryChannels(2) ++ new SingleChannelBenchmarkConfig)
class QuadChannelBenchmarkConfig extends Config(new WithNMemoryChannels(4) ++ new SingleChannelBenchmarkConfig)
class OctoChannelBenchmarkConfig extends Config(new WithNMemoryChannels(8) ++ new SingleChannelBenchmarkConfig)

class TinyConfig extends Config(
  new WithNoMemPort ++
  new WithNMemoryChannels(0) ++
  new WithNBanks(0) ++
  new With1TinyCore ++
  new WithIncoherentBusTopology ++
  new BaseConfig
)

class MemPortOnlyConfig extends Config(
  new WithNoMMIOPort ++
  new WithNoSlavePort ++
  new DefaultConfig
)

class MMIOPortOnlyConfig extends Config(
  new WithNoSlavePort ++
  new WithNoMemPort ++
  new WithNMemoryChannels(0) ++
  new WithNBanks(0) ++
  new WithIncoherentTiles ++
  new WithScratchpadsOnly ++
  new WithIncoherentBusTopology ++
  new DefaultConfig
)

class BaseFPGAConfig extends Config(new BaseConfig ++ new WithCoherentBusTopology)
class DefaultFPGAConfig extends Config(new WithNSmallCores(1) ++ new BaseFPGAConfig)

class CloneTileConfig extends Config(new WithCloneRocketTiles(7) ++ new WithNBigCores(1) ++ new WithCoherentBusTopology ++ new BaseConfig)

class BaseLitexConfig extends Config(
  new WithLitexMemPort() ++
  new WithLitexMMIOPort() ++
  new WithLitexSlavePort ++
  new WithNExtTopInterrupts(8) ++
  new WithCoherentBusTopology ++
  new BaseConfig
)

class WithLitexHextConfig extends Config(
  new WithHypervisor ++
  new WithBitManip ++ new WithBitManipCrypto ++
  new WithCryptoNIST ++ new WithCryptoSM
)

class LitexConfig_small_1_1 extends Config(
  new WithNSmallCores(1) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_small_1_2 extends Config(
  new WithNSmallCores(1) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_small_1_4 extends Config(
  new WithNSmallCores(1) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_small_1_8 extends Config(
  new WithNSmallCores(1) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_small_2_1 extends Config(
  new WithNSmallCores(2) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_small_2_2 extends Config(
  new WithNSmallCores(2) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_small_2_4 extends Config(
  new WithNSmallCores(2) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_small_2_8 extends Config(
  new WithNSmallCores(2) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_small_4_1 extends Config(
  new WithNSmallCores(4) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_small_4_2 extends Config(
  new WithNSmallCores(4) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_small_4_4 extends Config(
  new WithNSmallCores(4) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_small_4_8 extends Config(
  new WithNSmallCores(4) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_small_8_1 extends Config(
  new WithNSmallCores(8) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_small_8_2 extends Config(
  new WithNSmallCores(8) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_small_8_4 extends Config(
  new WithNSmallCores(8) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_small_8_8 extends Config(
  new WithNSmallCores(8) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_medium_1_1 extends Config(
  new WithNMedCores(1) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_medium_1_2 extends Config(
  new WithNMedCores(1) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_medium_1_4 extends Config(
  new WithNMedCores(1) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_medium_1_8 extends Config(
  new WithNMedCores(1) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_medium_2_1 extends Config(
  new WithNMedCores(2) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_medium_2_2 extends Config(
  new WithNMedCores(2) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_medium_2_4 extends Config(
  new WithNMedCores(2) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_medium_2_8 extends Config(
  new WithNMedCores(2) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_medium_4_1 extends Config(
  new WithNMedCores(4) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_medium_4_2 extends Config(
  new WithNMedCores(4) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_medium_4_4 extends Config(
  new WithNMedCores(4) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_medium_4_8 extends Config(
  new WithNMedCores(4) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_medium_8_1 extends Config(
  new WithNMedCores(8) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_medium_8_2 extends Config(
  new WithNMedCores(8) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_medium_8_4 extends Config(
  new WithNMedCores(8) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_medium_8_8 extends Config(
  new WithNMedCores(8) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_linux_1_1 extends Config(
  new WithNBigCores(1) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_linux_1_2 extends Config(
  new WithNBigCores(1) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_linux_1_4 extends Config(
  new WithNBigCores(1) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_linux_1_8 extends Config(
  new WithNBigCores(1) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_linux_2_1 extends Config(
  new WithNBigCores(2) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_linux_2_2 extends Config(
  new WithNBigCores(2) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_linux_2_4 extends Config(
  new WithNBigCores(2) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_linux_2_8 extends Config(
  new WithNBigCores(2) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_linux_4_1 extends Config(
  new WithNBigCores(4) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_linux_4_2 extends Config(
  new WithNBigCores(4) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_linux_4_4 extends Config(
  new WithNBigCores(4) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_linux_4_8 extends Config(
  new WithNBigCores(4) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_linux_8_1 extends Config(
  new WithNBigCores(8) ++
  new WithMemoryDataBits(64) ++
  new BaseLitexConfig
)

class LitexConfig_linux_8_2 extends Config(
  new WithNBigCores(8) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_linux_8_4 extends Config(
  new WithNBigCores(8) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_linux_8_8 extends Config(
  new WithNBigCores(8) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_full_1_1 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(1) ++
  new WithMemoryDataBits(64) ++
  new WithInclusiveCache() ++
  new BaseLitexConfig
)

class LitexConfig_full_1_2 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(1) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_full_1_4 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(1) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_full_1_8 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(1) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_full_2_1 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(2) ++
  new WithMemoryDataBits(64) ++
  new WithInclusiveCache() ++
  new BaseLitexConfig
)

class LitexConfig_full_2_2 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(2) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_full_2_4 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(2) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_full_2_8 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(2) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_full_4_1 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(4) ++
  new WithMemoryDataBits(64) ++
  new WithInclusiveCache() ++
  new BaseLitexConfig
)

class LitexConfig_full_4_2 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(4) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_full_4_4 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(4) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_full_4_8 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(4) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)

class LitexConfig_full_8_1 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(8) ++
  new WithMemoryDataBits(64) ++
  new WithInclusiveCache() ++
  new BaseLitexConfig
)

class LitexConfig_full_8_2 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(8) ++
  new WithMemoryDataBits(128) ++
  new BaseLitexConfig
)

class LitexConfig_full_8_4 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(8) ++
  new WithMemoryDataBits(256) ++
  new BaseLitexConfig
)

class LitexConfig_full_8_8 extends Config(
  new WithLitexHextConfig ++
  new WithNBigCores(8) ++
  new WithMemoryDataBits(512) ++
  new BaseLitexConfig
)
