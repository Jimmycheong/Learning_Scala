import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.Encoders

case class Automobile (mpg: Double, cylinders: Int, displacement: Double, horsepower: Double, weight: Int, acceleration: Double, modelyear: Int)

object Main {

  def main(args: Array[String]): Unit = {
    val spark : SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("auto")
      .getOrCreate()

    import spark.implicits._

    val schema = Encoders.product[Automobile].schema

    val striped_data = spark.read
          .option("header",value = true)
          .option("inferSchema",value = true)
          .csv("src/main/resources/auto-mpg-cleaned.csv")
          .drop("carname", "origin")
          .as[Automobile]

    val inputs = striped_data.drop("mpg")
    val assembler = new VectorAssembler()
      .setInputCols(inputs.columns)
      .setOutputCol("features")

    val data_assembled_df = assembler.transform(striped_data)

    data_assembled_df.show()

    // Regression
    val lr = new LinearRegression()
      .setMaxIter(10)
      .setRegParam(0.3)
      .setElasticNetParam(0.8)
      .setLabelCol("mpg")

    // Fit the model
    val lrModel = lr.fit(data_assembled_df)

    //Testing

    var test_df = striped_data.where("horsepower = 150").where("weight = 3436").where("modelyear=70")

    val test_assembled_df = assembler.transform(test_df.drop("mpg"))

    val results = lrModel.transform(test_assembled_df)

    val predictedValue = results.select("prediction").head().getDouble(0)
    val actualValue = test_df.select("mpg").head().getDouble(0)
    val mpgRange = 37.6

    // Comparison
    println("Actual Value", actualValue)
    println("Predicted value",predictedValue)
    println("Error margin : " + (Math.abs(predictedValue - actualValue)/mpgRange)*100 + "%")

  }
}
