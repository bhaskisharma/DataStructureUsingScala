package BasicScala.lectures.functionalProgramming

object MapFlatmapFilter extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  //filter
  println(list.filter(x => x % 2 == 0))

  //flatmap
  println(list.flatMap(x => List(x, x + 1)))

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')

  // List("a1", "a2"... "d4")

  val combine = numbers.flatMap(n => chars.map(x => "" + x + n))

  println(combine)

  val colors = List("black", "white")

  val combinations = numbers.flatMap(n => chars.flatMap(x => colors.map(y => "" + y + x + n)))
  println(combinations)

  val evenNumberCombine = numbers.filter(n => n % 2 == 0).flatMap(n => chars.flatMap(x => colors.map(y => "" + y + x + "-" + n)))
  println(evenNumberCombine)


  //for-comperhension

  val comphersion = for {
    n <- numbers
    chars <- chars
    colors <- colors
  } yield "" + n + chars + "-" + colors

  println(comphersion)


}
