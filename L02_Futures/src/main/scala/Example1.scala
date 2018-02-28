import scala.concurrent.{Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random

object Example1 extends App {

  def sleep(time:Long) = Thread.sleep(time)

  println("starting calculation...")

  val f = Future {
    sleep(Random.nextInt(500))
    42
  }

  println("beforeOnComplete")

  f.onComplete {
    case Success(value) => println(s"Got the callback, meaning = $value")
    case Failure(e) => e.printStackTrace
  }

  println("A..."); sleep(100)
  println("B..."); sleep(100)
  println("C..."); sleep(100)
  println("D..."); sleep(100)
  println("E..."); sleep(100)
  println("F..."); sleep(100)
  println("G..."); sleep(100)

}
