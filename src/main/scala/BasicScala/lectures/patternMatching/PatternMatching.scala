package BasicScala.lectures.patternMatching

import scala.util.Random

object PatternMatching extends App {
  val random = new Random()
  val number = random.nextInt(10)

  val description = number match {
    case 1 => "One Number"
    case 2 => "Two Number"
    case 3 => "Three Number"
    case 4 => "Four Number"
    case _ => "Something else"
  }

  println(number)
  println(description)


  //Decompose Values

  case class Person(name: String, age: Int)

  val bob = Person("Bob", 23)


  val greeting = bob match {
    case Person(n, a) => s"Hi, my name $n and age $a year old "
    case Person(name, age) if age < 21 => s"Hi, my name is $name and I can't drink in the US"
    case _ => "I don't know who am i "
  }

  println(greeting)


  //PM on sealed hierachies

  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

  println(animal)

  //Match everything

  val isEven = number match {
    case numeber if number % 2 == 0 => true
    case _ => false
  }

  val isEvenNumber = number % 2 == 0

  /*
    Exercise
    simple function uses PM
     takes an Expr => human readable form
     Sum(Number(2), Number(3)) => 2 + 3
     Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
     Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
     Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
   */

  trait Expr

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Prod(e1: Expr, e2: Expr) extends Expr


  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => {
      def showParanthesse(expr: Expr) = expr match {
        case Prod(_, _) => show(expr)
        case Number(_) => show(expr)
        case _ => "(" + show(expr) + ")"
      }

      showParanthesse(e1) + " * " + showParanthesse(e2)
    }
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(4)))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))



  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)


}
