package BasicScala.lectures.Basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println("Computing Factorial of " + n + "I first need Factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }
  }

  println(factorial(10))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumator: BigInt): BigInt =
      if (x <= 1) accumator
      else factHelper(x - 1, x * accumator) //Tail Recursion

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))
}
