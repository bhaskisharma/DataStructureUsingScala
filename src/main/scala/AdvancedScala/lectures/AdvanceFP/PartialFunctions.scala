package AdvancedScala.lectures.AdvanceFP

object PartialFunctions extends App {

  val aFunction = (x: Int) => x + 1 // Function[Int, Int]

  val aFussyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 43
    else if (x == 3) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  val aNicerFussyFunction = (x: Int) => x match {
    case 1 => 9
    case 2 => 3
    case 3 => 999
  }

  //define partial function with scope

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 9
    case 2 => 3
    case 3 => 999
  }


  //PF util
  println(aPartialFunction(2))
  println(aPartialFunction.isDefinedAt(45))

  val lifted = aPartialFunction.lift
  println(lifted(2))
  println(lifted(23))

  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  println(pfChain(2))
  println(pfChain(45))

  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  //HOF function accept partial function

  val aMappedList = List(1, 2, 3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList)

  /*
  Note: PF can only have ONE parameter type
 */

  /**
    * Exercises
    *
    * 1 - construct a PF instance yourself (anonymous class)
    * 2 - dumb chatbot as a PF
    */


  val manualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(x: Int): Int = x match{
      case 1 => 42
      case 2 => 78
      case 3 => 1000
    }

    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2 || x == 5
  }

  val chatBot: PartialFunction[String, String] = {
    case "hello" => "Hi, my name is HAL9000"
    case "goodbye" => "Once you start talking to me, there is no return, human!"
    case "call mom" => "Unable to find your phone without your credit card"
  }

  scala.io.Source.stdin.getLines().map(chatBot).foreach(println)

}
