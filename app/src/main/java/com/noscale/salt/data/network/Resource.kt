package com.noscale.salt.data.network

sealed class Resource<out R> {
    object Loading: Resource<Nothing>()
    data class Success<out R>(val result: R): Resource<R>()
    data class Failure(val e: Exception): Resource<Nothing>()
}
