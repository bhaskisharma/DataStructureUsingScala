package AdvancedScala.lectures.Implicits

object TypeClass extends App {

  trait HTMLWritable {
    def toHTML: String
  }

  case class User(name: String, age: Int, email: String) extends HTMLWritable {
    override def toHTML: String = s"<div>$name ($age yo) <a href=$email/> </div>"
  }

  User("ABC", 233, "abc@abc.com").toHTML

  /*
   1 - for the types WE write
   2 - ONE implementation out of quite a number
  */

  /*
    1 - lost type safety
    2 - need to  modify the code every time
    3 - still ONE implementation
   */


  trait HTMLSerializer[T] {
    def serialize(value: T): String
  }

  implicit object UserSerializer extends HTMLSerializer[User] {
    override def serialize(user: User): String = s"<div>${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }

  val abc = User("ABC", 233, "abc@abc.com")
  println(UserSerializer.serialize(abc))

  //we can define other type of serializers

  import java.util.Date

  object DateSerializer extends HTMLSerializer[Date] {
    override def serialize(date: Date): String = s"<div>${date.toString}</div>"
  }

  object PartialSerializer extends HTMLSerializer[User] {
    override def serialize(user: User): String = s"<div>${user.name}</div>"
  }

  trait Equal[T] {
    def apply(a: T, b: T): Boolean
  }

  implicit object NameEquality extends Equal[User] {
    override def apply(a: User, b: User): Boolean = a.name == b.name
  }

  //part 2

  object HTMLSerializer {
    def serialize[T](value: T)(implicit serializer: HTMLSerializer[T]): String =
      serializer.serialize(value)

    def apply[T](implicit serializer: HTMLSerializer[T]): HTMLSerializer[T] = serializer
  }

  implicit object IntSerializer extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div style: color=blue>$value</div>"
  }

  println(HTMLSerializer.serialize(42))

  println(HTMLSerializer.serialize(abc))

  println(HTMLSerializer[User].serialize(abc))


  //Exercise: Implement the TC pattern for the equality

  object Equal {
    def apply[T](a: T, b: T)(implicit equalizer: Equal[T]): Boolean = equalizer.apply(a, b)

  }

  val anotherJhon = User("Jhon", 43, "jhon")

  println(Equal.apply(abc, anotherJhon))


  //Part 3


}
