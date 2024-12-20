package com.example.internetshop

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(private var items: List<Item>, private var context: Context) :
    RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.item_list_image)
        val title: TextView = view.findViewById(R.id.item_list_title)
        val desc: TextView = view.findViewById(R.id.item_list_desc)
        val price: TextView = view.findViewById(R.id.item_list_price)
        val button: Button = view.findViewById(R.id.item_list_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.desc.text = items[position].desc
        holder.price.text = items[position].price.toString() + " ₽"

        val imageName = items[position].image
        val imageId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

        if (imageId != 0) {
            holder.image.setImageResource(imageId)
        } else {
            holder.image.setImageResource(R.drawable.gucci)
            Toast.makeText(context, "Изображение не найдено: $imageName", Toast.LENGTH_SHORT).show()
        }

        holder.button.setOnClickListener {
            val intent = Intent(context, ItemActivity::class.java)

            intent.putExtra("itemImageName", imageName)
            intent.putExtra("itemTitle", items[position].title)
            intent.putExtra("itemDesc", items[position].text)
            intent.putExtra("itemPrice", items[position].price.toString() + " ₽")

            context.startActivity(intent)
        }
    }
}