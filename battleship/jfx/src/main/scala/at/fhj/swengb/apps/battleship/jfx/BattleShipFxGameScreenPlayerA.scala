package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control._
import javafx.scene.layout.GridPane

class BattleShipFxGameScreenPlayerA extends Initializable{






  @FXML private var player1txt: TextField = _
  @FXML private var gameTitle: TextField = _



  @FXML def backToMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }

  @FXML def playerB(): Unit = BattleShipFxApp.display(BattleShipFxApp.load("/at/fhj/swengb/apps/battleship/jfx/gameScreenPlayerB.fxml"),BattleShipFxApp.main)





  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    player1txt.setText("Playername: " ++ BattleShipFxApp.playerOne)
    gameTitle.setText(BattleShipFxApp.battleName)
  }




}