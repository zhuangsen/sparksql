package com.zs.spark

import org.apache.spark.sql.SparkSession

/**
  * DataFrame和RDD的互操作
  */
object DataFrameRDDApp {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()

    // RDD ==> DataFrame
    val rdd = spark.sparkContext.textFile("file:///home/madison/data/student.data")

    //注意：需要导入隐式转换
    import spark.implicits._
    val studentDF = rdd.map(_.split("\\|")).map(line => Student(line(0).toInt, line(1), line(2), line(3))).toDF()

    studentDF.show()
    studentDF.show(30)
    studentDF.show(30, false)

    studentDF.filter(studentDF.col("id")>15).show()

    studentDF.createOrReplaceTempView("students")
    spark.sql("select * from students where id > 15").show()

    spark.stop()
  }

  case class Student(id: Int, name: String, phone: String, email: String)
}
