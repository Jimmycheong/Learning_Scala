import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Futures1 extends App {

  def sleep(time: Long) {
    Thread.sleep(time)
  }

  implicit val baseTime = System.currentTimeMillis()


  val f = Future {
    sleep(500)
    1 + 1
  }

  println("Starting..")

  val result = Await.result(f, 1 second)
  println(result)
  sleep(1000)

}
