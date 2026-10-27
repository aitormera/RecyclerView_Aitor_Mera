package com.aitor.recyclerview_aitor_mera

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val contacts = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombres = arrayOf("Paquito", "Marcos", "Aitor", "Hector", "Miguel",
            "Ivan", "Paula", "Miguel", "Unai", "Marc")
        val apellidos = arrayOf("Mera", "Muriel", "Martinez", "Fernandez", "Gomez",
            "Grajea", "Romero", "Soler", "Torres", "Roca")

        for (i in 1..40) {
            val nombre= nombres.random()
            val apellido=apellidos.random()
            val name = nombre+" "+apellido
            val phone = (100000000..999999999).random().toString()
            contacts.add(Contact(name, phone))
        }


        val rvContacts = findViewById<RecyclerView>(R.id.rvContacts)
        rvContacts.layoutManager = LinearLayoutManager(this)
        rvContacts.adapter = ContactAdapter(contacts)
    }
}
data class Contact(val name: String, val phone: String)