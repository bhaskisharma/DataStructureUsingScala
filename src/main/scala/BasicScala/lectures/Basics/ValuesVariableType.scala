package BasicScala.lectures.Basics

/** *
  *
  * Variable testing application
  *
  */
object ValuesVariableType extends App {
  /***
    * variable using Val Type immutable
    * can not be reassigned
    * */
  val x: Int = 42
  println(x)

  val aString: String = "Hello World"
  println(aString)

  val asBoolean : Boolean = true
  println(asBoolean)

  val aChar : Char = 'a'
  println(aChar)

  val aShort: Short = 4613
  println(aShort)

  val aLong : Long = 4612345
  println(aLong)

  val asFloat : Float = 4.2f
  println(asFloat)

  val aDouble : Double = 4.00
  println(aDouble)

  /**
    * variable using var mutable
    * can be reassigned
    * */

  var aVariable: Int = 12
  println(aVariable)

}
