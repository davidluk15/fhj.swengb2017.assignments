package at.fhj.swengb.apps.battleship.jfx
import java.net.URL
import java.nio.file.{Files, Paths}
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{TextArea, TextField}
import javafx.scene.layout.GridPane
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.BattleShipProtobuf
import at.fhj.swengb.apps.battleship.BattleShipProtocol._
import at.fhj.swengb.apps.battleship.model._
import javafx.scene.layout.Pane
import java.io.IOException
import java.util.ResourceBundle
import javafx.scene.{Parent, Scene}

import at.fhj.swengb.apps.battleship.jfx.BattleShipFxApp.display
class Start {
  @FXML def newGame(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadNewGame,BattleShipFxApp.loadMain)
  }

  @FXML def highscore(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadHighscore,BattleShipFxApp.loadMain)
  }

  @FXML def credits(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadCredits,BattleShipFxApp.loadMain)
  }

  @FXML def returnToMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadWelcome,BattleShipFxApp.loadMain)
  }

  @FXML def nextToEdit(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadEdit,BattleShipFxApp.loadMain)
  }

  @FXML def loadEditPlayerB(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadEditPlayerB,BattleShipFxApp.loadMain)
  }

  @FXML def loadGame(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadGameScreen,BattleShipFxApp.loadMain)
  }



  /*

    def playerNames(): Unit = {
      val player1 = txtPlayer1.getText
      val player2 = txtPlayer2.getText
      //player1Name.setText(player1)
      PlayerNameToSet.setText(player1)
      println("Player1: " + player1 + "Player2: " + player2)

    }

  */
}