package AdvancedScala.lectures.Implicits

object OrganizingImplicits extends App {

  implicit def reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)

  println(List(2, 3, 4, 5, 1).sorted)


  //Exercise
  case class Person(name: String, age: Int)

  val persons = List(
    Person("Steve", 30),
    Person("Amy", 22),
    Person("John", 66)
  )

  object Alphabate {
    implicit def reverseOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  }

  object AgeOrdering {
    //ordering by age
    implicit def reverseOrderingByAge: Ordering[Person] = Ordering.fromLessThan((a, b) => a.age < b.age)
  }

  import AgeOrdering._

  println(persons.sorted)

  /*
  Implicit scope
  - normal scope = LOCAL SCOPE
  - imported scope
  - companions of all types involved in the method signature
    - List
    - Ordering
    - all the types involved = A or any supertype
 */


  /*
   Exercise.
   - totalPrice = most used (50%)
   - by unit count = 25%
   - by unit price = 25%
  */

  case class Purchase(nUnits: Int, unitPrice: Double)

  object Purchase {
    implicit def totalPrice: Ordering[Purchase] = Ordering.fromLessThan(
      (a, b) => a.nUnits * a.unitPrice < b.nUnits * b.unitPrice)
  }

  object UnitCount {
    implicit def unitCount: Ordering[Purchase] = Ordering.fromLessThan(
      (a, b) => a.nUnits < b.nUnits)
  }

  object UnitPrice {
    implicit def unitCount: Ordering[Purchase] = Ordering.fromLessThan(
      (a, b) => a.unitPrice < b.unitPrice)
  }

}
