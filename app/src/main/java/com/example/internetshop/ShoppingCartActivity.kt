package com.example.internetshop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShoppingCartActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var totalPriceTextView: TextView
    private lateinit var checkoutButton: Button
    private lateinit var buttonBack: Button

    private val cartItems = Singleton.CartManager.getItems()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        recyclerView = findViewById(R.id.cart_recycler_view)
        totalPriceTextView = findViewById(R.id.total_price)
        checkoutButton = findViewById(R.id.button_checkout)
        buttonBack = findViewById(R.id.button_back_cart)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CartAdapter(cartItems)
        recyclerView.adapter = adapter

        updateTotalPrice()

        checkoutButton.setOnClickListener {
            finish()
        }

        buttonBack.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateTotalPrice() {
        val total = cartItems.sumOf { it.price }
        totalPriceTextView.text = "Итого: $total ₽"
    }
}