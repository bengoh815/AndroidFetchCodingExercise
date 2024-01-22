package com.example.android_fetchcodingexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val LOGTAG = "MainActivity"
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
                    Log.i(LOGTAG, "Response successful")
                    val dataItems: List<DataItem>? = response.body()
                    displayData(filterAndSortData(dataItems))
                } else {
                    // Handle error response
                    Log.i(LOGTAG, "Response failure")
                }
            }

            override fun onFailure(call: Call<List<DataItem>>, t: Throwable) {
                // Handle network failure
                Log.i(LOGTAG, "Failed to get response")
            }
        })
    }

    private fun filterAndSortData(dataItems: List<DataItem>?): List<DataItem> {
        return sortDefault(filterNameNA(dataItems))
    }

    private fun filterNameNA(dataItems: List<DataItem>?): List<DataItem> {
        // Filter out items with blank or null names
        return dataItems?.filter { !it.name.isNullOrBlank() } ?: emptyList()
    }

    private fun sortDefault(dataItems: List<DataItem>): List<DataItem> {
        // Group items by listId and sort by listId and name
        return dataItems.groupBy { it.listId }
            .flatMap { (_, items) -> items.sortedWith(compareBy({ it.listId }, { it.name })) }
    }

    private fun displayData(dataItems: List<DataItem>?) {
        // Update your UI with the retrieved data
        val textView = findViewById<TextView>(R.id.tvList)
        textView.text = dataItems?.joinToString("\n") { "${it.listId}: ${it.name}" } ?: "Null Data"
    }
}