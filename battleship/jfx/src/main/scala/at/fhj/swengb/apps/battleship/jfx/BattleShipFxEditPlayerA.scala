package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.Scene
import javafx.scene.control.Alert.AlertType
import javafx.scene.control._
import javafx.scene.layout.GridPane
import javafx.scene.text.{Font, Text}
import at.fhj.swengb.apps.battleship.model._
import at.fhj.swengb.apps.battleship.jfx.BattleShipFxChoosePlayer

import scala.util.Random

class BattleShipFxEditPlayerA extends Initializable{






  @FXML private var txtPlayer1: TextField = _
  @FXML private var gameTitle: TextField = _
  @FXML private var posX: TextField = _
  @FXML private var posY: TextField = _



  @FXML def backToMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }

  @FXML def toEditPlayerB(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.load("/at/fhj/swengb/apps/battleship/jfx/editPlayerB"), BattleShipFxApp.main)
  }

  @FXML private var battleGroundGridPane: GridPane = _



  override def initialize(url: URL, rb: ResourceBundle): Unit = {

    txtPlayer1.setText(BattleShipFxApp.playerOne )
    gameTitle.setText(BattleShipFxApp.battleName )
  }




}