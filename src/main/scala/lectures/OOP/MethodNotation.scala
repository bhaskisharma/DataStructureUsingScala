package lectures.OOP

object MethodNotation extends App {

  class Person(val name: String, favoriteMovie: String) {

    def likes(movies: String) : Boolean = movies == favoriteMovie
    def +(person:Person) : String = s"${this.name} is hanging out with other ${person.name}"

  }

  val person = new Person("Rahul","DDLJ")
  println(person.likes("DDLJ"))
  println(person likes "DDLJ") //infix operation = operator notation

  val tom = new Person("David","DDLJ")
  println(person + tom)
  println (1 + 2)

}
