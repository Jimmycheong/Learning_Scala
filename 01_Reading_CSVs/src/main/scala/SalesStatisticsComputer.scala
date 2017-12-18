/**
  * Created by kch31 on 18/12/2017.
  */

class SalesStatisticsComputer(val salesReader: SalesReader){

  val sales = salesReader.readSales

  // Return the number of sales

  def getTotalNumberOfSales(): Int = sales size

  def getAvgSalePricesGroupedByPaymentType(): Map[String, Double] = {
    def avg(salesOfAPaymentType: Seq[Sale]) : Double =
      salesOfAPaymentType.map(_.price).sum / salesOfAPaymentType.size

    sales.groupBy(_.paymentType).mapValues(avg(_))
  }

  def getNumberOfSalesGroupedByDay(): Map[String, Int] = {
    def extractDay(sale: Sale): String = {
      val parts = sale.date.split("/")
      parts(0) + "/" + parts(1)
    }
    sales.groupBy(extractDay(_)).mapValues(_.length)
  }

  def getTotalNumberAndPriceOfSalesMadeAbroad(): (Int, Int) = {
    val filtered = sales.filter(_.country != "United States")
    (filtered.size, filtered.map(_.price).sum)
  }

}
