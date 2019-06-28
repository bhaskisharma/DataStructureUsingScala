package AdvancedScala.lectures.concurrency

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object FutureAndPromises extends App {


  def caculateMeaningOfLife : Int = {
    Thread.sleep(2000)
    42
  }

  val aFuture = Future {
    caculateMeaningOfLife
  } //(global) passed by the compiler

  println(aFuture.value)

  println("Waiting to complete the future")
  aFuture.onComplete {
    case Success(value) => println(s"meaning of life is $value")
    case Failure(e) => println(s"failed becoz of $e")
  }

  Thread.sleep(3000)

}
