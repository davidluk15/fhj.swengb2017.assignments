package at.fhj.swengb.apps.battleship.model

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

/**
  * Represents one part of a vessel or one part of the ocean.
  */
case class Edit(pos: BattlePos,
                        width: Double,
                        height: Double,
                        someVessel: Option[Vessel] = None,
                        updateEditor: BattlePos => Unit)
  extends Rectangle(width, height) {

  def init(clickedPos: Seq[BattlePos]): Unit = {


    if (clickedPos.contains(pos)) {
      colorizeAfterClick()
    } else {
      init()
    }
  }

  def init(): Unit = {
    someVessel match {
      case Some(v) =>
        colorizeAfterClick()
      case None =>
        setFill(Color.DARKBLUE)
    }
  }

  setOnMouseClicked(e => {
    colorizeAfterClick()
    updateEditor(pos)
  })

  def colorizeAfterClick(): Unit = {
    setFill(Color.RED)
  }

}
