package com.example.appcontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.appcontacts.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")
        val address = intent.getStringExtra("address")
        val img = intent.getStringExtra("img")

        binding.tvName.text = name
        binding.tvAddress.text = address
        binding.tvPhone.text = phone
        binding.tvEmail.text = email

        Glide.with(this).load(img).into(binding.ivAvatar)
    }
}