package com.android.testclip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.testclip.data.remote.retrofit.models.WebService
import com.android.testclip.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //WebService.setupService("")
    }
}