package BasicScala.lectures.functionalProgramming

object Functions extends App {

  val doubler: MyFunctions[Int, Int] = new MyFunctions[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  val strintToIntConverter: String => Int = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(strintToIntConverter("3") +4)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2 : Int): Int = v1 + v2
  }
  println(adder(10,20))

  //ALL Scala function are object
  //Truly Pure Object Oriented Lang

  /*
   1.  a function which takes 2 strings and concatenates them
   2.  transform the MyPredicate and MyTransformer into function types
   3.  define a function which takes an int and returns another function which takes an int and returns an int
       - what's the type of this function
       - how to do it
  */


  val concatTwoString: (String,String) => String = new Function2[String,String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(concatTwoString("Hello", "How"))

  val fun1: Int => Int => Int = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int,Int]  = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }

  println(fun1(10)(220)) //curied function

}

trait MyFunctions[A, B] {
  def apply(element: A): B
}