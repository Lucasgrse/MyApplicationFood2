package com.example.myapplicationfood.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.myapplicationfood.Domain.Foods


class CartAdapter(private val list: ArrayList<Foods>, private val context: Context, private val changeNumberItemsListener: ChangeNumberItemsListener) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private val managmentCart: ManagmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.feeEachItem.text = "$" + (list[position].numberInCart * list[position].price)
        holder.totalEachItem.text = list[position].numberInCart.toString() + " * $" + (list[position].price)
        holder.num.text = list[position].numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(list[position].imagePath)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.pic)

        holder.plusItem.setOnClickListener {
            managmentCart.plusNumberItem(list, position) {
                notifyDataSetChanged()
                changeNumberItemsListener.change()
            }
        }

        holder.minusItem.setOnClickListener {
            managmentCart.minusNumberItem(list, position) {
                notifyDataSetChanged()
                changeNumberItemsListener.change()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTxt)
        val pic: ImageView = itemView.findViewById(R.id.pic)
        val feeEachItem: TextView = itemView.findViewById(R.id.feeEachItem)
        val plusItem: TextView = itemView.findViewById(R.id.plusCartBtn)
        val minusItem: TextView = itemView.findViewById(R.id.minusCartBtn)
        val totalEachItem: TextView = itemView.findViewById(R.id.totalEachItem)
        val num: TextView = itemView.findViewById(R.id.numberItemTxt)
    }
}
