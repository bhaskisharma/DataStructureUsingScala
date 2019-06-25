package BasicScala.lectures.functionalProgramming

object TuplesAndMap extends App {

  val tuples = List(1, "ABC", 4)

  val tuples2 = new Tuple2(2, "Hello Scala")

  println(tuples2._1 + " " + tuples2._2)


  //Map -  keys -> value
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("abc", 22), "dccd" -> 34).withDefaultValue(-1)

  println(phoneBook)

  //basic map Ops
  println(phoneBook.contains("abc"))
  println(phoneBook("Mark"))

  val newPairing = "Mark" -> 20

  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // functionals on maps
  // map, flatMap, filter

  println(newPhoneBook.map(x => x._1.toLowerCase() -> x._2))
  println(newPhoneBook.filterKeys(x => x.startsWith("a")))

  //mapvalues

  println(newPhoneBook.mapValues(num => num * 10))

  println(newPhoneBook.toList)
  println(List(("Bhaskar", 12)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
}
