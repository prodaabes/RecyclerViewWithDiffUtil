package com.prodaabes.recyclerviewwithdiffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import net.pavolive.recyclerviewwithdiffutil.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ItemAdapter
    private var items = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items.add(
            Item(
                UUID.randomUUID(),
                "https://images4.alphacoders.com/107/thumb-1920-1070725.jpg",
                "BMW",
                "X6 M (2019)"
            )
        )

        items.add(
            Item(
                UUID.randomUUID(),
                "https://i.ytimg.com/vi/3UzAxxDClCM/maxresdefault.jpg",
                "Mercedes",
                "GLE 400d (2021)"
            )
        )

        items.add(
            Item(
                UUID.randomUUID(),
                "https://autonxt.net/wp-content/uploads/2018/01/autocontentexp.com2019-Lamborghini-Urus1-5ac58db2899292c401053e992115f9ebad658391.jpg",
                "Lamborghini",
                "Urus (2018)"
            )
        )

        items.add(
            Item(
                UUID.randomUUID(),
                "https://hips.hearstapps.com/hmg-prod/images/2020-tesla-model-x-mmp-1-1579127420.jpg?crop=0.827xw:0.985xh;0.0769xw,0&resize=1200:*",
                "Tesla",
                "Model X (2020)"
            )
        )

        items.add(
            Item(
                UUID.randomUUID(),
                "https://www.supercars.net/blog/wp-content/uploads/2016/04/2014_LaFerrari_-3-1536.jpg",
                "Ferrari",
                "LaFerrari (2014)"
            )
        )

        adapter = ItemAdapter(this, object : ItemAdapter.OnItemClickListener {
            override fun onItemLongClick(position: Int) {

                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setMessage("Delete Item ${position + 1} ?")

                dialog.setNegativeButton("No") { p0, p1 ->
                    p0.cancel()
                }

                dialog.setPositiveButton("Yes") { p0, p1 ->
                    p0.cancel()

                    val item = items[position]

                    val list = ArrayList(items)
                    list.remove(item)
                    items = list
                    adapter.submitList(list)
                }

                dialog.show()
            }
        })

        adapter.submitList(items)

        binding.recyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            hasFixedSize()
            adapter = this@MainActivity.adapter
        }
    }
}