package com.sky.mars.clustering

import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.SparkSession


object Playground {

  def main(args: Array[String]): Unit ={

  val spark : SparkSession = SparkSession
    .builder()
    .master("local")
    .appName("playground")
    .getOrCreate()

  val sc = spark.sparkContext

  val data = spark.sparkContext.textFile("src/main/resources/data.txt")
  val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble))).cache()

  parsedData.foreach(println)
  }
}
