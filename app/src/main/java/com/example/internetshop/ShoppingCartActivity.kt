package com.example.internetshop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShoppingCartActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var totalPriceTextView: TextView
    private lateinit var checkoutButton: Button
    private lateinit var cartCountTextView: TextView

    private val cartItems = Singleton.CartManager.getItems()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        recyclerView = findViewById(R.id.cart_recycler_view)
        totalPriceTextView = findViewById(R.id.total_price)
        checkoutButton = findViewById(R.id.button_checkout)
        val cartIcon: ImageView = findViewById(R.id.cart_icon_bottom)
        val mainIcon: ImageView = findViewById(R.id.main_icon)
        val orderIcon: ImageView = findViewById(R.id.order_icon)
        cartCountTextView = findViewById(R.id.cart_count_bottom)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CartAdapter(cartItems)
        recyclerView.adapter = adapter

        updateTotalPrice()
        updateCartCount()

        cartIcon.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
        }

        mainIcon.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }

        orderIcon.setOnClickListener{
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }



        checkoutButton.setOnClickListener {
            val total = cartItems.sumOf { it.price }
            Singleton.CartManager.addSum(total)
            Singleton.CartManager.clearItems()

            Toast.makeText(this, "Заказ оформлен", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun updateTotalPrice() {
        val total = cartItems.sumOf { it.price }
        totalPriceTextView.text = "Итого: $total ₽"
    }

    override fun onResume() {
        super.onResume()
        updateCartCount()
    }

    private fun updateCartCount() {
        cartCountTextView.text = Singleton.CartManager.itemCount.toString()
    }

}