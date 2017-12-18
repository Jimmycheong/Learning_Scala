/**
  * Created by kch31 on 18/12/2017.
  */

trait SalesReader {
  def readSales(): Seq[Sale]
}

case class Sale(date: String, product: String, price:Int, paymentType:String, country: String)

