package com.example.internetshop

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity() {
    private lateinit var cartCountTextView: TextView
    private var cartCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        if (savedInstanceState != null) {
            cartCount = savedInstanceState.getInt("cartCount", 0)
        }

        val itemsList: RecyclerView = findViewById(R.id.itemsList)

        val cartIcon: ImageView = findViewById(R.id.cart_icon_bottom)
        val mainIcon: ImageView = findViewById(R.id.main_icon)
        val orderIcon: ImageView = findViewById(R.id.order_icon)
        cartCountTextView = findViewById(R.id.cart_count_bottom)

        updateCartCount()

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(itemList, this)

        cartIcon.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
        }

        mainIcon.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }

        orderIcon.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateCartCount()
    }

    private fun updateCartCount() {
        cartCountTextView.text = Singleton.CartManager.itemCount.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("cartCount", cartCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cartCount = savedInstanceState.getInt("cartCount", 0)
        updateCartCount()
    }
}