package com.noscale.salt.data.repository

import com.noscale.salt.data.models.User
import com.noscale.salt.data.network.Resource

interface IRepository {
    suspend fun postLogin(email: String, password: String): Resource<User>
}