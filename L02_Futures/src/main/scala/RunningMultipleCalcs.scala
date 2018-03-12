import scala.concurrent.{Future, future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random

object Cloud {

  def sleep(time:Long) = Thread.sleep(time)

  def runAlgorithm(i:Int): Future[Int] = future {
    sleep(Random.nextInt(500))
    val result = i + 10
    println(s"returning result from cloud: $result")
    result
  }
}

object RunningMultipleCalcs extends App {

  def sleep(time:Long) = Thread.sleep(time)

  println("Starting futures")

  val result1 = Cloud.runAlgorithm(10)
  val result2 = Cloud.runAlgorithm(20)
  val result3 = Cloud.runAlgorithm(30)

  println("Before for-comprehension")
  val result: Future[Int] = for {
    r1 <- result1
    r2 <- result2
    r3 <- result3
  } yield (r1 + r2 + r3)

  println("Before OnSuccess")

  result onComplete {
    case result => println(s"total = $result")
  }

  println("before sleep at the end")
  sleep(2000) // Important: keeps jvm alive
}
