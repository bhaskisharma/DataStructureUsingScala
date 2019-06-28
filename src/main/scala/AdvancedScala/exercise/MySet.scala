package AdvancedScala.exercise

trait MySet[A] extends (A => Boolean) {

  def apply(elem: A): Boolean =
    contain(elem)


  def contain(elem: A): Boolean

  def +(elem: A): MySet[A]

  def ++(elem: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B]

  def filter(pred: A => Boolean): MySet[A]

  def foreach(f: A => Unit): Unit

}

class EmptySet[A] extends MySet[A] {
  override def contain(elem: A): Boolean = false

  override def +(elem: A): MySet[A] = new NonEmptySet[A](elem, this)

  override def ++(anotherSetelem: MySet[A]): MySet[A] = anotherSetelem

  override def map[B](f: A => B): EmptySet[B] = new EmptySet[B]

  override def flatMap[B](f: A => MySet[B]): EmptySet[B] = new EmptySet[B]

  override def filter(pred: A => Boolean): MySet[A] = this

  override def foreach(f: A => Unit): Unit = ()
}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A] {
  override def contain(elem: A): Boolean = {
    elem == head || tail.contain(elem)
  }

  override def +(elem: A): MySet[A] = {
    if (this contain elem) this
    else new NonEmptySet[A](elem, this)
  }

  override def ++(anotherSetelem: MySet[A]): MySet[A] = tail ++ anotherSetelem + head

  override def map[B](f: A => B): MySet[B] = (tail map f) + f(head)

  override def flatMap[B](f: A => MySet[B]): MySet[B] = (tail flatMap f) ++ f(head)

  override def filter(predicate: A => Boolean): MySet[A] = {
    val filteredTail = tail filter predicate
    if (predicate(head)) filteredTail + head
    else filteredTail
  }

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail foreach f
  }
}

