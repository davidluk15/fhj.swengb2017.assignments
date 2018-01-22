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


  val list1: List[String] = List("Fight", "War", "Assault","Bloodshed","Encounter","Skirmish","Battle")
  val list2: List[String] = List("of", "at", "in")
  val list3: List[String] = List("Graz", "Venice", "Azeroth","Normandy")

  /*var playerOne: String=_
  var playerTwo: String=_
  var gameName: String = _*/
  var game: GameInfo = _
  private var filename: String = _

  @FXML private var gameTitle: TextField = _
  @FXML private var txtPlayer1: TextField = _
  @FXML private var txtPlayer2: TextField = _



  @FXML def backToMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }

  @FXML def toEditPlayerB(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadEditPlayerB,BattleShipFxApp.loadMain)
  }

  override def initialize(url: URL, rb: ResourceBundle): Unit = initGame

  private def initGame(): Unit = {
    getRandomGameName()
  }



  def backToHome(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }

 /*def getPlayerOne(name: String) = {
    if (name != "") {
      playerOne = name
    } else {
      playerOne = "Player 1"
    }
  }*/

 /* def getPlayerTwo(name: String) = {
    if (name != "") {
      playerTwo = name
    } else {
      playerTwo = "Player 2"
    }

  }

  def getFinalBattleName(name: String) = {
      gameName = getRandomGameName()
  }
*/
  def getRandomGameName(): String = {
    var name = ""
    name = list1(Random.nextInt(list1.size)) + " " + list2(Random.nextInt(list2.size)) + " " + list3(Random.nextInt(list3.size))
    gameTitle.setText(name)
    return name
  }


  @FXML def nextEditPlayerA(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.load("/at/fhj/swengb/apps/battleship/jfx/editPlayerA.fxml"),BattleShipFxApp.main)
    val player1 =txtPlayer1.getText()
    val player2 =txtPlayer2.getText()
    val battleName =gameTitle.getText()
    System.out.println(player1)
    System.out.println(player2)
    System.out.println(battleName)
    game = GameInfo(player1,player2,battleName)
    BattleShipFxApp.setGameInfo(game)
    BattleShipFxApp.saveGameState("battleship/" + filename)


  }



}