package com.example.android_fetchcodingexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrofit setup
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create the service interface
        val apiService = retrofit.create(ApiService::class.java)

        // Make the network request
        val call = apiService.getData()
        call.enqueue(object : Callback<List<DataItem>> {
            override fun onResponse(call: Call<List<DataItem>>, response: Response<List<DataItem>>) {
                if (response.isSuccessful) {
                    // Handle successful response
                    val dataItems: List<DataItem>? = response.body()
                    displayData(dataItems)
                } else {
                    // Handle error response
                    // You may want to show an error message or retry the request
                }
            }

            override fun onFailure(call: Call<List<DataItem>>, t: Throwable) {
                // Handle network failure
                // You may want to show an error message or retry the request
            }
        })
    }

    private fun displayData(dataItems: List<DataItem>?) {
        // Update your UI with the retrieved data
        val textView = findViewById<TextView>(R.id.tvList)
        textView.text = dataItems?.toString() ?: "Data is null"
    }
}