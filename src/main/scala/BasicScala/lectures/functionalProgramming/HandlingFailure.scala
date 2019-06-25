package BasicScala.lectures.functionalProgramming

import com.sun.net.httpserver.Authenticator

import scala.util.{Failure, Random, Success, Try}


object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure"))

  println(aSuccess)
  println(aFailure)

  def unSafeMethod(): String = throw new RuntimeException("No String for you Stupid")

  //Try object with apply method
  val pFailure = Try(unSafeMethod())
  println(pFailure)

  //Syntaxt sugar
  val anotherFailure = Try {

  }

  println(pFailure.isSuccess)

  def backUpMethod(): String = "A valid result"

  val validationResult = Try(unSafeMethod()).orElse(Try(backUpMethod()))
  println(validationResult)

  def betterSafeMethod(): Try[String] = Failure(new RuntimeException("Runtime Exception"))

  def betterBackupMethod(): Try[String] = Success("A valid Resul")

  val validResult = betterSafeMethod() orElse betterBackupMethod()
  println(validResult)

  // map, flatMap, filter

  println(aSuccess.map(x => x * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))


  /**
    * Problem
    **/

  val hostName = "localhost"
  val port = "8080"

  def renderHtml(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>....</html"
      else throw new RuntimeException("Connection Lost")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConntion(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("port already connected")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConntion(host, port))
  }

  // if you get the html page from the connection, print it to the console i.e. call renderHTML

  val getConnection = HttpService.getSafeConnection(hostName, port)
  val possibleConnection = getConnection.flatMap(connection => connection.getSafe("/page"))
  possibleConnection.foreach(println)


  //for comphersion

  for{
    connection <- HttpService.getSafeConnection(hostName,port)
    html <- connection.getSafe("/url")
  }renderHtml(html)

}


