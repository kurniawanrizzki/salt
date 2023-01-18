package com.noscale.salt.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noscale.salt.data.models.User
import com.noscale.salt.data.network.Resource
import com.noscale.salt.data.repository.IRepository
import com.noscale.salt.utils.combine
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: IRepository) : ViewModel() {
    private val eventChannel = Channel<Resource<User>>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    private val _email = MutableLiveData<String?>()
    val email = _email

    private val _password = MutableLiveData<String?>()
    val password = _password

    val isEmpty: LiveData<Boolean> = Transformations.map(email.combine(password)) {
        it?.first.isNullOrEmpty() || it.second.isNullOrEmpty()
    }

    fun onEmailTextChanged(email: CharSequence) {
        _email.value = email.toString()
    }

    fun onPasswordTextChanged(password: CharSequence) {
        _password.value = password.toString()
    }

    fun onLogin() {
        loading()
        viewModelScope.launch {
            val email = email.value ?: ""
            val password = password.value ?: ""
            eventChannel.send(repository.postLogin(email, password))
        }
    }

    private fun loading() {
        viewModelScope.launch {
            eventChannel.send(Resource.Loading)
        }
    }
}