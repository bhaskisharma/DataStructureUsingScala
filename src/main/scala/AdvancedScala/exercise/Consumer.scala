package AdvancedScala.exercise

import scala.collection.mutable
import scala.util.Random

class Consumer(id: Int, buffer: mutable.Queue[Int]) extends Thread {

  override def run(): Unit = {
    val random = new Random()

    while (true) {
      buffer.synchronized {
        if (buffer.isEmpty) {
          println(s"[consumer $id] buffer empty, waiting...")
          buffer.wait()
        }
        val x = buffer.enqueue()
        println(s"[consumer $id] consumed " + x)
        buffer.notifyAll()
      }
      Thread.sleep(random.nextInt(250))
    }
  }

}
