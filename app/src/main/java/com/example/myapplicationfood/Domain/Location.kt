package com.example.myapplicationfood.Domain

data class Location(
    var id: Int = 0,
    var loc: String? = null
) {
    override fun toString(): String {
        return loc ?: ""
    }
}