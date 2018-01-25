package at.fhj.swengb.apps.battleship.model


object FleetConfig {

  /**
    * Standard configuration for a fleet
    */
  val playerA: FleetConfig =
    FleetConfig(Map(classOf[BattleShip] -> 1,
      classOf[BlackPearl] -> 3,
      classOf[FlyingDutchman] -> 1,
      classOf[Revenge] -> 1)
    )

  /*val playerB: FleetConfigB =
    FleetConfig(Map(classOf[HMSInterceptor] -> 1,
      classOf[BlackPearl] -> 1,
      classOf[FlyingDutchman] -> 1,
      classOf[Revenge] -> 1)
    )*/

  val OneShip: FleetConfig = FleetConfig(Map(classOf[BattleShip] -> 1))

  val TwoShips: FleetConfig = FleetConfig(Map(classOf[BattleShip] -> 2))


}

/**
  * A configuration for a fleet. How many ships are there for which type
  *
  * @param vesselMap a map which tells us how many instances should be allowed for which vessel type
  */
case class FleetConfig(vesselMap: Map[Class[_ <: Vessel], Int]) {

  // for a given configuration, returns the total number of expected occupied battle positions
  lazy val numberOfTotalOccupiedPositions: Int = {
    vesselMap.map {
      case (tpe, count) => (tpe match {
        case t if t == classOf[BattleShip] => Victory.Size
        case t if t == classOf[BlackPearl] => BlackPearl.Size
        case t if t == classOf[FlyingDutchman] => FlyingDutchman.Size
        case t if t == classOf[Revenge] => Revenge.Size
        case t if t == classOf[Nemesis] => Nemesis.Size
        case _ => ???
      }) * count
    }.sum
  }

}

case class FleetConfigB(vesselMap: Map[Class[_ <: Vessel], Int]) {

  // for a given configuration, returns the total number of expected occupied battle positions
  lazy val numberOfTotalOccupiedPositions: Int = {
    vesselMap.map {
      case (tpe, count) => (tpe match {
        case t if t == classOf[HMSInterceptor] => HMSInterceptor.Size
        case t if t == classOf[PrideOfLondon] => PrideOfLondon.Size
        case t if t == classOf[ArkRoyal] => ArkRoyal.Size
        case t if t == classOf[HMSProvidence] => HMSProvidence.Size
        case t if t == classOf[Predator] => Predator.Size
        case _ => ???
      }) * count
    }.sum
  }

}