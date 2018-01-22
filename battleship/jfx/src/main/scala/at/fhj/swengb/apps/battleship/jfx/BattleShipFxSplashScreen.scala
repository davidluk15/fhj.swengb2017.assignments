package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}


class BattleShipFxSplashScreen extends Initializable {

  @FXML def toMain(): Unit = {
    BattleShipFxApp.display(BattleShipFxApp.loadMainMenu,BattleShipFxApp.loadMain)
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {

  }



}