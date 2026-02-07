package com.example.form2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform