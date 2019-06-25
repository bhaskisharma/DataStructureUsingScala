package AdvancedScala.exercise

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random

object MiniSocialNetwork extends App {

  case class Profile(id: String, name: String) {
    def poke(anotherProfile: Profile): Unit = {
      println(s"${this.name} poking to ${anotherProfile.name}")
    }
  }

  object SocialNetwork {

    val names = Map(
      "fb.id.1-zuck" -> "Mark",
      "fb.id.2-bill" -> "Bill",
      "fb.id.0-dummy" -> "Dummy"
    )

    val friends = Map(
      "fb.id.1-zuck" -> "fb.id.2-bill"
    )

    val random = new Random()


    // API
    def fetchProfile(id: String): Future[Profile] = Future {
      Thread.sleep(random.nextInt(300))
      Profile(id, names(id))
    }

    def fetchBestFriend(profile: Profile): Future[Profile] = Future {
      Thread.sleep(random.nextInt(400))
      val friendId = friends(profile.id)
      Profile(friendId, names(friendId))
    }
  }

  // client: mark to poke bill
  val mark = SocialNetwork.fetchProfile("fb.id.1-zuck")
  mark.onComplete {
    case Success(markProfile) => {
      val bill = SocialNetwork.fetchBestFriend(markProfile)
      bill.onComplete {
        case Success(billProfile) => markProfile.poke(billProfile)
        case Failure(exception) => exception.printStackTrace()
      }
    }
    case Failure(exception) => exception.printStackTrace()
  }

  Thread.sleep(1000)


  // functional composition of futures
  // map, flatMap, filter

  val nameOFProfile = mark.map(profile => profile.name)
  val markBestFriend = mark.flatMap(profile => SocialNetwork.fetchBestFriend(profile))
  val zuckBestFriendRestricted = markBestFriend.filter(profile => profile.name.startsWith("z"))

  // for-comprehensions

  for {
    mark <- SocialNetwork.fetchProfile("fb.id.1-zuck")
    bill <- SocialNetwork.fetchBestFriend(mark)
  } yield mark.poke(bill)

  Thread.sleep(1000)


  // fallbacks

  val aProfile = SocialNetwork.fetchProfile("unknown-id").recover {
    case e: Throwable => Profile("fb-id-dummy", "Forever-Alone")
  }

  val aFetchProfile = SocialNetwork.fetchProfile("unknown-id").recoverWith {
    case e: Throwable => SocialNetwork.fetchProfile("fb-id-dummy")
  }

  val fallbackResult = SocialNetwork.fetchProfile("unknown-id").
    fallbackTo(SocialNetwork.fetchProfile("fb-id-dummy"))


}
