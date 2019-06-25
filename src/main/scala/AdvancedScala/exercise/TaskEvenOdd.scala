package AdvancedScala.exercise

class TaskEvenOdd extends Runnable {

  private var isEvenNumber = false
  private var max = 0
  @volatile var isOddNumber = false

  override def run(): Unit = {
    var number = if (isEvenNumber) 2 else 1
    while (number <= max) {
      if (isEvenNumber) {
        //print Even number
        printEven(number)

      } else {
        //print odd number
        printOdd(number)
      }
      number += 2
    }
  }

  def printEven(number: Int): Unit = {
    synchronized {
      while (!isOddNumber) {
        try {
          wait()
        } catch {
          case ex: InterruptedException => ex.printStackTrace()
        }
      }
      println(Thread.currentThread().getName + ":" + number)
      isOddNumber = false
      notify()
    }
  }

  def printOdd(number: Int): Unit = {
    synchronized {
      while (isOddNumber) {
        try {
          wait()
        } catch {
          case ex: InterruptedException => ex.printStackTrace()
        }
      }
      println(Thread.currentThread().getName + ":" + number)
      isOddNumber = true
      notify()
    }
  }


}
