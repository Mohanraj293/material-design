package com.example.materialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


class RetrofitGet : AppCompatActivity() {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private lateinit var textViewResult:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_get)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toolbar: Toolbar = findViewById(R.id.AppBar)
        setSupportActionBar(toolbar)
        getPost()
    }
    private  fun getPost(){
        val retrofitbuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
        val retrofitdata = retrofitbuilder.getPosts()

        retrofitdata.enqueue(object : Callback<List<PostdataItem>?> {
            override fun onResponse(call: Call<List<PostdataItem>?>,response: Response<List<PostdataItem>?>) {
                val resp = response.body()!!

                val myBuilder = StringBuilder()
                for (myData in resp){
                    myBuilder.append("ID: "+myData.id)
                    myBuilder.append("\n")
                    myBuilder.append("Title: "+myData.title)
                    myBuilder.append("\n")
                    myBuilder.append("Body: "+myData.body)
                    myBuilder.append("\n")
                    myBuilder.append("\n")
                }
                val text = findViewById<TextView>(R.id.text_view_result)
                text.text = myBuilder

            }
            override fun onFailure(call: Call<List<PostdataItem>?>, t: Throwable) {
                Log.d("Retro Activity","OnFailure"+t.message)
            }
        })
    }
}