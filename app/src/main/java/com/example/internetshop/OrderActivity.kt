package com.example.internetshop

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderAdapter

    private val sumItems = Singleton.CartManager.getSum()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val cartIcon: ImageView = findViewById(R.id.cart_icon_bottom)
        val mainIcon: ImageView = findViewById(R.id.main_icon)
        val orderIcon: ImageView = findViewById(R.id.order_icon)

        recyclerView = findViewById(R.id.order_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = OrderAdapter(sumItems)

        recyclerView.adapter = adapter

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
    }
}