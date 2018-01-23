package at.fhj.swengb.apps.battleship.jfx

import javafx.application.{Application, Preloader}
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.{Failure, Random, Success, Try}
import java.io.IOException
import java.nio.file.{Files, Paths}
import java.util.Calendar
import javafx.scene.control.ProgressBar
import javafx.scene.image.ImageView
import javafx.scene.layout.{BorderPane, Pane}

import at.fhj.swengb.apps.battleship.BattleShipProtobuf
import at.fhj.swengb.apps.battleship.BattleShipProtocol.convert
import at.fhj.swengb.apps.battleship.model.GameInfo
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


  val list1: List[String] = List("Fight", "War", "Assault","Bloodshed","Encounter","Skirmish","Battle")
  val list2: List[String] = List("of", "at", "in")
  val list3: List[String] = List("Graz", "Venice", "Azeroth","Normandy")

  var filename: String = _

  var playerOne: String = _
  var playerTwo: String = _
  var battleName: String = _

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
    mainMenu = load("/at/fhj/swengb/apps/battleship/jfx/mainMenu.fxml")
    choosePlayer = load("/at/fhj/swengb/apps/battleship/jfx/choosePlayer.fxml")
    highscore = load("/at/fhj/swengb/apps/battleship/jfx/highscores.fxml")
    credits = load("/at/fhj/swengb/apps/battleship/jfx/credits.fxml")


  }

  def loadFxmlEditPlayerA(): Unit = {
    editPlayerA = load("/at/fhj/swengb/apps/battleship/jfx/editPlayerA.fxml")
  }

  def loadFxmlEditPlayerB(): Unit = {
    editPlayerB = load("/at/fhj/swengb/apps/battleship/jfx/editPlayerB.fxml")
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





  /**
    *
    * @return
    */



  def getRandomGameName(): String = {
    var name = ""
    name = list1(Random.nextInt(list1.size)) + " " + list2(Random.nextInt(list2.size)) + " " + list3(Random.nextInt(list3.size))
    return name
  }
  def getPlayerOne(name: String) = {
    if(name != "") {
      playerOne = name
    } else {
      playerOne = "Player 1"
    }
  }

  def getPlayerTwo(name: String) = {
    if(name != "") {
      playerTwo = name
    } else {
      playerTwo = "Player 2"
    }
  }

  def getGameTitle(name: String) = {
    if(name != "") {
      battleName = name
    } else {
      battleName = getRandomGameName()
    }
  }


    def saveGameState(fname: String): Unit = {
      filename = fname
      convert(gameInfo).writeTo(Files.newOutputStream(Paths.get(filename)))
    }





  class BattleShipFxApp extends Application {


    override def init(): Unit = {
      BattleShipFxApp.loadFxml()
    }


    override def start(stage: Stage): Unit = {
      BattleShipFxApp.main = stage
      stage.setTitle("BattleshipGame by Jägermeister")
      BattleShipFxApp.display(BattleShipFxApp.load("/at/fhj/swengb/apps/battleship/jfx/splashscreen.fxml"),stage)

    }


  }

}