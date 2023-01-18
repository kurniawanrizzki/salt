package com.noscale.salt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.noscale.salt.base.ViewBindingFragment
import com.noscale.salt.data.network.Resource
import com.noscale.salt.databinding.FragmentLoginBinding
import com.noscale.salt.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : ViewBindingFragment<FragmentLoginBinding>() {
    private val viewModel by viewModel<LoginViewModel>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun setup() {
        binding.vm = viewModel

        viewModel.isEmpty.observe(viewLifecycleOwner) {
            binding.btnLogin.isEnabled = !it
        }

        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect {
                when(it) {
                    is Resource.Failure -> {
                        val error = it.e.message ?: "Unknown Reason"
                        Snackbar.make(
                            binding.root,
                            error,
                            Snackbar.LENGTH_LONG
                        ).show()

                        binding.btnLogin.isEnabled = true
                    }
                    is Resource.Success -> {
                        val user = it.result
                        val action = LoginFragmentDirections.actionLoginToHome(user.email)
                        findNavController().navigate(action)
                    }
                    else -> {
                        binding.btnLogin.isEnabled = false
                    }
                }
            }
        }
    }
}