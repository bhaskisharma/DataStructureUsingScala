package BasicScala.lectures.functionalProgramming

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(2)
  val noOption: Option[Int] = None

  println(myFirstOption)

  def unSafeMethod(): String = null

  val result = Some(unSafeMethod())
  println(result)


  // chained methods
  def backupMethod(): String = "A valid result"

  val chainedResult = Option(unSafeMethod()).orElse(Option(backupMethod()))


  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // USAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))


  //Problem

  val config: Map[String, String] = Map(
    "host" -> "10.20.30.2",
    "port" -> "8080"
  )

  class Connection {
    def connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }


  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")

  val connection = host.flatMap(x => port.flatMap(y => Connection.apply(x,y)))

  val connectionStatus = connection.map(x => x.connect)
  println(connectionStatus)

  connectionStatus.foreach(println)

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}


