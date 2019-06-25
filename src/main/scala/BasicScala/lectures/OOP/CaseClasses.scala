package BasicScala.lectures.OOP

object CaseClasses extends App {

  /**
    * equal , hashcode, toString
    **/

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("jim", 12)
  println(jim.name)

  //2. toString representation

  println(jim.toString)
  println(jim)
  //Same

  // 3. equals and hashCode implemented OOTB

  val jim2 = new Person("jim", 12)
  println(jim == jim2)

  //4 hand copy method

  val jim3 = jim.copy(age = 23)
  println(jim3)

  // 5. CCs have companion objects

  val person = Person
  val mary = Person("Mary",23)

  // 6. CCs are serializable
  // Akka

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
    Expand MyList - use case classes and case objects
   */

}
