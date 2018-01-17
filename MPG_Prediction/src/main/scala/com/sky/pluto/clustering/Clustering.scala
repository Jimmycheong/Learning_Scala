package com.sky.pluto.clustering

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Clustering {

  def main(args: Array[String]): Unit = {

    val spark : SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("playground")
      .getOrCreate()

//    import spark.sparkContext.implicits._

    val striped_data = spark.read
      .option("header",value = true)
      .option("inferSchema",value = true)
      .csv("src/main/resources/auto-mpg-cleaned.csv")
      .drop("carname", "origin")

    val rdd_data = striped_data.rdd



  }
}
