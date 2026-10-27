package com.aitor.recyclerview_aitor_mera

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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

        val nombres = arrayOf("Paquito", "Marcos", "Aitor", "Hector", "Raul",
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


        val customAdapter = ContactAdapter(contacts)

        val recyclerView: RecyclerView = findViewById(R.id.rvContacts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter


        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_contact, null)
            val etName: EditText = dialogView.findViewById(R.id.etName)
            val etPhone: EditText = dialogView.findViewById(R.id.etPhone)

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setTitle("Nuevo contacto")
                .setView(dialogView)
                .setPositiveButton("Añadir") { dialog, which ->
                    val name = etName.text.toString()
                    val phone = etPhone.text.toString()
                    contacts.add(Contact(name, phone))
                    customAdapter.notifyItemInserted(contacts.size - 1)
                    recyclerView.scrollToPosition(contacts.size - 1)
                }
                .setNegativeButton("Cancelar") { dialog, which ->
                    dialog.cancel()
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

}
