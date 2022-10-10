package com.example.appcontacts

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.appcontacts.api.Contact

class ContactAdapter(private val context: Activity, private val arrayList: ArrayList<Contact>): ArrayAdapter<Contact>(context, R.layout.contact_item, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        return super.getView(position, convertView, parent)
        val inflayer:LayoutInflater = LayoutInflater.from(context)
        val view:View = inflayer.inflate(R.layout.contact_item,null)

        val ivAvatar:ImageView = view.findViewById(R.id.iv_avatar)
        val tvFullName:TextView = view.findViewById(R.id.tv_name)
        val tvPhone:TextView = view.findViewById(R.id.tv_phone)
        val tvEmail:TextView = view.findViewById(R.id.tv_email)
        val tvCity:TextView = view.findViewById(R.id.tv_city)

        tvFullName.text = arrayList[position].name.title + ". " +
            arrayList[position].name.last + " " + arrayList[position].name.first

        tvPhone.text = arrayList[position].phone
        tvEmail.text = arrayList[position].email
        tvCity.text = arrayList[position].location.city

        Glide.with(context).load(arrayList[position].picture.thumbnail).into(ivAvatar)

        return view
    }
}