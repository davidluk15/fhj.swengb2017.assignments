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

class BattleShipFxEditPlayerB extends Initializable {

  private var fleetConf: FleetConfig = FleetConfig.playerA
  var initPlayerNr: Int = 1
  var closedRegularly: Boolean = false
  @FXML private var fleetList: ListView[VesselListViewEntry] = _
  @FXML private var directionBX: ComboBox[Direction] = _
  @FXML private var grid: GridPane = _
  @FXML private var gameTitle:TextField =_
  @FXML private var player:TextField =_
  private var gameField: BattleField = BattleField.placeRandomly(BattleField(10, 10, Fleet.Empty))


  @FXML def toGame(): Unit = BattleShipFxApp.display(BattleShipFxApp.load("/at/fhj/swengb/apps/battleship/jfx/gameScreenPlayerA.fxml"),BattleShipFxApp.main)



  @FXML def backToMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }

  @FXML def onShipSelected(): Unit = {
    if (fleetList.getSelectionModel.getSelectedItem != null) {

      val ship: Vessel = fleetList.getSelectionModel.getSelectedItem.getVessel
      val direction: Direction = directionBX.getSelectionModel.getSelectedItem
      if (ship != null && direction != null) {
        disableAllButons(false)
      }
    }
  }

  private def disableAllButons(disable: Boolean): Unit = {

    grid.getChildren.forEach(e => {
      val cell: Edit = e.asInstanceOf[Edit]
      cell.someVessel match {
        case Some(v) =>
          cell.setDisable(true)
        case None => cell.setDisable(disable)
      }
    })
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {


    fleetList.getSelectionModel.setSelectionMode(SelectionMode.SINGLE)
    refreshGameField(gameField)
    refreshFleetList(gameField.fleet.vessels)
    initDirectionComboBox()
    gameTitle.setText(BattleShipFxApp.gameName)
    player.setText(BattleShipFxApp.playerTwo ++ " place your ships!")

  }

  private def getCellHeight(y: Int): Double = grid.getRowConstraints.get(y).getPrefHeight

  private def getCellWidth(x: Int): Double = grid.getColumnConstraints.get(x).getPrefWidth

  private def initDirectionComboBox(): Unit = {
    val Direction: ObservableList[Direction] = FXCollections.observableArrayList()
    Direction.add(Horizontal)
    Direction.add(Vertical)

    directionBX.setItems(Direction)
    directionBX.getSelectionModel.selectFirst()
  }


  private def updateEditorAfterClick(pos: BattlePos): Unit = {

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
    disableAllButons(true)
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
        updateEditorAfterClick)
    }
    for (c <- cells) {
      grid.add(c, c.pos.x, c.pos.y)
      c.init()
    }

    disableAllButons(true)
  }


  private def refreshFleetList(alreadySet: Set[Vessel]): Unit = {
    var aFullStandardFleet: Set[Vessel] = Fleet(fleetConf).vessels

    val alreadySetShips: Set[NonEmptyString] = alreadySet.map(e => e.name)
    aFullStandardFleet = aFullStandardFleet.filter(e => !alreadySetShips.contains(e.name))


    val listData: ObservableList[VesselListViewEntry] = FXCollections.observableArrayList()
    aFullStandardFleet.toList.foreach(e => listData.add(VesselListViewEntry(e)))
    fleetList.setItems(listData)
  }


  private class VesselListViewEntry {
    val name: SimpleStringProperty = new SimpleStringProperty()
    private var ship: Vessel = _

    def setName(name: String): Unit = this.name.set(name)

    def getVessel: Vessel = ship

    def setVessel(vessel: Vessel): Unit = {
      ship = vessel
    }

    override def toString: String = name.get
  }

  private object VesselListViewEntry {
    def apply(vessel: Vessel): VesselListViewEntry = {
      val entry = new VesselListViewEntry
      entry.setName(vessel.name.value)
      entry.setVessel(vessel)

      entry
    }
  }

}



