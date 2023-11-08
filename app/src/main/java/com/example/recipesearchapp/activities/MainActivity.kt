package com.example.recipesearchapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipesearchapp.R
import com.example.recipesearchapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}