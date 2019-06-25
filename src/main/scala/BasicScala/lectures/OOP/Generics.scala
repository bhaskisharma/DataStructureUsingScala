package BasicScala.lectures.OOP

object Generics extends App {

  class MyList[+A] {
    //use the type A inside the type A
  }

  class MyMap[Key, Value]

  val listOfIntger = new MyList[Int]
  val listOfString = new MyList[String]

  object MyList {
    def empty[A] :MyList[A] = ???
  }

  val emptyListInt = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE

  class CovariantList[+A]
  val animal : Animal = new Cat
  val animalList : CovariantList[Animal] = new CovariantList[Cat]

  // 2. Invariance

  class InvariantList[A]

  val invariantList : InvariantList[Cat] = new InvariantList[Cat]


  //3. Contravariant List

  class ContravariantList[-A]
  val contravariantList : ContravariantList[Cat] = new ContravariantList[Animal]

  //Bounded Type Upper Bounded Type

  class Cage[A <: Animal](animal : A)
  val cage = new Cage(new Dog)

  class Car
}
