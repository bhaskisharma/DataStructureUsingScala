package AdvancedScala.lectures.concurrency

import AdvancedScala.exercise.{Consumer, Producer}

import scala.collection.mutable
import scala.util.Random

object ThreadCommunication extends App {


  class SimpleContainer {
    private var value = 0

    def isEmpty: Boolean = value == 0

    def set(newValue: Int): Unit = value = newValue

    def get(): Int = {
      val result = value
      value = 0
      result
    }
  }


  def naiveProducerConsumer(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(new Runnable {
      override def run(): Unit = {
        println("[consumer] waiting..")
        while (container.isEmpty) {
          println("[consumer] actively waiting...")
        }
        println("[consumer] I have consumed " + container.get)
      }
    })

    val producer = new Thread(new Runnable {
      override def run(): Unit = {
        println("[producer] computation")
        Thread.sleep(500)
        val value = 42
        println("[producer] computation value " + value)
        container.set(value)
      }
    })

    consumer.start()
    producer.start()
  }


  // wait and notify


  def startProdCons(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(new Runnable {
      override def run(): Unit = {
        println("[consumer] waiting...")
        container.synchronized {
          container.wait()
        }
        println("[consumer] I have consumed " + container.get)
      }
    })

    val producer = new Thread(new Runnable {
      override def run(): Unit = {
        println("[producer] computation")
        val value = 42
        println("[producer] I'm producing " + value)
        container.synchronized {
          container.set(value)
          container.notify()
        }
      }
    })

    consumer.start()
    producer.start()
  }

  //  startProdCons()
  /*
    producer -> [ ? ? ? ] -> consumer
   */

  def prodConsLargerBuffer(): Unit = {
    val container = new SimpleContainer

    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3

    val consumer = new Thread(new Runnable {
      override def run(): Unit = {
        val random = new Random()
        while (true) {
          buffer.synchronized {
            if (buffer.isEmpty) {
              println("[consumer] buffer empty, waiting...")
              buffer.wait()
            }

            val x = buffer.dequeue()
            println("[consumer] consumed " + x)

            //hey producer you can produce now

            buffer.notify()
          }
          Thread.sleep(random.nextInt(500))
        }
      }
    })


    val producer = new Thread(new Runnable {
      override def run(): Unit = {
        val random = new Random()
        var i = 0
        while (true) {
          buffer.synchronized {
            if (buffer.size == capacity) {
              println("[producer] buffer is full, waiting...")
              buffer.wait()
            }

            println("[producer] producing " + i)
            buffer.enqueue(i)
            //hey consumer i am free now you can consume
            buffer.notify()

            i += 1
          }
          Thread.sleep(random.nextInt(500))
        }
      }
    })
    consumer.start()
    producer.start()
  }

  //  prodConsLargerBuffer()


  def multiProdCons(nConsumer: Int, nProducer: Int): Unit = {
    val buffer = new mutable.Queue[Int]
    val capacity = 20

    (1 to nConsumer).foreach(i => new Consumer(i, buffer).start())
    (1 to nProducer).foreach(i => new Producer(i, buffer, capacity).start())
  }

  //  multiProdCons(3, 6)

  /*
   Exercises.
   1) think of an example where notifyALL acts in a different way than notify?
   2) create a deadlock
   3) create a livelock
  */


  def testNotifyAll(): Unit = {
    val bell = new Object

    (1 to 10).foreach(i => new Thread(new Runnable {
      override def run(): Unit = {
        bell.synchronized {
          println(s"[thread $i] waiting...")
          bell.wait()
          println(s"[thread $i] hooray!")
        }
      }
    }).start())

    new Thread(new Runnable {
      override def run(): Unit = {
        Thread.sleep(2000)
        println("[announcer] Rock'n roll!")
        bell.synchronized {
          bell.notifyAll()
        }
      }
    }).start()

  }

  //  testNotifyAll()


  //Create deadlock

  case class Friend(name: String) {


    def bow(other: Friend): Unit = {
      this.synchronized {
        println(s"$this: I am bowing to my friend $other")
        other.rise(this)
        println(s"$this: my friend $other has risen")
      }
    }

    def rise(other: Friend): Unit = {
      this.synchronized {
        println(s"$this: I am rising to my friend $other")
      }
    }

    var side = "right"

    def switchSide(): Unit = {
      if (side == "right") side = "left"
      else side = "right"
    }

    def pass(other: Friend): Unit = {
      while (this.side == other.side) {
        println(s"$this: Oh, but please, $other, feel free to pass...")
        switchSide()
        Thread.sleep(1000)
      }
    }

  }

  val sam = Friend("Sam")
  val pierre = Friend("Pierre")

//    new Thread(new Runnable {
//      override def run(): Unit =sam.bow(pierre)}).start() // sam's lock,    |  then pierre's lock
//    new Thread(new Runnable {
//      override def run(): Unit = pierre.bow(sam)}).start() // pierre's lock  |  then sam's lock


  // 3 - livelock
  new Thread(new Runnable {
         override def run(): Unit = sam.pass(pierre)}).start()
  new Thread(new Runnable {
    override def run(): Unit = pierre.pass(sam)}).start()


}
