package com.zs.spark

import com.zs.utils.DateUtils
import org.apache.spark.sql.SparkSession

/**
  * 第一步清洗：抽取出我们所需要额指定列的数据
  */
object SparkStatFormatJob {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("SparkStatFormatJob")
      .master("local[2]").getOrCreate()

    val access = spark.sparkContext.textFile("file:///home/madison/data/10000_access.log")

    access.take(10).foreach(println)

    access.map(line => {
      val splits = line.split(" ")
      val ip = splits(0)
      val time = splits(3) + " " + splits(4)

      val url = splits(11).replaceAll("\"", "")
      val traffic = splits(9)
      //      (ip, DateUtils.parse(time), url, traffic)
      DateUtils.parse(time) + "\t" + url + "\t" + traffic + "\t" + ip
    }).saveAsTextFile("file:///home/madison/data/tmp/output")

    spark.stop()
  }

}
