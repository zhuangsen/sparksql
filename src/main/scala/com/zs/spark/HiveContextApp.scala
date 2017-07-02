package com.zs.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext

object HiveContextApp {

  def main(args: Array[String]): Unit = {
    //    1)创建相应的Context
    val sparkConf = new SparkConf()

    //在测试或生产中，AppName和Master我们是通过脚本进行指定: --name --master
//    sparkConf.setAppName("SQLContextApp").setMaster("local[2]")

    val sc = new SparkContext(sparkConf)
    val hiveContext = new HiveContext(sc)

    //2)相关的处理:json
    hiveContext.table("emp").show()


    //3)关闭资源
    sc.stop()
  }
}
