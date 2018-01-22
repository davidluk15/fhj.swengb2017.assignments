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

class BattleShipFxEditPlayerB extends Initializable{


  @FXML private var txtPlayer2: TextField = _
  @FXML private var gameTitle: TextField = _
  @FXML private var posX: TextField = _
  @FXML private var posY: TextField = _



  @FXML def backToMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }



  @FXML private var battleGroundGridPane: GridPane = _



  override def initialize(url: URL, rb: ResourceBundle): Unit = {

    txtPlayer2.setText(BattleShipFxApp.playerTwo )
    gameTitle.setText(BattleShipFxApp.battleName )
  }




}