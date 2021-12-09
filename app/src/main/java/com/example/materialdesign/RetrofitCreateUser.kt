package com.example.materialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_retrofit_create_user.*

class RetrofitCreateUser : AppCompatActivity() {
    lateinit var viewModel: CreateNewUserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_create_user)
        val user_id = intent.getStringExtra("user_id")
        initViewModelModel()
        createUserObservable()
        if(user_id!=null){
            loadUserData(user_id)
        }
        createButton.setOnClickListener {
            createUser(user_id)
        }
        deleteButton.setOnClickListener {
            deleteUser(user_id)
        }
    }
    private fun deleteUser(user_id:String?){
        viewModel.getDeleteUserObservable().observe(this, Observer<UserResponse?> {
            if(it!=null){
                Toast.makeText(this@RetrofitCreateUser,"Failed to delete", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this@RetrofitCreateUser,"Successfully Deleted", Toast.LENGTH_LONG).show()
                finish()
            }
        })
        viewModel.deleteUser(user_id)
    }

    private fun loadUserData(user_id:String){
        viewModel.getLoadUserObservable().observe(this, Observer<UserResponse?> {
            if(it!=null){
                editTextName.setText(it.data?.name)
                editTextEmail.setText(it.data?.email)
                createButton.setText("Update User")
                deleteButton.visibility = View.VISIBLE
            }
        })
        viewModel.getUserData(user_id)
    }

    private fun createUser(user_id:String?){
        val user = User("",editTextName.text.toString(),editTextEmail.text.toString(),"Male","Active")
        if (user_id==null)
            viewModel.createUser(user)
        else
            viewModel.updateUser(user_id,user)

    }

    private fun initViewModelModel(){
        viewModel = ViewModelProvider(this).get(CreateNewUserModel::class.java)
    }
    private fun createUserObservable(){
        viewModel.getCreateNewUserObservable().observe(this, Observer<UserResponse?> {
            if(it==null){
                Toast.makeText(this@RetrofitCreateUser,"Failed to create/update", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this@RetrofitCreateUser,"Successfully created/Updated", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }
}