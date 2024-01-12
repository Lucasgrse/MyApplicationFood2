package com.example.myapplicationfood.helper

import android.content.Context
import android.widget.Toast
import com.example.myapplicationfood.Domain.Foods

class ManagementCart(private val context: Context) {
    private val tinyDB = TinyDB(context)

    fun insertFood(item: Foods) {
        val listpop = getListCart()
        var existAlready = false
        var n = 0

        for (i in listpop.indices) {
            if (listpop[i].title == item.title) {
                existAlready = true
                n = i
                break
            }
        }

        if (existAlready) {
            listpop[n].numberInCart = item.numberInCart
        } else {
            listpop.add(item)
        }

        tinyDB.putListObject("CartList", listpop)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<Foods> {
        return tinyDB.getListObject("CartList")
    }

    fun getTotalFee(): Double {
        val listItem = getListCart()
        var fee = 0.0

        for (i in listItem.indices) {
            fee += listItem[i].price * listItem[i].numberInCart
        }

        return fee
    }

    fun minusNumberItem(
        listItem: ArrayList<Foods>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        if (listItem[position].numberInCart == 1) {
            listItem.removeAt(position)
        } else {
            listItem[position].numberInCart--
        }

        tinyDB.putListObject("CartList", listItem)
        changeNumberItemsListener.change()
    }

    fun plusNumberItem(
        listItem: ArrayList<Foods>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        listItem[position].numberInCart++
        tinyDB.putListObject("CartList", listItem)
        changeNumberItemsListener.change()
    }
}
