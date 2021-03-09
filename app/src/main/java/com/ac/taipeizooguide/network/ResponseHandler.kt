package com.ac.taipeizooguide.network

import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * Created on 2021/3/9.
 */

open class ResponseHandler {

    fun <T : Any> handleSuccess(data: T?): Response<T> {
        return Response.success(data)
    }

    fun <T : Any> handleError(e: Exception): Response<T> {
        return when (e) {
            is HttpException -> {
                Response.error("發生錯誤，代碼: ${e.code()}", null)
            }
            is SocketTimeoutException -> {
                Response.error("Socket Timeout", null)
            }
            else -> {
                Response.error("發生錯誤，請稍後重試。", null)
            }
        }
    }

}