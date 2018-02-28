name := "MPG_Prediction"

version := "0.1"

scalaVersion := "2.11.12"

mainClass in (Compile,run) := Some("Main")


libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-client" % "2.7.2",
  "org.apache.spark" %% "spark-sql" % "2.1.2",
  "org.apache.spark" %% "spark-mllib" % "2.1.2"
)