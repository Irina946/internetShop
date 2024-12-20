package com.example.internetshop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ItemActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    private lateinit var cartCountTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val title: TextView = findViewById(R.id.item_title)
        val text: TextView = findViewById(R.id.item_description)
        val price: TextView = findViewById(R.id.item_price)
        val imageView: ImageView = findViewById(R.id.item_image)
        val cartIcon: ImageView = findViewById(R.id.cart_icon_bottom)
        val mainIcon: ImageView = findViewById(R.id.main_icon)
        val buttonBuy: Button = findViewById(R.id.button_buy)
        val orderIcon: ImageView = findViewById(R.id.order_icon)
        cartCountTextView = findViewById(R.id.cart_count_bottom)

        updateCartCount()

        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemDesc")
        price.text = "Цена: " + intent.getStringExtra("itemPrice")
        val imageName = intent.getStringExtra("itemImageName")

        imageName?.let {
            val imageId = resources.getIdentifier(imageName, "drawable", packageName)
            imageView.setImageResource(imageId)
        }

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

        buttonBuy.setOnClickListener {
            addToCart()
        }
    }

    override fun onResume() {
        super.onResume()
        updateCartCount()
    }

    private fun addToCart() {
        val itemTitle = intent.getStringExtra("itemTitle") ?: return
        var itemPriceString = intent.getStringExtra("itemPrice") ?: return
        itemPriceString = itemPriceString.replace(Regex("[^0-9]"), "")
        val itemPrice = itemPriceString.toIntOrNull() ?: return

        Singleton.CartManager.addItem(CartItem(itemTitle, itemPrice))
        updateCartCount()
        Toast.makeText(this, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
    }

    private fun updateCartCount() {
        cartCountTextView.text = Singleton.CartManager.itemCount.toString()
    }
}
