package BasicScala.lectures.OOP

object MethodNotation extends App {

  class Person(val name: String, val favoriteMovie: String, val age: Int = 0) {

    def likes(movies: String): Boolean = movies == favoriteMovie

    def +(person: Person): String = s"${this.name} is hanging out with other ${person.name}"

    def +(nickName: String): Person = new Person(s"$name ($nickName)", favoriteMovie)

    def unary_! : String = s"$name what the hack"

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def isAlive: Boolean = true

    def learns(learn: String) = s"$name learn $learn"

    def learnsScala: String = this learns "Scala"

    def apply(): String = s"Hi my name is $name and like $favoriteMovie"

    def apply(n: Int): String = s"$name watch $favoriteMovie $n times"

  }

  val rahul = new Person("Rahul", "DDLJ")
  println(rahul.likes("DDLJ"))
  println(rahul likes "DDLJ") //infix operation = operator notation

  val tom = new Person("David", "DDLJ")
  println(rahul + tom)
  println(1 + 2)

  println(!rahul)
  println(rahul.unary_!)


  // unary operator also method

  val x = -1
  val y = 1.unary_-
  //unary_ prefix only work with  - + ~ !

  //postfix
  println(rahul.isAlive)
  println(rahul isAlive)


  //apply is the special method in scala

  println(rahul.apply())
  println(rahul())


  /*
   1.  Overload the + operator
       mary + "the rockstar" => new person "Mary (the rockstar)"
   2.  Add an age to the Person class
       Add a unary + operator => new person with the age + 1
       +mary => mary with the age incrementer
   3.  Add a "learns" method in the Person class => "Mary learns Scala"
       Add a learnsScala method, calls learns method with "Scala".
       Use it in postfix notation.
   4.  Overload the apply method
       mary.apply(2) => "Mary watched Inception 2 times"
  */

  println((rahul + "the Rockstar").apply())
  println((+rahul).age)
  println(rahul learnsScala)
  println(rahul(10))


}
