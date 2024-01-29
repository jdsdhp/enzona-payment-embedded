/*
 * Copyright (c) 2024 jesusd0897.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource

import com.github.jdsdhp.enzona.payment.embedded.Enzona
import com.github.jdsdhp.enzona.payment.embedded.data.datasource.remote.datasource.util.post
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
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

internal class AuthRemoteDatasourceImpl(
    private val okHttpClient: OkHttpClient,
    private val dispatcher: CoroutineDispatcher,
    private val remoteDatasource: RemoteDatasource,
) : AuthRemoteDatasource {

    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun authenticate(
        apiUrl: Enzona.ApiUrl,
        consumerKey: String,
        consumerSecret: String,
    ): ResultValue<Token> = withContext(dispatcher) {
        remoteDatasource.call {
            val authHeader = "Basic ${Base64.encode("$consumerKey:$consumerSecret".toByteArray())}"
            val res = okHttpClient.post(
                fullUrl = "${apiUrl.url}/token",
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

