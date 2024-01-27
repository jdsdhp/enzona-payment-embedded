package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource

import com.github.jdsdhp.enzona.payment.embedded.Enzona
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.util.post
import com.github.jdsdhp.enzona.payment.embedded.di.IoDispatcher
import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.AuthRemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.datasource.RemoteDatasource
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Scope
import com.github.jdsdhp.enzona.payment.embedded.domain.model.Token
import com.github.jdsdhp.enzona.payment.embedded.util.ResultValue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

internal class AuthRemoteDatasourceImpl @Inject constructor(
    private val okHttpClient: OkHttpClient,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val remoteDatasource: RemoteDatasource,
) : AuthRemoteDatasource {

    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun authenticate(
        consumerKey: String,
        consumerSecret: String,
    ): ResultValue<Token> = withContext(dispatcher) {
        remoteDatasource.call {
            val authHeader = "Basic ${Base64.encode("$consumerKey:$consumerSecret".toByteArray())}"
            val res = okHttpClient.post(
                fullUrl = "${Enzona.ApiUrl.OFFICIAL.url}/token",
                mediaTypeContent = "application/x-www-form-urlencoded",
                content = "grant_type=client_credentials&scope=${Scope.ENZONA_BUSINESS_PAYMENT.label}",
                headers = mapOf("Authorization" to authHeader),
            )
            val json = JSONObject(res.body?.string() ?: throw JSONException("Token parsing error."))
            val token = json.getString("access_token")
            val expiresIn = json.getInt("expires_in")
            val expireTime: Long = System.currentTimeMillis().plus(expiresIn)

            Token(accessToken = token, expireTime = expireTime)
        }
    }

}

