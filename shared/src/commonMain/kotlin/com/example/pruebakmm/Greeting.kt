package com.example.pruebakmm

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.core.*
import io.ktor.utils.io.charsets.*

class Greeting {
    val httpClient = HttpClient()
    val host = "https://dronesuite.itg.es/api"

    suspend fun greeting(): String {
        return "${getHello()}, ${Platform().platform}!"
    }

    suspend fun login(authorization:String): String {


        val response: HttpResponse = httpClient.get("${host}/auth/login"){
            headers {
                append(HttpHeaders.Authorization, "Basic $authorization")
            }
        }
        return response.readText()
    }

    private suspend fun getHello(): String {
        val response: HttpResponse = httpClient.get("https://my.api.mockaroo.com/prueba?key=538b7ef0")
        return response.readText()
    }
}