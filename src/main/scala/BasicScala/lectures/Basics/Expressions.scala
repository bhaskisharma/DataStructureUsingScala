package BasicScala.lectures.Basics

/**
  * Expression Examples
  * */
object Expressions extends App {


  /**
    * In Scala everything is expression if we compute anything
    * */
  val x = 1 + 2
  println(x)

  println(2 + 3 * 4)

  println( 1 == x)

  println(!(1 == x))

  var aVariable = 2
  aVariable += 3
  println(aVariable)

  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 3
  println(aConditionValue)

  println(if(aCondition) 5 else 3)

  var i = 0


  /**Never write this in scala again*/
 var aWhile: Unit =  while(i < 10){
    println(i)
    i += 1
  }

  println(aWhile)

    /**Everthing in Scal ais exper*/

    val aWeirdValue: Unit = aVariable = 3 // reassign a var is side effects in scala
    println(aWeirdValue)

  // Code Blocks

  val aCodeBlocks = {
    val y = 2
    val z = y + 1

    if(z > 2) "Hello" else "bye"
  }

  val aString  = "Hello World" //Variable value of type string
  println("Hello World") // expression which is side effects

  val someValue = {
    2 < 3
  }

  println(someValue)

  val someOtherValue: Int = {
    if(someValue) 239 else 986
    42
  }
  println(someOtherValue)

}
