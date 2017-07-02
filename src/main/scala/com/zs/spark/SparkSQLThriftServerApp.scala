package com.zs.spark

import java.sql.DriverManager

/**
  *  通过JDBC的方式访问
  */
object SparkSQLThriftServerApp {

  def main(args: Array[String]): Unit = {
    Class.forName("org.apache.hive.jdbc.HiverDriver")

    val conn = DriverManager.getConnection("jdbc:hive2://hadoop001:14000","hadoop","")
    val pstm = conn.prepareStatement("select empno, ename, sal from emp")

    val rs = pstm.executeQuery()
    while (rs.next()){
      while (rs.next()) {
        println("empno:" + rs.getInt("empno") +
          " , ename:" + rs.getString("ename") +
          " , sal:" + rs.getDouble("sal"))

      }

      rs.close()
      pstm.close()
      conn.close()
    }
  }
}
