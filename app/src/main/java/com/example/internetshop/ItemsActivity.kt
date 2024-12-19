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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        val items = arrayListOf<Item>()

        val cartIcon: ImageView = findViewById(R.id.cart_icon)
        cartCountTextView = findViewById(R.id.cart_count)

        updateCartCount()

        items.add(
            Item(
                1,
                "gucci",
                "GUCCI Flora Gorgeous Gardenia",
                "Парфюмерная вода, спрей 30 мл",
                "Перенеситесь в радостную страну фантазий с ароматом Gucci Flora. Новая цветочная парфюмерная вода Gucci Flora Gorgeous Gardenia с легким сладким оттенком предстает в новой рекламной кампании в окружении цветов и пушистых животных. Культовый цветочный аромат создан для свободных духом женщин, излучающих позитивную энергию.Композиция аромата построена вокруг гардении, которая испокон веков почиталась за свою величественную красоту и, как гласят легенды, использовалась для приготовления чудодейственных эликсиров и волшебных зелий. Таинственная нота белой гардении сливается в сердце аромата с абсолю солнечного крупноцветкового жасмина. Упоительный нектар радости открывают ноты цветка груши. Аккорд тростникового сахара придает шлейфу изысканную сладость.Новый удлиненный флакон из лакированного розового стекла с блестящей золотой крышечкой можно назвать подлинным произведением искусства. Его украшает знаменитый узор.",
                8500
            )
        )
        items.add(
            Item(
                2,
                "lacoste",
                "LACOSTE L.12.12",
                "Парфюмерная вода, спрей 90 мл",
                "LACOSTE L.12.12 — это свежий аромат, который воплощает собой спортивную элегантность и стиль жизни. Созданный для современных мужчин, этот парфюм раскрывается яркими нотами грейпфрута и мандарина, которые придают ему энергичность и жизнерадостность. В сердце аромата располагаются пряные ноты кардамона и зелёного чая, добавляющие глубину и характер. Завершает композицию тёплая база из кедра и ванили, создавая стойкий и запоминающийся след. Флакон выполнен в классическом дизайне с эмблемой крокодила, отражая стиль LACOSTE.",
                6500
            )
        )
        items.add(
            Item(
                3,
                "paco_rabano",
                "PACO RABANO PHANTOM",
                "Парфюмерная вода, спрей 100 мл",
                "PACO RABANNE PHANTOM представляет собой смелый и инновационный аромат для уверенных мужчин. Этот парфюм начинается с ярких цитрусовых акцентов лимона и лаванды, создавая свежесть и энергетику. В сердце аромата проникает нота розового перца, добавляя пряный и загадочный оттенок. Завершает композицию насыщенный аккорд древесных нот, придавая аромату стойкость и силу. Флакон выполнен в футуристическом дизайне и отражает современное видение masculinity.",
                8800
            )
        )
        items.add(
            Item(
                4,
                "zadig_voltaire",
                "ZADIG&VOLTAIRE ART FOR ALL",
                "Парфюмерная вода, спрей 50 мл",
                "ZADIG&VOLTAIRE ART FOR ALL — это аромат, который сочетает в себе бунтарский дух и креативность. Он начинается с свежих нот ландыша и жасмина, которые создают фруктовою искристость. В сердце звенят ноты сладкого сандалового дерева и ванили, придающих теплоту и утонченность. Заключительная база из бойкой амбры и древесных аккордов обеспечивает длительное присутствие аромата, оставаясь в памяти. Флакон оформлен в минималистичном стиле, отражая философию бренда.",
                7500
            )
        )

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)

        cartIcon.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
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
}

