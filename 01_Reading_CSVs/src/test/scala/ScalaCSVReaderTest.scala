/**
  * Created by kch31 on 18/12/2017.
  */


import org.scalatest.FunSuite
import org.scalatest.Matchers._

class ScalaCSVReaderTest extends FunSuite {

  test("Load CSV file") {
    val sales = new SalesCSVReader("src/main/resources/salesData.csv").readSales

    sales.size shouldBe 50

    sales(0) shouldBe Sale("1/2/09 6:17", "Product1", 1200, "Mastercard", "United Kingdom")

  }

}
