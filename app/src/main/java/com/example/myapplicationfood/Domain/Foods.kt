package com.example.myapplicationfood.Domain

import java.io.Serializable

data class Foods(
    var categoryId: Int = 0,
    var description: String? = null,
    var bestFood: Boolean = false,
    var id: Int = 0,
    var locationId: Int = 0,
    var price: Double = 0.0,
    var imagePath: String? = null,
    var priceId: Int = 0,
    var star: Double = 0.0,
    var timeId: Int = 0,
    var timeValue: Int = 0,
    var title: String? = null,
    var numberInCart: Int = 0
) : Serializable {
    override fun toString(): String {
        return title ?: ""
    }
}