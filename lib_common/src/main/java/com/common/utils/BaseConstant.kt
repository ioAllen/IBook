package com.common.utils

/**
 * author：WangLei
 * date:2018/5/23
 * QQ:619321796
 */
interface BaseConstant {

    /**
     * 请求响应码
     */
    interface ResponseCode {
        companion object {
            val OTHER_ERROR_CODE = -1
            val OK_CODE = 200
            val CAN_REGISTER = 0
        }
    }

    companion object {
        val appIp = "203.195.168.139"
    }
}
