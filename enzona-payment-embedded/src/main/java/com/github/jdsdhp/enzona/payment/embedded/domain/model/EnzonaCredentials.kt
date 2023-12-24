package com.github.jdsdhp.enzona.payment.embedded.domain.model

data class EnzonaCredentials(
    val apiUrl: ApiUrl,
    val merchantConsumerKey: String,
    val merchantConsumerSecret: String,
    //val autoRefreshToken: Boolean, //TODO: Pending for implementation.
    //val scopes: List<String>, //TODO: Pending for implementation.
) {

    enum class ApiUrl(val url: String) {
        OFFICIAL(url = "https://api.enzona.net"),
        SANDBOX(url = "https://apisandbox.enzona.net"),
    }

    //TODO: Pending for implementation.
    /*enum class AccessTokenUrl(val url: String) {
        OFFICIAL(url = "https://api.enzona.net/token"),
        SANDBOX(url = "https://apisandbox.enzona.net/token"),
    }*/

}
