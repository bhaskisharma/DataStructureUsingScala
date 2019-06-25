package BasicScala.lectures.functionalProgramming

object AnonymousFunction extends App {

  val doubler: Int => Int = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }

  val dou: Int => Int = x  => x * 2 // Anonymous Function Lambda

  //Multiple parameter

  val adder : (Int , Int) => Int = (x, y) => x + y

  //No params just Do Something

  val noParams : () => Int = () => 3

  println(noParams) //Function method
  println(noParams()) // function by value actually call

  val stringToInt = { str :String =>
    str.toInt
  }

  println(stringToInt("10"))

  val incrementor: Int => Int = _ +1
  val decrementor : Int => Int = _ - 1

  /*
     1.  Rewrite the "special" adder as an anonymous function
    */

  val specialAdder: Int => Int => Int = (x : Int) => (y : Int) => x + y

  println(specialAdder(10)(20))

}
