package BasicScala.lectures.functionalProgramming

object HigherOrderAndCurry extends App {

  val superFunction: (Int, (String, Int => Boolean) => Int) => Int => Int = null

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  // nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n-1, f(x))

  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOne = (x: Int) => x + 1

  println(nTimes(plusOne, 10, 1))


  /**
    * suquareSum, cubeOfSum and sum in Higher order function simple example
    **/

  val squareSum = (x: Int, y: Int) => x * x + y * y
  val cubeOfSum = (x: Int, y: Int, z: Int) => x * x * x + y * y * y + z * z * z
  val sumOfTwo = (x: Int, y: Int) => x + y


  def addition(f: (Int, Int) => Int, x: Int, y: Int) = f(x, y)

  println("Square of Numbers and Sum = " + addition(squareSum, 10, 20))

  def cubeOfNum(f: (Int, Int, Int) => Int, x: Int, y: Int, z: Int) = f(x, y, z)

  println("Cube of Numbers and Sum = " + cubeOfNum(cubeOfSum, 10, 20, 30))

  def sumOfNum(f: (Int, Int) => Int, x: Int, y: Int) = f(x, y)

  println("Sume of two numbers = " + sumOfNum(sumOfTwo, 10, 20))

  def nTimesBetter(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  val plusTo: Int => Int = nTimesBetter(plusOne, 10)
  println(plusTo(1))


  //Currying

  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y

  val adder = superAdder(3)

  println(adder(10))
  println(superAdder(10)(20))

  // functions with multiple parameter lists

  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: Double => String = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*

   1.  toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
       fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int
   2.  compose(f,g) => x => f(g(x))
       andThen(f,g) => x => g(f(x))
  */

  def toCurry(f: (Int, Int) => Int): Int => Int => Int = x => y => f(x,y)

  def fromCurry(f: Int => Int => Int) = (x: Int, y: Int) => f(x)(y)

  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int) = (x: Int) => g(f(x))


  def superAdder2: Int => Int => Int = toCurry(_ + _)

  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4,17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))
}
