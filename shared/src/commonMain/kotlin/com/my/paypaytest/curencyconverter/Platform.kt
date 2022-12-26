package com.my.paypaytest.curencyconverter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform