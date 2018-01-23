package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.nio.file.{Files, Paths}
import java.text.SimpleDateFormat
import java.util.{Calendar, ResourceBundle}
import javafx.fxml.{FXML, Initializable}
import javafx.scene.Scene
import javafx.scene.control.{Label, TextField}

import at.fhj.swengb.apps.battleship.BattleShipProtocol.convert
//import at.fhj.swengb.apps.battleship.jfx.BattleShipFxApp.battleName
import at.fhj.swengb.apps.battleship.model._

import scala.util.Random

class BattleShipFxChoosePlayer extends Initializable {





  @FXML private var gameTitle: TextField = _
  @FXML private var txtPlayer1: TextField = _
  @FXML private var txtPlayer2: TextField = _



  @FXML def backToMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }

  @FXML def toEditPlayerB(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadEditPlayerB,BattleShipFxApp.loadMain)
  }

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    gameTitle.setText(BattleShipFxApp.getRandomGameName())
  }





  def backToHome(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }

  @FXML def nextEditPlayerA(): Unit = {
    BattleShipFxApp.getPlayerOne(txtPlayer1.getText())
    BattleShipFxApp.getPlayerTwo(txtPlayer2.getText())
    BattleShipFxApp.getGameTitle(gameTitle.getText())
    BattleShipFxApp.display(BattleShipFxApp.load("/at/fhj/swengb/apps/battleship/jfx/editPlayerA.fxml"), BattleShipFxApp.main)

  }



}