package BasicScala.lectures.OOP

object InheritanceAndTraits extends App {

  sealed class Animal {
    val creatureType = "normal"

    def eat(): Unit = println("SuperClass")
  }

  class Cat extends Animal {
    def crunch(): Unit = {
      super.eat()
      println("SubClass")
    }

  }

  val cat = new Cat
  cat.crunch()


  //constructors

  class Person(name: String, age: Int = 0)

  class Adults(name: String, age: String, idCard: String) extends Person(name)

  class Dog(override val creatureType: String) extends Animal {
    super.eat()

    override def eat(): Unit = println("crunch , crunch")
  }

  val dog = new Dog("Normal")
  dog.eat()
  println(dog.creatureType)

  val dogAnimal = new Animal
  dogAnimal.eat()

  val dogAni: Animal = new Dog("FogType")

  dogAni.eat()

  // overRIDING vs overLOADING

  // super

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on the entire class
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other files

}

