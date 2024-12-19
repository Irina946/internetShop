package com.example.internetshop

object Singleton {
    object CartManager {
        var itemCount: Int = 0
        private val items: MutableList<CartItem> = mutableListOf()

        fun addItem(item: CartItem) {
            items.add(item)
            itemCount++
        }

        fun getItems(): List<CartItem> {
            return items
        }
    }
}