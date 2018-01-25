package at.fhj.swengb.apps.battleship.model


object Victory {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 3
}

object BlackPearl {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 5
}

object FlyingDutchman{
  // defines number of occupied battle positions for corresponding vessel
  val Size = 4
}

object Revenge {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 2
}

object Nemesis{
  // defines number of occupied battle positions for corresponding vessel
  val Size = 3
}

//PLAYER B SHIPS

object HMSInterceptor {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 5
}

object PrideOfLondon {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 4
}

object ArkRoyal {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 3
}

object HMSProvidence {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 2
}

object Predator {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 1
}


/**
  * A battleship has a name and a set of positions.
  *
  * Those positions have to be connected. Also they have to be in a straight line, that means
  * that either all x coordinates are equal or all y coordinates are equal.
  *
  * Often it is far easier to use the convenience constructor defined in the companion object to construct
  * a battleship.
  *
  * @param shipName the name of the ship (must be set and not empty)
  */
class BattleShip(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, Victory.Size)

class BlackPearl(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, BlackPearl.Size)

class FlyingDutchman(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, FlyingDutchman.Size)

class Revenge(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, Revenge.Size)

class Nemesis(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, Nemesis.Size)



//SHIPS PLAYER B
class HMSInterceptor(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, HMSInterceptor.Size)

class PrideOfLondon(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, PrideOfLondon.Size)

class ArkRoyal(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, ArkRoyal.Size)

class HMSProvidence(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, HMSProvidence.Size)

class Predator(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, Predator.Size)


