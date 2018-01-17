import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Playground {

  def main(args: Array[String]): Unit = {

    val sc : SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("playground")
      .getOrCreate()

    import sc.implicits._

    val striped_data = sc.read
      .option("header",value = true)
      .option("inferSchema",value = true)
      .csv("src/main/resources/auto-mpg-cleaned.csv")
      .drop("carname", "origin")
      .as[Automobile]

    val ds = striped_data.as[Automobile]

    // Count how many vehicles have over 120 horsepower
    val count_120_hp = ds.filter("horsepower > 120").count()
    println("Number of models with over 120 horsepower: "+count_120_hp)

    // Display the data by count of horsepower

    val groupedHP = ds.groupBy("horsepower").count()

    // Average weight of vehicles with 8 cylinders
    val result = ds.select("weight").filter("cylinders = 8").agg(avg("weight")).first().get(0)
    println("Average weight of 8 cylinder vehicles: "+result)

    // Sum of all the weights
    val result_3 = ds.select("weight").agg(sum("weight")).first().get(0)
    println("Sum of all displacements: ", result_3)
  }
}
