package com.test.ooktask.model

data class ResponseBody<T>(
    val status: String = "",
    val message: String = "",
    val data: T
)
