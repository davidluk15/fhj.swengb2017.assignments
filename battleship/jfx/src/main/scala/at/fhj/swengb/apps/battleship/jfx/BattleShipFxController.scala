package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.nio.file._
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{TextArea}
import javafx.scene.layout.GridPane

import at.fhj.swengb.apps.battleship.BattleShipProtobuf
import at.fhj.swengb.apps.battleship.BattleShipProtocol._
import at.fhj.swengb.apps.battleship.model.{BattleField, BattleShipGame, Fleet, FleetConfig}


class BattleShipFxController extends Initializable {


  @FXML private var GridPane: GridPane = _
  var Game: BattleShipGame = _

  /**
    * A text area box to place the history of the game
    */
  @FXML private var log: TextArea = _


  def newGame(): Unit = initGame()

  def saveGame(): Unit = {
    val savedGame: BattleShipProtobuf.BattleShipGame = convert(Game)
    val path = Paths.get("target/BattleShipProtobuf.bin")
    val outputstream = Files.newOutputStream(path)

    savedGame.writeTo(outputstream)

    println("Saved Game")
  }



  def loadGame(): Unit = {
    val path = Paths.get("target/BattleShipProtobuf.bin")
    val inputstream = Files.newInputStream(path)

    val protoGame: BattleShipProtobuf.BattleShipGame = BattleShipProtobuf.BattleShipGame.parseFrom(inputstream)
    val loadGame = BattleShipGame(convert(protoGame).battleField, getCellWidth, getCellHeight, appendLog)
    loadGame.clickedCells = convert(protoGame).clickedCells
    init(loadGame)
    println("Successfully loaded game")
    Game.refresh(Game.clickedCells.length)
  }

  override def initialize(url: URL, rb: ResourceBundle): Unit = initGame()

  private def getCellHeight(y: Int): Double = GridPane.getRowConstraints.get(y).getPrefHeight

  private def getCellWidth(x: Int): Double = GridPane.getColumnConstraints.get(x).getPrefWidth

  def appendLog(message: String): Unit = log.appendText(message + "\n")

  /**
    * Create a new game.
    *
    * This means
    *
    * - resetting all cells to 'empty' state
    * - placing your ships at random on the battleground
    *
    */
  def init(game : BattleShipGame) : Unit = {
    Game = game
    GridPane.getChildren.clear()
    for (c <- game.getCells) {
      GridPane.add(c, c.pos.x, c.pos.y)
    }
    game.getCells().foreach(c => c.init)
  }




  private def initGame(): Unit = {
    val game: BattleShipGame = createGame()
    init(game)
    appendLog("New game started.")
  }

  private def createGame(): BattleShipGame = {
    val field = BattleField(10, 10, Fleet(FleetConfig.Standard))

    val battleField: BattleField = BattleField.placeRandomly(field)

    BattleShipGame(battleField, getCellWidth, getCellHeight, appendLog)
  }

}