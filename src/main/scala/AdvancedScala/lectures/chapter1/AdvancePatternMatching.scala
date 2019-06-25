package AdvancedScala.lectures.chapter1

object AdvancePatternMatching extends App {

  val numbers = List(1, 2, 3)
  val description = numbers match {
    case head :: Nil => println(s"the only element is $head")
    case _ =>
  }

  /*
    - constants
    - wildcards
    - case classes
    - tuples
    - some special magic like above
   */

  class Person(var name: String, var age: Int)

  object Person {
    def unapply(person: Person): Option[(String, Int)] =
      if (person.age < 21) None
      else
        Some(person.name, person.age)

    def unapply(age: Int): Option[(String)] = Some(if (age < 21) "minor" else "major")
  }

  val bob = new Person("Bob", 23)
  val greeting = bob match {
    case Person(n, a) => s"Hi my name is $n and age is $a"
  }
  println(greeting)

  val legalStatus = bob.age match {
    case Person(status) => s"my age is legal status  $status"
  }
  println(legalStatus)


  //Exercise

  object even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object singleDigit {
    def unapply(arg: Int): Boolean = arg > -10 && arg < 10
  }

  val n: Int = 8
  val mathProperty = n match {
    case singleDigit() => "single digit"
    case even() => "an even number"
    case _ => "no property"
  }

  println(mathProperty)

  //infix Patterns

  case class Or[A, B](a: A, b: B)

  val either = Or(32, "Hello")
  val humanDescription = either match {
    case Or(number, string) => s"$number is written in the $string"
  }

  println(humanDescription)

  //decompsing sequences

  val varargs = numbers match {
    case List(1, _*) => "starting with 1"
  }

  abstract class MyList[+A] {
    def head: A = ???

    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]

  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))

  val decomposed = myList match {
    case MyList(1, 2, _*) => "starting with 1, 2"
    case _ => "something else"
  }

  println(decomposed)

  // custom return types for unapply
  // isEmpty: Boolean, get: something.

  abstract class Wrapper[T] {
    def isEmpty: Boolean

    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      override def isEmpty: Boolean = false

      override def get: String = person.name
    }
  }

  println(bob match {
    case PersonWrapper(n) => s"This is person name is $n"
    case _ => "Anything"
  })

}
