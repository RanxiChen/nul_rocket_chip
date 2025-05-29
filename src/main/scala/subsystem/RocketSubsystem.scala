// See LICENSE.SiFive for license details.

package freechips.rocketchip.subsystem

import org.chipsalliance.cde.config.Parameters
import freechips.rocketchip.diplomacy._
import freechips.rocketchip.prci.{ResetCrossingType, NoResetCrossing}
import freechips.rocketchip.tile._
import freechips.rocketchip.devices.debug.{HasPeripheryDebug}
import chisel3._

case class RocketCrossingParams(
  crossingType: ClockCrossingType = SynchronousCrossing(),
  master: TilePortParamsLike = TileMasterPortParams(),
  slave: TileSlavePortParams = TileSlavePortParams(),
  mmioBaseAddressPrefixWhere: TLBusWrapperLocation = CBUS,
  resetCrossingType: ResetCrossingType = NoResetCrossing(),
  forceSeparateClockReset: Boolean = false
) extends TileCrossingParamsLike

case class RocketTileAttachParams(
  tileParams: RocketTileParams,
  crossingParams: RocketCrossingParams
) extends CanAttachTile { type TileType = RocketTile }

trait HasRocketTiles extends HasTiles { this: BaseSubsystem =>
  val rocketTiles = tiles.collect { case r: RocketTile => r }

  def coreMonitorBundles = (rocketTiles map { t =>
    t.module.core.rocketImpl.coreMonitorBundle
  }).toList
  val nul_portNexus = BundleBridgeNexusNode[freechips.rocketchip.nulctrl.nul_port]()
  val tile_nul_portNodes = rocketTiles.map(_.nul_portNode)
  tile_nul_portNodes.foreach(nul_portNexus := _)
}

class RocketSubsystem(implicit p: Parameters) extends BaseSubsystem with HasRocketTiles with HasPeripheryDebug {
  override lazy val module = new RocketSubsystemModuleImp(this)
}

class RocketSubsystemModuleImp[+L <: RocketSubsystem](_outer: L) extends BaseSubsystemModuleImp(_outer)
    with HasTilesModuleImp{
      val nul_portIO = outer.nul_portNexus.in.map(_._1)
      val nul_port = IO(Output(new freechips.rocketchip.nulctrl.nul_port))
      nul_port := nul_portIO(0)
    }
