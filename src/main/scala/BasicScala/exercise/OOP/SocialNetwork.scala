package BasicScala.exercise.OOP

/** Overly simplified social network based on maps
  * Person = String
  *- add a person to the network
  *- remove
  *- friend (mutual)
  *- unfriend
  * *
  *
  *- number of friends of a person
  *- person with most friends
  *- how many people have NO friends
  *- if there is a social connection between two people (direct or not)
  */

object SocialNetwork extends App {

  def add(network :Map[String,Set[String]], person:String) : Map[String, Set[String]] = {
    network + (person -> Set())
  }

  def friend(network :Map[String,Set[String]], a :String, b: String) :  Map[String, Set[String]] = {
    val friendA = network(a)
    val friendB = network(b)

    network + (a  -> (friendA + b)) + (b -> (friendB + a))
  }

  def unfriend(network :Map[String,Set[String]], a :String, b: String) : Map[String, Set[String]] = {
    val friendA = network(a)
    val friendB = network(b)

    network + (a  -> (friendA - b)) + (b -> (friendB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")

  println(network)
  println(friend(network, "Bob", "Mary"))

  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim,Bob,Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)


  def nFriend(network : Map[String, Set[String]],person: String) : Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }
  println(nFriend(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

}
