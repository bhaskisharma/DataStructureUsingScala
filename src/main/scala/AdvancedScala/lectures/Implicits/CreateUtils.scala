package AdvancedScala.lectures.Implicits

import scala.collection.immutable

object CreateUtils extends App {

  implicit class RichInt(val value: Int) extends AnyVal {
    def isEven: Boolean = value % 2 == 0

    def sqrt: Double = Math.sqrt(value)

    def times(func: () => Unit): Unit = {
      def timesAux(n: Int): Unit = {
        if (n <= 0) ()
        else {
          func()
          timesAux(n - 1)
        }
        timesAux(value)
      }
    }

    def *[T](list: List[T]): List[T] = {
      def concatenate(n: Int): List[T] =
        if (n <= 0) List()
        else concatenate(n - 1) ++ list

      concatenate(value)
    }
  }

  new RichInt(42).isEven

  import scala.concurrent.duration._

  2.seconds


  /*
   Enrich the String class
   - asInt
   - encrypt
     "John" -> Lqjp
   Keep enriching the Int class
   - times(function)
     3.times(() => ...)
   - *
     3 * List(1,2) => List(1,2,1,2,1,2)
  */

  implicit class RichString(string: String) {
    def asInt: Integer = Integer.valueOf(string)

    def encrypt(str: Int): String = string.map(c => (c + str).asInstanceOf[Char])
  }

  println("43".asInt)
  println("jhon".encrypt(2))

 3.times(() => println("Scala rocks"))

  println(4 * List(1,2,3))


  implicit def stringToInt(string:String): Int = Integer.valueOf(string)

  println("34"/ 2)
}
