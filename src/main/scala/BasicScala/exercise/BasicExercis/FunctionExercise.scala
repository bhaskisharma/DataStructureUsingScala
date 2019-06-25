package BasicScala.exercise.BasicExercis

/**
  * Exercise 1
  *
  * */
object FunctionExercise extends App {

  /*
   *  1.  A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
   *
   **/

  println(aGreetingFunction("Rahul", 22))

  def aGreetingFunction(name: String, age: Int): String = {
    s"Hi, My name is $name and I am $age year old"
  }

  /*
   *  2.  Factorial function 1 * 2 * 3 * .. * n
   *
   * */

  println(aFactorialFunction(5))
  def aFactorialFunction(n: Int): Int = {
    if (n <= 0) 1
    else n * aFactorialFunction(n - 1)
  }

  /** 3.  A Fibonacci function
      f(1) = 1
    f(2) = 1
    f(n) = f(n - 1) + f(n - 2)
    * */

  println(9)
  def aFibonacciFunction(n: Int): Int = {
    if (n <= 2) 1
    else aFibonacciFunction(n - 1) + aFibonacciFunction(n - 2)
  }

  /**
    * Prime number calculation
    *
    * */
  def isPrime(n:Int):Boolean ={
    def isPrimeUtil(t:Int): Boolean =
      if(t <= 1) true
      else n % t != 0 && isPrimeUtil(t-1)

    isPrimeUtil(n / 2)
  }

  println("  "+isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}
