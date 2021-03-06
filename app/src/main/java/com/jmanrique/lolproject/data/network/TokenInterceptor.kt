package com.jmanrique.lolproject.data.network

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {

    var token: String = ""
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()

        if (original.url.encodedPath.contains("/login") && original.method == "POST") {
            return chain.proceed(original)
        }

        val originalHttpUrl = original.url
        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", token)
            .url(originalHttpUrl)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}