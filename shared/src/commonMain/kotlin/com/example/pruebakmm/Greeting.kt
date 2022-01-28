package com.example.pruebakmm

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Greeting {
    val httpClient = HttpClient()

    suspend fun greeting(): String {
        return "${getHello()}, ${Platform().platform}!"
    }

    private suspend fun getHello(): String {
        val response: HttpResponse = httpClient.get("https://my.api.mockaroo.com/prueba?key=538b7ef0")
        return response.readText()
    }
}