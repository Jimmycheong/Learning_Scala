import scala.util.{Failure, Success, Try}

object TryExample2 extends App {

  val someList = List("30","Hello","world","40")

  var newList = someList.map(e => Try(e.toInt)).map {
    case Success(e) => Option(e)
    case Failure(t) => None
  }

  var cleanedList = newList.flatMap(e => e).foreach(println)

}
