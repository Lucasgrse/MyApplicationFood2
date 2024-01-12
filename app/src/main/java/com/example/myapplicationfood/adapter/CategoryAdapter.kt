package com.example.myapplicationfood.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationfood.Domain.Category
import com.example.myapplicationfood.R
import com.example.myapplicationfood.activity.ListFoodsActivity

class CategoryAdapter(private val items: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTxt.text = items[position].name

        val resourceId = when (position) {
            0 -> R.drawable.cat_0_background
            1 -> R.drawable.cat_1_background
            2 -> R.drawable.cat_2_background
            3 -> R.drawable.cat_3_background
            4 -> R.drawable.cat_4_background
            5 -> R.drawable.cat_5_background
            6 -> R.drawable.cat_6_background
            7 -> R.drawable.cat_7_background
            else -> 0
        }

        if (resourceId != 0) {
            holder.pic.setBackgroundResource(resourceId)
        }

        val drawableResourceId = context.resources.getIdentifier(
            items[position].imagePath,
            "drawable",
            holder.itemView.context.packageName
        )

        Glide.with(context)
            .load(drawableResourceId)
            .into(holder.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ListFoodsActivity::class.java)
            intent.putExtra("CategoryId", items[position].id)
            intent.putExtra("CategoryName", items[position].name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.catNameTxt)
        val pic: ImageView = itemView.findViewById(R.id.imgCat)
    }
}
