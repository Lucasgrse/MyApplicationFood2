package com.example.myapplicationfood.Domain


data class Price(
    var id: Int = 0,
    var value: String? = null
) {
    override fun toString(): String {
        return value ?: ""
    }
}