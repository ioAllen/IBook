package com.common.utils

/**
 * author：WangLei
 * date:2018/5/23
 * QQ:619321796
 */
interface CommonConstant {

    companion object {
        const val biquge = "http://www.biqiuge.com/"

        const val appIp="118.24.53.144"
    }


    /**
     * 请求响应码
     */
    interface ResponseCode {
        companion object {
            const val OTHER_ERROR_CODE = -1
            const val OK_CODE = 200
            const val RESOURCES_DO_NOT_EXIST = 404
        }
    }
}
