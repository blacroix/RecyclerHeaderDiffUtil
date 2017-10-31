package fr.blacroix.recyclerheaderdiffutil

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = BookAdapter(LayoutInflater.from(this))
        adapter.setData((0..4).map { Book("Book: $it") })

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter

        add.setOnClickListener({
            val list = mutableListOf<Book>()
            list.addAll(adapter.getData())
            list.add(3, Book("Book added!"))
            adapter.updateData(list)
        })

        remove.setOnClickListener({
            val list = mutableListOf<Book>()
            list.addAll(adapter.getData())
            if (list.size > 4) {
                list.removeAt(3)
                adapter.updateData(list)
            }
        })

        change.setOnClickListener({
            val list = mutableListOf<Book>()
            list.addAll(adapter.getData())
            list[3] = Book("Book changed!")
            adapter.updateData(list)
        })
    }
}