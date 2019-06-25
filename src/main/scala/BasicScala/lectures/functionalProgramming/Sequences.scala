package BasicScala.lectures.functionalProgramming

object Sequences extends App {

  //Seq
  val aSequence = Seq(1, 2, 3, 4)
  println(aSequence) ///Apply factory method
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  //Ranges

  val a: Seq[Int] = 1 to 10
  println(a)

  (1 to 10).foreach(x => println("Hello"))

  val aList = List(1,2,3)
  val prependList = 42 +: aList :+ 23
  println(prependList)

  println(aList.mkString("-"))

  val apples5 = List.fill(5)("Apple")
  println(apples5)

  //Arrays

  val numbers = Array(1,2,3,4)
  val treeElement = Array.ofDim[Int](3)

  treeElement.foreach(println)

  numbers(2) = 0 ///numbers.update (2,0)
  println(numbers.mkString(" "))


  //arrays and Seq

  val numberSeq : Seq[Int] = numbers
  println(numberSeq)


  /** Vector
    **/

  val vector = Vector(1,2,3,4)
  println(vector)


  // vectors vs lists

  // keeps reference to tail
  // updating an element in the middle takes long

  // depth of the tree is small
  // needs to replace an entire 32-element chunk
}
