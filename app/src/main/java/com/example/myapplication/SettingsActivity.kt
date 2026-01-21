package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.switchLiquidGlass.setOnCheckedChangeListener { _, isChecked ->
            // TODO: handle liquid glass switch
        }

        binding.switchDynamicGradient.setOnCheckedChangeListener { _, isChecked ->
            // TODO: handle dynamic gradient switch
        }
    }
}
