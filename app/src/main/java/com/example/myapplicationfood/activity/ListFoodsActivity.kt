package com.example.myapplicationfood.activity

import android.app.DownloadManager
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationfood.Domain.Foods
import com.example.myapplicationfood.databinding.ActivityListFoodsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class ListFoodsActivity : BaseActivity() {
    private lateinit var binding: ActivityListFoodsBinding
    private lateinit var adapterListFood: RecyclerView.Adapter<*>
    private var categoryId = 0
    private var categoryName: String? = null
    private var searchText: String? = null
    private var isSearch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListFoodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        initList()
        setVariable()
    }

    private fun setVariable() {
        // You can add code here if needed
    }

    private fun initList() {
        val myRef = database.getReference("Foods")
        binding.progressBar.visibility = View.VISIBLE
        val list: ArrayList<Foods> = ArrayList()

        val query: DownloadManager.Query = if (isSearch) {
            myRef.orderByChild("Title").startAt(searchText).endAt(searchText + '\uf8ff')
        } else {
            myRef.orderByChild("CategoryId").equalTo(categoryId.toDouble())
        }

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Foods::class.java)!!)
                    }
                    if (list.size > 0) {
                        binding.foodListView.layoutManager = GridLayoutManager(this@ListFoodsActivity, 2)
                        adapterListFood = FoodListAdapter(list)
                        binding.foodListView.adapter = adapterListFood
                    }
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error if needed
            }
        })
    }

    private fun getIntentExtra() {
        categoryId = intent.getIntExtra("CategoryId", 0)
        categoryName = intent.getStringExtra("CategoryName")
        searchText = intent.getStringExtra("text")
        isSearch = intent.getBooleanExtra("isSearch", false)

        binding.titleTxt.text = categoryName
        binding.backBtn.setOnClickListener { finish() }
    }
}
