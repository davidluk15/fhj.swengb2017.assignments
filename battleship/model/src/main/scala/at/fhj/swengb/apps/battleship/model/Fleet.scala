package at.fhj.swengb.apps.battleship.model


import scala.util.Random

object Fleet {

  val Empty = Fleet(Set[Vessel]())


  def apply(battleField: BattleField): Fleet = {
    Default
  }

  def apply(fleetConfig: FleetConfig): Fleet = {
    val vessels: Set[Vessel] =
      (for {((k, v), i) <- fleetConfig.vesselMap.zipWithIndex
            a <- 0 until v} yield {
        val direction = if (Random.nextBoolean()) Horizontal else Vertical
        k match {
          case x if x == classOf[BattleShip] => new BattleShip(s"Black Pearl, Length: 5", BattlePos(0, 0), direction)
          case x if x == classOf[BlackPearl] => new BlackPearl(s"Ship $a, Length: 2", BattlePos(0, 0), direction)
          case x if x == classOf[FlyingDutchman] => new FlyingDutchman(s"Flying Dutchman, Length: 4", BattlePos(0, 0), direction)
          case x if x == classOf[Revenge] => new Revenge(s"HMS Interceptor, Length: 3", BattlePos(0, 0), direction)
          case x if x == classOf[Nemesis] => new Nemesis(s"Nemesis $i $a", BattlePos(0, 0), direction)
          case x if x == classOf[HMSInterceptor] => new HMSInterceptor(s"HMS Interceptor $i $a", BattlePos(0, 0), direction)
          case x if x == classOf[PrideOfLondon] => new PrideOfLondon(s"Pride of London $i $a", BattlePos(0, 0), direction)
          case x if x == classOf[ArkRoyal] => new ArkRoyal(s"Ark Royal $i $a", BattlePos(0, 0), direction)
          case x if x == classOf[HMSProvidence] => new HMSProvidence(s"HMS Providence $i $a", BattlePos(0, 0), direction)
          case x if x == classOf[Predator] => new Predator(s"Predator $i $a", BattlePos(0, 0), direction)


        }
      }).toSet


    Fleet(vessels)
  }

  val Default: Fleet = {
    val BattleCruisers: Set[Vessel] = Set(new BattleShip("Archduke John", BattlePos(0, 0), Vertical))
    val cruisers: Set[Vessel] = Set(new BlackPearl("Cruz", BattlePos(1, 0), Vertical), new BlackPearl("Santa", BattlePos(2, 0), Vertical))
    val destroyers: Set[Vessel] = Set(
      new FlyingDutchman("Graz", BattlePos(5, 5), Horizontal),
      new FlyingDutchman("Wien", BattlePos(0, 6), Horizontal),
      new FlyingDutchman("Linz", BattlePos(0, 7), Horizontal),
    )
    val submarines: Set[Vessel] = Set(
      new Revenge("A", BattlePos(6, 6), Horizontal),
      new Revenge("A", BattlePos(1, 6), Horizontal),
      new Revenge("A", BattlePos(3, 2), Horizontal),
      new Revenge("A", BattlePos(4, 4), Horizontal),
    )

    val fleet: Set[Vessel] = BattleCruisers ++ cruisers ++ destroyers ++ submarines
    Fleet(fleet)
  }

}

// ships may not overlap, each vessel should have a distinct place on the battleground
case class Fleet(vessels: Set[Vessel]) {

  def occupiedPositions: Set[BattlePos] = vessels.flatMap(v => v.occupiedPos)

  def findByPos(pos: BattlePos): Option[Vessel] = vessels.find(v => v.occupiedPos.contains(pos))

  def findByName(name: String): Option[Vessel] = vessels.find(v => v.name == NonEmptyString(name))

}