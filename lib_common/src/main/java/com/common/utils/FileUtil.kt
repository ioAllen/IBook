package com.common.utils

import java.text.DecimalFormat

/**
 * author：WangLei
 * date:2018/6/1
 * QQ:619321796
 */
object FileUtil {
    fun loadFileSize(size: Long): String {
        //获取到的size为：1705230
        val GB = 1024 * 1024 * 1024//定义GB的计算常量
        val MB = 1024 * 1024//定义MB的计算常量
        val KB = 1024//定义KB的计算常量
        val df = DecimalFormat("0.00")//格式化小数
        var resultSize = ""
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = df.format(size / GB.toFloat()) + "GB"
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = df.format(size / MB.toFloat()) + "MB"
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = df.format(size / KB.toFloat()) + "KB"
        } else {
            resultSize = size.toString() + "B"
        }
        return resultSize
    }
}