package AdvancedScala.lectures.chapter1

import scala.util.Try

object DarkSugar extends App {


  def singleMethodParam(single: Int) = s"$single dark sugar in scala"

  val description = singleMethodParam {
    42
  }

  println(description)

  val aTryInstance = Try {
    throw new RuntimeException
  }

  List(1, 2, 3, 4).map {
    x => x * 2
  }.foreach(println)

  trait Action {
    def act(x: Int): Int
  }

  val aInstance = new Action {
    override def act(x: Int): Int = x + 1
  }

  val thread = new Thread(new Runnable {
    override def run(): Unit = println("Runnable thread")
  })

  // syntax sugar #3: the :: and #:: methods are special

  val prependList = 2 :: List(3, 4, 5)

  1 :: 2 :: 3 :: List(4, 5)
  List(4, 5).::(3)

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this
  }

  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  class TeenGirl(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name and $gossip")
  }

  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "Scala is so sweet"


  //infix types
  class Composite[A, B]

  val composite: Int Composite String = ???

  class -->[A, B]

  val towards: Int --> String = ???

  //update methods
  val aArray = Array(1, 2, 3, 4)
  aArray(2) = 7 //aArray.update(2,7)


  //setters
  class Mutable {
    private var internalMember: Int = 0
    def member: Int = internalMember //getter
    def member_=(n: Int): Unit = {
      internalMember = n
    }
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // rewrittern as aMutableContainer.member_=(42)
}
