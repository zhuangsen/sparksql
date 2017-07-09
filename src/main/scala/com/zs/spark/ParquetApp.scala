package com.zs.spark

import org.apache.spark.sql.SparkSession

/**
  * Parquet文件操作
  */
object ParquetApp {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("SparkSessionApp")
      .master("local[2]").getOrCreate()

    val path = "file:///home/madison/app/spark-bin-2.6.0-cdh5.7.0/examples/src/main/resources/users.parquet"

    /**
      *spark.read.format("parquet").load(path)是标准写法
      */
    val df = spark.read.format("parquet").load(path)

    df.printSchema()
    df.show()

    df.select("name","favorite_color").show()

    df.select("name","favorite_color").write.format("json").save("file:///home/madison/data/tmp/jsonout")


    //会报错，因为sparksql默认处理的format就是parquet
    spark.read.load("file:///home/madison/app/spark-bin-2.6.0-cdh5.7.0/examples/src/main/resources/people.json").show()


    spark.read.format("parquet").option("path",path).load().show()


    spark.stop()
  }

}
