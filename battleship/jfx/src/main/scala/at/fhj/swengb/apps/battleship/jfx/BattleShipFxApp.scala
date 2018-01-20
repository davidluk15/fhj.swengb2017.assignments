package at.fhj.swengb.apps.battleship.jfx


import java.net.URL
import java.nio.file.{Files, Paths}
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.TextArea
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import scala.util.{Failure, Success, Try}
import at.fhj.swengb.apps.battleship.BattleShipProtobuf
import at.fhj.swengb.apps.battleship.BattleShipProtocol._
import at.fhj.swengb.apps.battleship.model._
import javafx.scene.layout.Pane
import java.io.IOException
import java.util.ResourceBundle
import javafx.scene.{Parent, Scene}

import at.fhj.swengb.apps.battleship.jfx.BattleShipFxApp.display

object BattleShipFxApp {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[BattleShipFxApp], args: _*)
  }


  var main: Stage = _
  var splash: Scene = _
  var welcome: Scene = _
  var choosePlayer: Scene = _
  var edit: Scene = _
  var editPlayerB: Scene = _
  var highscores: Scene = _
  var credits: Scene = _
  var game: Scene = _

  val css = "/at/fhj/swengb/apps/battleship/jfx/project.css"

  def loadMain: Stage = main

  def loadSplash: Scene = splash

  def loadWelcome: Scene = welcome

  def loadNewGame: Scene = choosePlayer

  def loadEdit: Scene = edit

  def loadEditPlayerB: Scene = editPlayerB

  def loadHighscore: Scene = highscores

  def loadCredits: Scene = credits

  def loadGameScreen: Scene = game


  def loadFxml(): Unit = {
    welcome = load("/at/fhj/swengb/apps/battleship/jfx/main.fxml")
    choosePlayer = load("/at/fhj/swengb/apps/battleship/jfx/choosePlayer.fxml")
    edit = load("/at/fhj/swengb/apps/battleship/jfx/edit.fxml")
    editPlayerB = load("/at/fhj/swengb/apps/battleship/jfx/editPlayerB.fxml")
    highscores = load("/at/fhj/swengb/apps/battleship/jfx/highscores.fxml")
    credits = load("/at/fhj/swengb/apps/battleship/jfx/credits.fxml")
    game = load("/at/fhj/swengb/apps/battleship/jfx/gameScreen.fxml")
  }

  private def load(file: String): Scene = {
    val triedRoot = Try(FXMLLoader.load[Parent](getClass.getResource(file)))
    triedRoot match {
      case Success(root) =>
        val scene: Scene = new Scene(root)
        scene.getStylesheets.clear()
        scene.getStylesheets.add(css)
        scene
      case Failure(e) => {
        e.printStackTrace()
        null
      }
    }
  }

  def display(scene: Scene, stage: Stage): Unit = {
    stage.setScene(scene)
    stage.show()
  }


  class BattleShipFxApp extends Application {


    override def init(): Unit = {
      BattleShipFxApp.loadFxml()
    }


    override def start(stage: Stage): Unit = {
      stage.setTitle("BattleshipGame by Jägermeister")
      stage.setResizable(false)
      BattleShipFxApp.main = stage
      BattleShipFxApp.display(BattleShipFxApp.loadWelcome, stage)
    }

  }


}