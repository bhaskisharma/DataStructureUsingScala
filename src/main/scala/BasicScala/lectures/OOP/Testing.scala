package BasicScala.lectures.OOP

trait A {
  def message()

}

trait B {

  def message()
}

class Testing extends A with B{
  override def message(): Unit = ???
}
