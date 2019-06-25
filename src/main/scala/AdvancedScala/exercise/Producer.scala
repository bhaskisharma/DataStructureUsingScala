package AdvancedScala.exercise

import scala.collection.mutable
import scala.util.Random

class Producer(id: Int, buffer: mutable.Queue[Int], capacity: Int) extends Thread {

  override def run(): Unit = {

    val random = new Random()
    var i = 0

    while (true) {
      buffer.synchronized {
        if (buffer.size == capacity) {
          println(s"[producer $id] buffer is full, waiting...")
          buffer.wait()
        }

        // there must be at least ONE EMPTY SPACE in the buffer
        println(s"[producer $id] producing " + i)
        buffer.enqueue(i)

        buffer.notifyAll()
        i += 1
      }
      Thread.sleep(random.nextInt(500))
    }
  }

}
