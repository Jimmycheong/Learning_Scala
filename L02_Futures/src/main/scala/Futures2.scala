import scala.concurrent.{Await, Future, future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Futures2 extends App {

  def sleep(i: Int) = Thread.sleep(i)

  implicit val baseTime = System.currentTimeMillis()

  def longRunningComputation(i:Int): Future[Int] = future {
    sleep(100)
    i + 1
  }

  // This does not block

  longRunningComputation(11).onComplete{
    case Success(result) => println(s"result = $result")
    case Failure(e) => e.printStackTrace
  }

  sleep(1000)

}
