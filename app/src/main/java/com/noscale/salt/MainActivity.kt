package com.noscale.salt

import android.view.LayoutInflater
import com.noscale.salt.base.ViewBindingActivity
import com.noscale.salt.databinding.ActivityMainBinding

class MainActivity : ViewBindingActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setup() = with(binding) {}
}