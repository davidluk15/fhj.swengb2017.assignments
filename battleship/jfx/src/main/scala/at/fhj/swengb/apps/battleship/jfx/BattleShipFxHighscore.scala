package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.Scene

class BattleShipFxHighscore extends Initializable {



  /**
    * Change Scene to Game scene
    */
  @FXML def backToMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {

  }
}

