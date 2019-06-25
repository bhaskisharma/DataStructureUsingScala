package AdvancedScala.lectures.concurrency

import java.util.concurrent.Executors

object Intro extends App {

  /*
  interface Runnable {
    public void run()
  }
 */

  //JVM Thread

  //1 Way to create a thred using Thread Class
  //  val aThread: Thread = new Thread(new Runnable {
  //    override def run(): Unit = println("JYM thread is running")
  //  })
  //  aThread.start()


  // 2. Way to creat a thread using Runnable interface

  val aRunnable: Runnable = new Runnable {
    override def run(): Unit = println("Runnable interface to start thread")
  }
  val bThread = new Thread(aRunnable)

  bThread.start()
  bThread.join() //will block the until the thread finishes the running


  val threadHello = new Thread(new Runnable {
    override def run(): Unit = (1 to 5).foreach(_ => println("Hello"))
  })


  val goodbyeHello = new Thread(new Runnable {
    override def run(): Unit = (1 to 5).foreach(_ => println("goodbyeHello"))
  })

  //  threadHello.start()
  //  goodbyeHello.start()

  //Executors
  val pool = Executors.newFixedThreadPool(10)
  pool.execute(new Runnable {
    override def run(): Unit = println("Something in thread pool")
  })

  pool.execute(new Runnable {
    override def run(): Unit =
      Thread.sleep(1000)

    println("done after 1 sec")
  })

  pool.execute(new Runnable {
    override def run(): Unit =
      Thread.sleep(1000)

    println("almost done")
    Thread.sleep(1000)
    println("done after 2 seconds")
  })

  //  pool.execute(new Runnable {
  ////    override def run(): Unit = println("Starting")
  ////  })

  //  pool.shutdownNow()

  pool.shutdown()


  //  def runInParallel = {
  //    var x = 0
  //    val thread1 = new Thread(new Runnable {
  //      override def run(): Unit = x = 1
  //    })
  //
  //    val thread2 = new Thread(new Runnable {
  //      override def run(): Unit = x = 2
  //    })
  //
  //    thread1.start()
  //    thread2.start()
  //    println(x)
  //  }

  //  for(_ <- 1 to 10000) runInParallel

  //raceCondition

  class BankAccount(@volatile var amount: Int) {
    override def toString: String = "" + amount
  }

  def buy(account: BankAccount, thing: String, price: Int): Unit = {
    account.amount -= price
    //    println("I have bought ."+thing)
    //    println("my account "+account)
  }

  for (_ <- 1 to 100) {
    val account = new BankAccount(50000)
    val thread1 = new Thread(new Runnable {
      override def run(): Unit = buy(account, "shoes", 3000)
    })
    val thread2 = new Thread(new Runnable {
      override def run(): Unit = buy(account, "Iphones", 4000)
    })

    thread1.start()
    thread2.start()
  }

  // option #1: use synchronized()

  def buySafe(account: BankAccount, thing: String, price: Int): Unit = {
    account.synchronized {
      account.amount -= price
      println("I have bought ." + thing)
      println("my account " + account)
    }
  }

  // option #2: use @volatile


  /**
    * Exercises
    *
    * 1) Construct 50 "inception" threads
    * Thread1 -> thread2 -> thread3 -> ...
    * println("hello from thread #3")
    * in REVERSE ORDER
    *
    */

  def inceptionThread(maxThread: Int, i: Int = 1): Thread = new Thread(new Runnable {
    override def run(): Unit = {
      if (i < maxThread) {
        val newThread = inceptionThread(maxThread, i + 1)
        newThread.start()
        newThread.join()
      }
      println(s"Hello from thread $i")
    }
  })

  inceptionThread(50).start()


  /**
    * 2
    * */

  var x = 0
  val threads = (1 to 100).map(_ => new Thread(new Runnable {
    override def run(): Unit = x += 1
  }))

  threads.foreach(_.start())


  /*
    1) what is the biggest value possible for x? 100
    2) what is the SMALLEST value possible for x? 1
    thread1: x = 0
    thread2: x = 0
      ...
    thread100: x = 0
    for all threads: x = 1 and write it back to x
   */
  threads.foreach(_.join())
  println(x)


  var message = ""
  val awesomThread = new Thread(new Runnable {
    override def run(): Unit = Thread.sleep(1000)
    message = "scala is awesom"
  })

  message = "scala sucks"
  awesomThread.start()
  Thread.sleep(1001)
  awesomThread.join()
  println(message)

}
