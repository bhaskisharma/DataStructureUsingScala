package AdvancedScala.exercise

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Success, Try}

object BankingTranscationApp extends App {

  case class User(name: String)

  case class Transaction(sender: String, receiver: String, amount: Double, status: String)

  object BankingApp {
    val name = "Banking App"

    def fetchUser(name: String): Future[User] = Future {
      //do some DB operation
      Thread.sleep(500)
      User(name)
    }

    def createTransaction(user: User, merchantName: String, amount: Double): Future[Transaction] = Future {
      //Do Some Db Operation for Transaction
      Thread.sleep(500)
      Transaction(user.name, merchantName, amount, "SUCCESS")
    }

    def purchase(userName: String, item: String, merchantNamt: String, cost: Double): String = {
      //Do some DB operation
      val transactionStatus = for {
        user <- fetchUser(userName)
        transaction <- createTransaction(user, merchantNamt, cost)
      } yield transaction.status

      Await.result(transactionStatus, 2.seconds)
    }
  }

  //  println(BankingApp.purchase("Mark", "IPhone8", "XYZ", 6000))


  //Promise

  val promise = Promise[Int]()
  val future = promise.future

  //Thread 1

  future.onComplete {
    case Success(r) => println("[consumer] I've received " + r)
  }

  //Thread 2

  val producer = new Thread(new Runnable {
    override def run(): Unit = {
      println("[producer] crunching numbers...")
      Thread.sleep(500)
      promise.success(42)
      println("[producer] done")
    }
  })

  //  producer.start()
  ////  Thread.sleep(1000)


  /*
   1) fulfill a future IMMEDIATELY with a value
   2) inSequence(fa, fb)
   3) first(fa, fb) => new future with the first value of the two futures
   4) last(fa, fb) => new future with the last value
   5) retryUntil[T](action: () => Future[T], condition: T => Boolean): Future[T]
  */


  //   1) fulfill a future IMMEDIATELY with a value

  def fullFill[T](value: T): Future[T] = Future(value)

  //   2) inSequence(fa, fb)

  def inSequence[A, B](firstA: Future[A], secondB: Future[B]): Future[B] =
    firstA.flatMap(_ => secondB)


  //   3) first(fa, fb) => new future with the first value of the two futures
  def first[A](fa: Future[A], fb: Future[A]): Future[A] = {
    val promise = Promise[A]
    fa.onComplete(promise.tryComplete)
    fb.onComplete(promise.tryComplete)

    promise.future
  }

  // 4 - last out of the two futures

  def last[A](fa: Future[A], fb: Future[A]): Future[A] = {
    val bothPromise = Promise[A]
    val lastPromise = Promise[A]

    val checkAndComplete = (result: Try[A]) =>
      if (!bothPromise.tryComplete(result))
        lastPromise.tryComplete(result)

    fa.onComplete(checkAndComplete)
    fb.onComplete(checkAndComplete)

    lastPromise.future
  }


  val listFuture = Future(
    List(1, 2, 3)
  )

  listFuture.onComplete {
    case Success(value) => println(value.map(list => list * 2))
  }

  Thread.sleep(500)


}
