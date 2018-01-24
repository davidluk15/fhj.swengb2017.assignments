package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control._
import javafx.scene.layout.GridPane

class BattleShipFxEditPlayerB extends Initializable{






  @FXML private var txtPlayer2: TextField = _
  @FXML private var gameTitle: TextField = _
  @FXML private var posX: TextField = _
  @FXML private var posY: TextField = _



  @FXML def backToMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }




  @FXML def playerA(): Unit = BattleShipFxApp.display(BattleShipFxApp.load("/at/fhj/swengb/apps/battleship/jfx/gameScreenPlayerA.fxml"),BattleShipFxApp.main)


  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    txtPlayer2.setText("Playername: " ++ BattleShipFxApp.playerTwo)
    gameTitle.setText(BattleShipFxApp.battleName)
  }




}