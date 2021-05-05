package com.sahrial.submission_3_jetpackpro.data.source.control.status

class ResponseAPI<T>(val status: ResponseStatus, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T): ResponseAPI<T> = ResponseAPI(ResponseStatus.SUCCESS, body, null)

        fun <T> error(msg: String, body: T): ResponseAPI<T> = ResponseAPI(ResponseStatus.ERROR, body, msg)
    }
}