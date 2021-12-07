package com.example.materialdesign


import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class DbActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toolbar: Toolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)
    }

    fun saveRecord(view: View){
        val u_id = findViewById<EditText>(R.id.u_id)
        val u_name = findViewById<EditText>(R.id.u_name)
        val u_email = findViewById<EditText>(R.id.u_email)
        val id = u_id.text.toString()
        val name = u_name.text.toString()
        val email = u_email.text.toString()
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        if(id.trim()!="" && name.trim()!="" && email.trim()!=""){
            val status = databaseHandler.addEmployee(UserModel(Integer.parseInt(id),name, email))
            if(status > -1){
                Toast.makeText(applicationContext,"Record saved",Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_name.text.clear()
                u_email.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"ID or name or email cannot be blank",Toast.LENGTH_LONG).show()
        }
    }
    fun viewRecord(view: View){
        val listView = findViewById<ListView>(R.id.listView)
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        val emp: List<UserModel> = databaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        val empArrayEmail = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail
            index++
        }
        val myListAdapter = MyListAdapter(this,empArrayId,empArrayName,empArrayEmail)
        listView.adapter = myListAdapter
    }
    fun updateRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId) as EditText
        val edtName = dialogView.findViewById(R.id.updateName) as EditText
        val edtEmail = dialogView.findViewById(R.id.updateEmail) as EditText

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = edtId.text.toString()
            val updateName = edtName.text.toString()
            val updateEmail = edtEmail.text.toString()
            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
            if(updateId.trim()!=""){
                val status = databaseHandler.updateEmployee(UserModel(Integer.parseInt(updateId),updateName, updateEmail))
                if(status > -1){
                    Toast.makeText(applicationContext,"Record updated",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"ID cannot be blank",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->  })
        val b = dialogBuilder.create()
        b.show()
    }
    fun deleteRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
            if(deleteId.trim()!=""){
                val status = databaseHandler.deleteEmployee(UserModel(Integer.parseInt(deleteId),"",""))
                if(status > -1){
                    Toast.makeText(applicationContext,"Record deleted",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"ID cannot be blank",Toast.LENGTH_LONG).show()
            }
        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->})
        val b = dialogBuilder.create()
        b.show()
    }
}