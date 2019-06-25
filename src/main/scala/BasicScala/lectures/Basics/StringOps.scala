package BasicScala.lectures.Basics

object StringOps extends App {

  val str: String = "Hello, How are you doing"

  println(str.charAt(0)) //charAt return the char at the index
  println(str.substring(7, 11)) // subString to 7 to 11
  println(str.split(" ").toList) //split based on the regex
  println(str.startsWith("Hello"))
  println(str.replace("How", "I am"))
  println(str.toLowerCase())
  println(str.length)

  val aNumberString = "2"
  val aNumberInt = aNumberString.toInt
  println('a' +: aNumberString :+ 'b') // +: prepending  :+ appending ops
  println(str.reverse) // reverse the string
  println(str.take(2)) //Take out

  // Scala Specific: String interpolators

  // S - interpolators

  val name = "Rahul"
  val age = 12
  val greeting = s"Hello, my name is $name and i am $age years old"
  println(greeting)
  val anotherGreeting = s"Hello, my name is $name and i am ${age + 1} years old"
  println(anotherGreeting)

  // F - Interpolators

  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per min"
  println(myth)

  //raw - Interpolators

  println(raw"This is a \n while")
  val escaped = "This is a \n newline"
  println(raw"$escaped")


}
