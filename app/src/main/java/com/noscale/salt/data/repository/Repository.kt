package com.noscale.salt.data.repository

import com.noscale.salt.data.models.User
import com.noscale.salt.data.network.Constant
import com.noscale.salt.data.network.Resource
import io.ktor.client.*
import io.ktor.client.request.*

private const val LOGIN_URL = "${Constant.BASE_URL}/login"

class Repository(private val client: HttpClient) : IRepository {
    override suspend fun postLogin(email: String, password: String): Resource<User> = try {
        val user = User(email, password)

        val token = client.post<String> {
            url(LOGIN_URL)
            body = user
        }

        Resource.Success(
            user.apply { this.token = token }
        )
    } catch (e: Exception) {
        Resource.Failure(e)
    }
}