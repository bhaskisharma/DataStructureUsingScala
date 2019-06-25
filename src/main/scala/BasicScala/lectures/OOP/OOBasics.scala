package BasicScala.lectures.OOP

import BasicScala.exercise.OOP.{Counter, Novel, Writer}
import BasicScala.exercise.OOP.{Counter, Writer}

object OOBasics extends App {

  val person = new Person("Jhon", 26)
  person.greet("Testing")

}

//Construtor
class Person(val name: String, val age: Int) {
  val x = 2

  def greet(name: String): Unit = println(s"My name is ${this.name} say hi $name")

  def greet(): Unit = println(s"My name is $name")

  //Overloading construtor

  def this(name: String) = this(name, 0)

  def this() = this("jhon deo")

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)


  //  println(novel.authorAge)
  //  println(novel.isWrittenBy(imposter))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print

}
