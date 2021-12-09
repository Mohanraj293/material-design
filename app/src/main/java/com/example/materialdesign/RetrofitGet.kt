package com.example.materialdesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_retrofit_create_user.*
import kotlinx.android.synthetic.main.activity_retrofit_get.*


class RetrofitGet : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: RetrofitViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_get)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toolbar: Toolbar = findViewById(R.id.AppBar)
        setSupportActionBar(toolbar)
        initRecyclerView()
        initViewModel()
        searchUser()
        createUserButton.setOnClickListener{
            startActivity(Intent(this@RetrofitGet,RetrofitCreateUser::class.java))
        }
    }

    private fun initRecyclerView(){
        recylerView.apply {
            layoutManager = LinearLayoutManager(this@RetrofitGet)
            val decoration = DividerItemDecoration(this@RetrofitGet,DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@RetrofitGet)
            adapter = recyclerViewAdapter
        }
    }
    private fun searchUser(){
        search_button.setOnClickListener{
            if(!TextUtils.isEmpty(searchEditText.text.toString())){
                viewModel.searchUser(searchEditText.text.toString())
            }else{
                viewModel.getUserList()
            }
        }
    }
    fun initViewModel(){
        viewModel=ViewModelProvider(this).get(RetrofitViewModel::class.java)
        viewModel.getUserListObservalble().observe(this, Observer<UserList> {
            if (it == null){
                Toast.makeText(this@RetrofitGet,"No results Found",Toast.LENGTH_SHORT)
            }
            else{
                recyclerViewAdapter.userList = it.data.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getUserList()
    }

    override fun onItemEditClick(user: User) {
        val intent = Intent(this@RetrofitGet,RetrofitCreateUser::class.java)
        intent.putExtra("user_id",user.id)
        startActivityForResult(intent,1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==1000){
            viewModel.getUserList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}