package AdvancedScala.lectures.concurrency

import java.util.concurrent.atomic.AtomicReference

import scala.collection.parallel.{ForkJoinTaskSupport, Task, TaskSupport}
import scala.collection.parallel.immutable.ParVector
import scala.concurrent.forkjoin.ForkJoinPool

object ParallelUtils extends App {
  //1. Parallel Collection

  val parList = List(1, 2, 3).par

  val aParVector = ParVector[Int](1, 2, 3)

  /*
   Seq
   Vector
   Array
   Map - Hash, Trie
   Set - Hash, Trie
  */

  def measure[T](operation: => T): Long = {
    val time = System.currentTimeMillis()
    operation
    System.currentTimeMillis() - time
  }

  val list = (1 to 10000).toList
  val serialTime = measure {
    list.map(_ + 1)
  }
  println("SerialTime :" + serialTime)

  val parallelTime = measure {
    list.par.map(_ + 1)
  }

  println("parallelTime : " + parallelTime)

  /*
    Map-reduce model
    - split the elements into chunks - Splitter
    - operation
    - recombine - Combiner
   */

  // fold, reduce with non-associative operators
  println(List(1, 2, 3).reduce(_ - _))
  println(List(1, 2, 3).par.reduce(_ - _))

  // synchronization
  var sum = 0
  List(1, 2, 3, 4).par.foreach(sum += _)
  println(sum) // race conditions!


  //configuration

  aParVector.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(2))
  /*
     alternatives
     - ThreadPoolTaskSupport - deprecated
     - ExecutionContextTaskSupport(EC)

    */

//  aParVector.tasksupport = new TaskSupport{
//    override val environment: AnyRef = _
//
//    override def execute[R, Tp](fjtask: Task[R, Tp]): () => R = ???
//
//    override def executeAndWaitResult[R, Tp](task: Task[R, Tp]): R = ???
//
//    override def parallelismLevel: Int = ???
//  }


  // 2 - atomic ops and references

  val atomic  = new AtomicReference[Int](2)

  val currentValue = atomic.get() //Thread Safe Read
  atomic.set(23) //Thread safe write

  atomic.getAndSet(5)

  atomic.compareAndSet(32,43)


}
