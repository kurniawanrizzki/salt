package com.noscale.salt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.noscale.salt.base.ViewBindingFragment
import com.noscale.salt.databinding.FragmentHomeBinding

class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun setup() = with(binding) {
        email = arguments?.getString("email")

        requireActivity().onBackPressedDispatcher.addCallback(this@HomeFragment, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val nav = findNavController()
                nav.navigateUp()
            }
        })
    }
}