package at.fhj.swengb.apps.battleship.jfx

import javafx.application.{Application, Preloader}
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.{Failure, Success, Try}
import java.io.IOException
import java.nio.file.{Files, Paths}
import java.util.Calendar
import javafx.scene.control.ProgressBar
import javafx.scene.image.ImageView
import javafx.scene.layout.{BorderPane, Pane}

import at.fhj.swengb.apps.battleship.BattleShipProtobuf
import at.fhj.swengb.apps.battleship.BattleShipProtocol.convert
import at.fhj.swengb.apps.battleship.model.{GameInfo}
import com.sun.javafx.application.LauncherImpl
import at.fhj.swengb.apps.battleship.BattleShipProtocol._





/**object BattleShipFxApp {
  def main(args: Array[String]): Unit = {

    Application.launch(classOf[BattleShipFxApp], args: _*)
  }
}


class BattleShipFxApp extends Application {

  //val triedRoot = Try(FXMLLoader.load[Parent](getClass.getResource("/at/fhj/swengb/apps/battleship/jfx/battleshipfx.fxml")))
 // val welcomeScreen = Try(FXMLLoader.load[Parent](getClass.getResource("/at/fhj/swengb/apps/battleship/jfx/welcome_screen.fxml")))

  //val css = "/at/fhj/swengb/apps/battleship/jfx/battleship.css"

  override def start(stage: Stage): Unit = {
  * welcomeScreen match {
  * case Success(root) =>
  *stage.setScene(new Scene(root))
  *stage.show()
  *stage.getScene.getStylesheets.clear()
  *stage.getScene.getStylesheets.add(css)
  *stage.setTitle("BattleShip")
  * case Failure(e) => e.printStackTrace()
  * }
  * }
  * */








object BattleShipFxApp {

  def main(args: Array[String]): Unit = {

    Application.launch(classOf[BattleShipFxApp], args: _*)

  }

  var filename: String = _
  var gameInfo: GameInfo = _

  var main: Stage = _
  var splashScreen: Scene = _
  var mainMenu: Scene = _
  var choosePlayer: Scene = _
  var editPlayerA: Scene = _
  var editPlayerB: Scene = _
  var highscore: Scene = _
  var credits: Scene = _
  var gameScreen: Scene = _


  def loadMain: Stage = main
  def loadSplashScreen: Scene = splashScreen
  def loadMainMenu: Scene = mainMenu
  def loadChoosePlayer: Scene = choosePlayer
  def loadEditPlayerA: Scene = editPlayerA
  def loadEditPlayerB: Scene = editPlayerB
  def loadHighscore: Scene = highscore
  def loadCredits: Scene = credits
  def loadGameScreen: Scene = gameScreen

  val css = "/at/fhj/swengb/apps/battleship/jfx/project.css"



  def loadFxml(): Unit = {
    //splash = load("/at/fhj/swengb/apps/battleship/jfx/splash.fxml")
    mainMenu = load("/at/fhj/swengb/apps/battleship/jfx/mainMenu.fxml")
    choosePlayer = load("/at/fhj/swengb/apps/battleship/jfx/choosePlayer.fxml")
    highscore = load("/at/fhj/swengb/apps/battleship/jfx/highscores.fxml")
    credits = load("/at/fhj/swengb/apps/battleship/jfx/credits.fxml")

  }

  def loadFxmlEditPlayerA(): Unit = {
    editPlayerA = load("/at/fhj/swengb/apps/battleship/jfx/editPlayerA.fxml")
  }

  def loadFxmlEditPlayerB(): Unit = {
    editPlayerA = load("/at/fhj/swengb/apps/battleship/jfx/editPlayerB.fxml")
  }

  def loadFxmlGame(): Unit = {
    gameScreen = load("/at/fhj/swengb/apps/battleship/jfx/gameScreen.fxml")
  }



   def load(file: String): Scene = {
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

  //def setGameRound(game: GameRound): Unit = {
    //this.gameRound = game
  //}

  def setFilename(fname: String): Unit = {
    this.filename = fname
  }

  /**
    *
    * @return the gameRound which is needed
    */

  def setGameInfo(game: GameInfo): Unit = {
    this.gameInfo = game
  }


  def getGameInfo(): GameInfo = {
    return gameInfo
  }

  /**
    *
    * @return
    */
  def getFilename(): String = {
    return filename

  }

  def Player1(name: String) = {

  }

  //def saveGameState(fname: String): Unit = {
    //filename = fname
    //convert(gameRound).writeTo(Files.newOutputStream(Paths.get(filename)))
  //}
    def saveGameState(fname: String): Unit = {
      filename = fname
      convert(gameInfo).writeTo(Files.newOutputStream(Paths.get(filename)))
    }





  class BattleShipFxApp extends Application {


    override def init(): Unit = {
      BattleShipFxApp.loadFxml()
    }


    override def start(stage: Stage): Unit = {
      /*
      stage.setResizable(false)

      BattleShipFxApp.display(BattleShipFxApp.loadWelcome,stage)*/
      BattleShipFxApp.main = stage
      stage.setTitle("BattleshipGame by Jägermeister")
      BattleShipFxApp.display(BattleShipFxApp.load("/at/fhj/swengb/apps/battleship/jfx/splashscreen.fxml"),stage)

    }


  }

}