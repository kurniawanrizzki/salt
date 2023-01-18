package com.noscale.salt.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("email")
    var email: String,
    @SerialName("password")
    var password: String,
    var token: String? = null
)
