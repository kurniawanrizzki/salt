package com.noscale.salt.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <A,B> LiveData<A>.combine(other: LiveData<B>): MediatorLiveData<Pair<A?,B?>> {
    val mediator = MediatorLiveData<Pair<A?,B?>>()

    mediator.addSource(this) { mediator.value = it to other.value  }
    mediator.addSource(other) { mediator.value = value to it  }

    return mediator
}