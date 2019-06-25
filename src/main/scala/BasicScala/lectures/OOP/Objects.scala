package BasicScala.lectures.OOP

object Objects extends App {

  //Scala does not have class level functionality (static)

  object Person {
    val N_EYS = 2

    def canFly: Boolean = false

    //factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }

  class Person(val name: String) {

  }

  //Same name and same scope is called comapanions object

  println(Person.N_EYS)
  println(Person.canFly)

  //Scala object is singleton object

  //Scala doesnot have static variable and method
  //scala has the object and we can implement singleton object
  //Companion object can exchange thr private members

  val per1 = Person
  val per2 = Person
  println(per1 == per2)

  val mary = new Person("Mary")
  val jhon = new Person("Jhon")
  println(mary == jhon)

  val bobby = Person(mary,jhon)

}
