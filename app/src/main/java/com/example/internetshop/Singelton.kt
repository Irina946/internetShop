package com.example.internetshop

object Singleton {
    object CartManager {
        var itemCount: Int = 0
        private val items: MutableList<CartItem> = mutableListOf()
        private var sumItems: MutableList<Int> = mutableListOf()


        fun addItem(item: CartItem) {
            items.add(item)
            itemCount++
        }

        fun getItems(): List<CartItem> {
            return items
        }

        fun clearItems() {
            items.clear()
            itemCount = 0
        }

        fun addSum(sum: Int) {
            sumItems.add(sum)
        }

        fun getSum(): List<Int> {
            return sumItems
        }
    }
}