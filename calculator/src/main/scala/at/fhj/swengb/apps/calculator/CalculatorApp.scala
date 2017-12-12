package at.fhj.swengb.apps.calculator

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.TextField
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.{Failure, Success}
import scala.util.control.NonFatal

object CalculatorApp {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[CalculatorFX], args: _*)
  }
}

class CalculatorFX extends javafx.application.Application {

  val fxml = "/at/fhj/swengb/apps/calculator/calculator.fxml"
  val css = "/at/fhj/swengb/apps/calculator/calculator.css"

  def mkFxmlLoader(fxml: String): FXMLLoader = {
    new FXMLLoader(getClass.getResource(fxml))
  }

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("Calculator Lukas David")
      setSkin(stage, fxml, css)
      stage.show()
      stage.setMinWidth(stage.getWidth)
      stage.setMinHeight(stage.getHeight)
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

  def setSkin(stage: Stage, fxml: String, css: String): Boolean = {
    val scene = new Scene(mkFxmlLoader(fxml).load[Parent]())
    stage.setScene(scene)
    stage.getScene.getStylesheets.clear()
    stage.getScene.getStylesheets.add(css)
  }

}

class CalculatorFxController extends Initializable {

  val calculatorProperty: ObjectProperty[RpnCalculator] = new SimpleObjectProperty[RpnCalculator](RpnCalculator())

  def getCalculator() : RpnCalculator = calculatorProperty.get()

  def setCalculator(rpnCalculator : RpnCalculator) : Unit = calculatorProperty.set(rpnCalculator)

  @FXML var resultText : TextField = _

  override def initialize(location: URL, resources: ResourceBundle) = {

  }

  def sgn(): Unit = {
    getCalculator().push(Op(resultText.getText)) match {
      case Success(c) => setCalculator(c); resultText.setText(c.peek().asInstanceOf[Val].value.toString)
      case Failure(e) => setCalculator(RpnCalculator()); resultText.setText("Error") // show warning / error
    }
    getCalculator().stack foreach println
  }

  def btX(): Unit = setOp("*")

  def btDivision(): Unit = setOp("/")

  def btPlus(): Unit = setOp("+")

  def btMinus(): Unit = setOp("-")

  def btEnter(): Unit = {
    if (resultText.getText != "Error" && !resultText.getText.contains(".")) {
      resultText.setText(resultText.getText + ".")
    }
  }
  def bt0(): Unit = setNumber("0")

  def bt1(): Unit  = setNumber("1")

  def bt2(): Unit  = setNumber("2")

  def bt3(): Unit  = setNumber("3")

  def bt4(): Unit  = setNumber("4")

  def bt5(): Unit  = setNumber("5")

  def bt6(): Unit  = setNumber("6")

  def bt7(): Unit  = setNumber("7")

  def bt8(): Unit  = setNumber("8")

  def bt9(): Unit = setNumber("9")



  def btSign(): Unit = {
    if (resultText.getText != "Error"
      && resultText.getText != "0"
      && resultText.getText != "*"
      && resultText.getText != "/"
      && resultText.getText != "-"
      && resultText.getText != "+") {
      val dbl = resultText.getText.toDouble * -1
      resultText.setText(dbl.toString)
    }
  }

  def btClear(): Unit = {
    if (resultText.getText == "0") {
      setCalculator(RpnCalculator())
    } else {
      resultText.setText("")
    }
  }

  private def setOp(op: String): Unit = {
    if (resultText.getText != "Error") {
      resultText.setText(op)
    }
  }

  private def setNumber(s: String): Unit = {
    if (resultText.getText == "Error"
      || resultText.getText == "0"
      || resultText.getText == "*"
      || resultText.getText == "/"
      || resultText.getText == "-"
      || resultText.getText == "+") {
      resultText.setText(s)
    } else {
      resultText.setText(resultText.getText + s)
    }
  }

}