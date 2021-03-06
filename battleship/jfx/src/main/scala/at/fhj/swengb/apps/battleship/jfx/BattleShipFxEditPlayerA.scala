package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.beans.property.SimpleStringProperty
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control._
import javafx.scene.layout.GridPane
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.model.{BattleShip, _}

class BattleShipFxEditPlayerA extends Initializable {

  private var fleetConf: FleetConfig = FleetConfig.playerA


  @FXML private var fleetList: ListView[shipsListEntry] = _
  @FXML private var directionBX: ComboBox[Direction] = _
  @FXML private var grid: GridPane = _
  @FXML private var gameTitle:TextField =_
  @FXML private var player:TextField =_
  private var gameField: BattleField = BattleField.placeRandomly(BattleField(10, 10, Fleet.Empty))


  @FXML def toEditPlayerB(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.load("/at/fhj/swengb/apps/battleship/jfx/editPlayerB.fxml"), BattleShipFxApp.main)
  }


  @FXML def backToMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }

  @FXML def select(): Unit = {
    if (fleetList.getSelectionModel.getSelectedItem != null) {

      }
    }


  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    refreshGameField(gameField)
    refreshFleetList(gameField.fleet.vessels)
    dirComb()
    gameTitle.setText(BattleShipFxApp.gameName)
    player.setText(BattleShipFxApp.playerOne ++ " place your ships!")

  }

  private def getCellHeight(y: Int): Double = grid.getRowConstraints.get(y).getPrefHeight

 private def getCellWidth(x: Int): Double = grid.getColumnConstraints.get(x).getPrefWidth

  private def dirComb(): Unit = {
    val Direction: ObservableList[Direction] = FXCollections.observableArrayList()
    Direction.add(Horizontal)
    Direction.add(Vertical)
    directionBX.setItems(Direction)
  }


  private def updateAfterClick(pos: BattlePos): Unit = {

    var setVessel: Vessel = fleetList.getSelectionModel.getSelectedItem.getVessel
    val setDirection: Direction = directionBX.getSelectionModel.getSelectedItem

    val shipName: String = setVessel.name.value
    if (setVessel != null && setDirection != null) {
      val newVessel: Vessel = setVessel.size match {
        case 2 => new BattleShip(shipName, pos, setDirection)
        case 3 => new BlackPearl(shipName, pos, setDirection)
        case 4 => new FlyingDutchman(shipName, pos, setDirection)
        case 5 => new Revenge(shipName, pos, setDirection)
      }

      val invalid: Set[BattlePos] = newVessel.occupiedPos.diff(gameField.availablePos)
      if (invalid.nonEmpty) {
        println("Place on valid position")
      } else {
        val newShips: Seq[Vessel] = newVessel +: gameField.fleet.vessels.toSeq
        gameField = BattleField(10, 10, new Fleet(newShips.toSet))
      }
    }

    refreshGameField(gameField)
    refreshFleetList(gameField.fleet.vessels)

  }


  private def refreshGameField(field: BattleField): Unit = {
    grid.getChildren.clear()

    val cells: Seq[Edit] = for {
      x <- 0 until field.width
      y <- 0 until field.height
      pos = BattlePos(x, y)
    } yield {
      Edit(BattlePos(x, y),
        getCellWidth(x),
        getCellHeight(y),
        field.fleet.findByPos(pos),
        updateAfterClick)
    }
    for (c <- cells) {
      grid.add(c, c.pos.x, c.pos.y)
      c.init()
    }


  }


  private def refreshFleetList(alreadySet: Set[Vessel]): Unit = {
    var standardFleet: Set[Vessel] = Fleet(fleetConf).vessels

    val alreadySetShips: Set[NonEmptyString] = alreadySet.map(e => e.name)
    standardFleet = standardFleet.filter(e => !alreadySetShips.contains(e.name))

    val listData: ObservableList[shipsListEntry] = FXCollections.observableArrayList()
    standardFleet.toList.foreach(e => listData.add(shipsListEntry(e)))
    fleetList.setItems(listData)
  }


  private class shipsListEntry {
    val name: SimpleStringProperty = new SimpleStringProperty()
    private var ship: Vessel = _

    def setName(name: String): Unit = this.name.set(name)

    def getVessel: Vessel = ship

    def setVessel(vessel: Vessel): Unit = {
      ship = vessel
    }

    override def toString: String = name.get
  }

  private object shipsListEntry {
    def apply(vessel: Vessel): shipsListEntry = {
      val entry = new shipsListEntry
      entry.setName(vessel.name.value)
      entry.setVessel(vessel)

      entry
    }
  }

}



