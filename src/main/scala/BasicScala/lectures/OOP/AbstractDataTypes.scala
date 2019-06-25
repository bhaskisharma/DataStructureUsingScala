  package BasicScala.lectures.OOP

object AbstractDataTypes extends App {


    abstract class Animal {
      val creatureType: String

      def eat(): Unit
    }

    class Dog extends Animal {
      override val creatureType: String = "DogType"

      override def eat(): Unit = println("crunch Crunch")
    }

    //traits

    trait Carnivore {
      def eat(animal: Animal): Unit
    }

    trait coldBlooded

    class Crocodile extends Animal with Carnivore with coldBlooded {
      override val creatureType: String = "croc"

      def eat(): Unit = println("nomnomnom")

      def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
    }

    val dog = new Dog
    val croc = new Crocodile
    croc.eat(dog)

  }
