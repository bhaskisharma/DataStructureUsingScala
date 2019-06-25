package BasicScala.exercise.BasicExercis

object TailRecursionExercise extends App {

  /*
   1.  Concatenate a string n times
   2.  IsPrime function tail recursive
   3.  Fibonacci function, tail recursive.
  */

  /**
    * Concatenate a String n times
    **/
  def concatenateStringTail(aString: String, n: Int): String = {
    def conTailRec(aStr: String, x: Int, accumlator: String): String =
      if (x <= 0) accumlator
      else
        conTailRec(aStr, x - 1, aStr + accumlator)

    conTailRec(aString, n, "")
  }

  println(concatenateStringTail("hello", 3))


  /**
    *   2.  IsPrime function tail recursive
    **/

  def isPrime(n: Int): Boolean = {
    def primeCheck(t: Int, accumulator: Boolean): Boolean =
      if (!accumulator) false
      else if (t <= 1) true
      else primeCheck(t - 1, n % 2 != 0 && accumulator)

    primeCheck(n / 2, true)
  }

  println(isPrime(2103))
  println(isPrime(629))


  /**
    *     3.  Fibonacci function, tail recursive.
    **/
  def fibonacci(n: Int): Int = {
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if(i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }

  println(fibonacci(8)) // 1 1 2 3 5 8 13, 21
}
