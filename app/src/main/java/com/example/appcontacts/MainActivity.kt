package com.example.appcontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.appcontacts.api.Contact
import com.example.appcontacts.api.ContactRequestApi
import com.example.appcontacts.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var listContacts:ArrayList<Contact>
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listContacts = ArrayList<Contact>()
        getContactsFromApi()
    }

         fun getContactsFromApi(){
        val api: ContactRequestApi = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ContactRequestApi::class.java)

        GlobalScope.launch(Dispatchers.Main){
            val res = api.getContacts().awaitResponse()
            if(res.isSuccessful){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity,"connect!",Toast.LENGTH_LONG).show()
                    listContacts.clear()
                    for (contact in res.body()!!.results){
                        listContacts.add(contact)
                        Log.i("BBBB", contact.name.title+". "+contact.name.last+ " "+contact.name.first)
                    }
                    binding.lsvContacts.isClickable = true
                    binding.lsvContacts.adapter = ContactAdapter(this@MainActivity,listContacts)
                    binding.lsvContacts.setOnItemClickListener { parent, view, position, id ->
                        val name = listContacts[position].name.last
                        val email = listContacts[position].email
                        val phone = listContacts[position].phone
                        val city = listContacts[position].location.city
                        val img = listContacts[position].picture.thumbnail
                        val intent = Intent(this@MainActivity, ContactActivity::class.java)
                        intent.putExtra("name",name)
                        intent.putExtra("email",email)
                        intent.putExtra("phone",phone)
                        intent.putExtra("address",city)
                        intent.putExtra("img",img)
                        startActivity(intent)
                    }
                }
            }else{
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity,"Cannot connect!",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}