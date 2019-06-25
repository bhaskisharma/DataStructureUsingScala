package BasicScala.lectures.Basics

object Functions extends App {

  def aFunction(a: String ,b : Int) : String = a + " " + b

  println(aFunction("Hello",3))

  def aParameterLessFunction() : Int  = 42

  println(aParameterLessFunction())

  def aRepeatedFunction(aString: String , n : Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString,n-1)
  }

  println(aRepeatedFunction("Hello",3))

  def aFunctionWithSideEffects(aString: String) : Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallFunction(a: Int, b: Int): Int = a + b
    aSmallFunction(n, n-1)
  }
}
