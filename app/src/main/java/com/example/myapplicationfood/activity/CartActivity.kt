package com.example.myapplicationfood.activity

import com.example.myapplicationfood.adapter.CartAdapter
import com.example.myapplicationfood.databinding.ActivityCartBinding

package com.example.project162.Activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVariable()
        calculateCart()
        initList()
    }

    private fun initList() {
        if (managmentCart.listCart.isEmpty()) {
            binding.emptyTxt.visibility = View.VISIBLE
            binding.scrollviewCart.visibility = View.GONE
        } else {
            binding.emptyTxt.visibility = View.GONE
            binding.scrollviewCart.visibility = View.VISIBLE
        }

        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cardView.layoutManager = linearLayoutManager
        adapter = CartAdapter(managmentCart.listCart, this) { calculateCart() }
        binding.cardView.adapter = adapter
    }

    private fun calculateCart() {
        val percentTax = 0.02 // percent 2% tax
        val delivery = 10.0 // 10 Dollar

        tax = (managmentCart.totalFee * percentTax * 100.0) / 100

        val total = (managmentCart.totalFee + tax + delivery * 100) / 100
        val itemTotal = (managmentCart.totalFee * 100) / 100

        binding.totalFeeTxt.text = "$" + itemTotal
        binding.taxTxt.text = "$" + tax
        binding.deliveryTxt.text = "$" + delivery
        binding.totalTxt.text = "$" + total
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}
