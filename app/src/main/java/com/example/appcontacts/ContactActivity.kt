package com.example.appcontacts

import android.R.attr.phoneNumber
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        val latitude = intent.getStringExtra("latitude")
        val longitude =intent.getStringExtra("longitude")

        binding.tvName.text = name
        binding.tvAddress.text = address
        binding.tvPhone.text = phone
        binding.tvEmail.text = email

        Glide.with(this).load(img).into(binding.ivAvatar)

        //Gọi điện thoại
        binding.tvPhone.setOnClickListener {
//            Toast.makeText(this,phone,Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + phone)
//            if (intent.resolveActivity(packageManager) != null) {
//                startActivity(intent)
//            }
            startActivity(intent)
        }

        //Gọi google map
        binding.tvAddress.setOnClickListener {
            val mapIntent: Intent = Uri.parse(
                "geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"
            ).let { location ->
                // Or map point based on latitude/longitude
//                 val location: Uri = Uri.parse("geo:${latitude},${longitude}") // z param is zoom level
                val location: Uri = Uri.parse("geo:10.0308541,105.768986") // z param is zoom level
                Intent(Intent.ACTION_VIEW, location)
            }
            startActivity(mapIntent)
        }
    }
}