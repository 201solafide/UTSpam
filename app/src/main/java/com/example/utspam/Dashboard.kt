package com.example.utspam

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
//import kotlinx.android.synthetic.main.activity_dashboard.*
import androidx.databinding.DataBindingUtil
import com.example.utspam.databinding.ActivityDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class Dashboard : AppCompatActivity() {
    private lateinit var userAdapter: UserAdapter
    private lateinit var apiService: ApiService
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding =
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter()
        binding.recyclerViewUsers.adapter = userAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        fetchData()

        binding.searchViewUsers.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                userAdapter.filter.filter(newText)
                return false
            }
        })

    }
    private fun fetchData(){
        apiService.getUser(1).enqueue(object : Callback<ApiResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if(response.isSuccessful){
                    val user = response.body()?.data ?: emptyList()
                    userAdapter = UserAdapter()
                    userAdapter.updateData(user)
                    userAdapter.notifyDataSetChanged()
                    binding.recyclerViewUsers.adapter = userAdapter
                }else{
                    Toast.makeText(this@Dashboard, "Fail fetch Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@Dashboard, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}