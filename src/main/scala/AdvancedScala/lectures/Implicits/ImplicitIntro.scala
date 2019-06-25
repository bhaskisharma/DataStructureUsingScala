package AdvancedScala.lectures.Implicits

object ImplicitIntro extends App {

  val pair = "A" -> "B"
  val intPair = 1 -> 2

  case class Person(name: String) {
    def greet = s"Hi my name is $name"
  }


  //implicit methods
  implicit def fromStringToPerson(str : String): Person = Person(str)

  println("XYZ".greet)  // println(fromStringToPerson("XYZ").greet)

  //implicit parameter

  def inc(x :Int)(implicit amount : Int) = x + amount

  implicit val defaultAmount: Int = 10

  println(inc(2))






}
