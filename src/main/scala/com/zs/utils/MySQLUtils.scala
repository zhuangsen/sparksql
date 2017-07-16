package com.zs.utils

import java.sql.{Connection, DriverManager, PreparedStatement}

/**
  * MySQL操作工具类
  */
object MySQLUtils {

  /**
    * 获取数据库连接
    * @return
    */
  def getConnection() = {
    DriverManager.getConnection("jdbc:mysql://localhost:3306/imooc_project?user=root&password=inserthome&useUnicode=true&characterEncoding=utf-8")
  }

  /**
    * 释放数据库连接等资源
    * @param connection
    * @param psmt
    */
  def release(connection: Connection, psmt: PreparedStatement): Unit = {
    try {
      if (psmt != null) {
        psmt.close()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (connection != null) {
        connection.close()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println(getConnection())
  }
}
