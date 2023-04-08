package com.example.midterm__2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.midterm__2.ui.theme.Midterm__2Theme
import androidx.recyclerview.widget.RecyclerView



class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        setContent {
            Midterm__2Theme {
                // A surface container using the 'background' color from the theme

            }
        }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val url = "https://restcountries.com/v3.1/all"
        val queue = Volley.newRequestQueue(this)

        val countries = mutableListOf<Country>()

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    val name = item.getJSONObject("name").getString("official")
                    val subregion = item.getString("subregion")
                    val country = Country(name, subregion)
                    countries.add(country)
                }
                recyclerView.adapter = CountryAdapter(countries)
            },
            { error -> Log.e("TAG", "Error: ${error.message}") }
        )

        queue.add(request)

    }
}
