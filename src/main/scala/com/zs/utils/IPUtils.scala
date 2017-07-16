package com.zs.utils

import com.ggstar.util.ip.IpHelper

/**
  * IP工具解析类
  */
object IPUtils {

  def getCity(ip:String) ={
    IpHelper.findRegionByIp(ip)
  }

  def main(args: Array[String]): Unit = {
    println(getCity("58.30.15.255"))
  }
}
