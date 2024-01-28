package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.util

import android.annotation.SuppressLint
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Executes an HTTP POST request using OkHttpClient.
 * @param fullUrl The full URL for the POST request.
 * @param mediaTypeContent The media type of the request content (e.g., "application/json").
 * @param content The content of the request.
 * @param headers A map of headers to be included in the request.
 * @return The response of the POST request.
 */
internal fun OkHttpClient.post(
    fullUrl: String,
    mediaTypeContent: String,
    content: String,
    headers: Map<String, String>,
): Response {
    val mediaType: MediaType? = mediaTypeContent.toMediaTypeOrNull()
    val body: RequestBody = content.toRequestBody(mediaType)
    val request: Request = Request.Builder()
        .url(fullUrl)
        .post(body)
        .apply { headers.forEach { addHeader(it.key, it.value) } }
        .build()
    return newCall(request).execute()
}

/**
 * Executes an HTTP GET request using OkHttpClient.
 * @param fullUrl The full URL for the GET request.
 * @param headers A map of headers to be included in the request.
 * @return The response of the GET request.
 */
internal fun OkHttpClient.get(
    fullUrl: String,
    headers: Map<String, String>,
): Response {
    val request: Request = Request.Builder()
        .url(fullUrl)
        .apply { headers.forEach { addHeader(it.key, it.value) } }
        .build()
    return newCall(request).execute()
}

/**
 * Configures OkHttpClient.Builder to ignore SSL/TLS certificates.
 * Use with caution and only in scenarios where certificate validation is not required.
 * @return The modified OkHttpClient.Builder.
 */
internal fun OkHttpClient.Builder.ignoreCertificates(): OkHttpClient.Builder {
    // Create a trust manager that does not validate certificate chains
    val trustAllCerts = arrayOf<TrustManager>(
        @SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) = Unit

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) = Unit

            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        }
    )
    // Install the all-trusting trust manager
    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, SecureRandom())
    // Create an SSL socket factory with our all-trusting manager
    val sslSocketFactory = sslContext.socketFactory
    sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
    hostnameVerifier { _, _ -> true }
    return this
}