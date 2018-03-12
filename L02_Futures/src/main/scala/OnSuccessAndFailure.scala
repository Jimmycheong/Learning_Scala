import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}

object OnSuccessAndFailure extends App {

  def sleep(time: Long) = Thread.sleep(time)

  println("starting calculation...")

  val f = Future {
    sleep(Random.nextInt(500))
    if (Random.nextInt(500) > 250) throw new Exception("Yikes!")
  }

  f onComplete  {
    case Success(result) => println(s"Success: $result")
    case Failure(t) => println(s"Exception: ${t.getMessage}")
  }

  // Do the rest of the work
  println("A..."); sleep(100)
  println("B..."); sleep(100)
  println("C..."); sleep(100)
  println("D..."); sleep(100)
  println("E..."); sleep(100)
  println("F..."); sleep(100)
  println("G..."); sleep(100)

}
