package com.sky.mars.clustering

import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Main {

  def main(args: Array[String]): Unit = {

    val spark : SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("playground")
      .getOrCreate()

    val sc = spark.sparkContext
    sc.setLogLevel("ERROR")

    val data = spark.sparkContext.textFile("src/main/resources/K.txt")
    val parsedData: RDD[linalg.Vector] = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble))).cache()

    val errorsArray = new Array[Double](6)

    for (i <- 1 to 6) {
      val numClusters = i
      val numIterations = 20
      val clusters = KMeans.train(parsedData, numClusters, numIterations)

      // Evaluate clustering by computing Within Set Sum of Squared Errors
      val WSSSE = clusters.computeCost(parsedData)
      errorsArray(i-1) = WSSSE

    }

    errorsArray.foreach(println)

    // Save and load model
//    clusters.save(sc, "src/main/resources/KMeansModel")
//    val sameModel = KMeansModel.load(sc, "src/main/resources/KMeansModel")

//    val results = sameModel.predict(parsedData)


  }
}
