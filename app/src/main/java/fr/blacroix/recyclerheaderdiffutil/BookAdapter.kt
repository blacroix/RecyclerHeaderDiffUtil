package fr.blacroix.recyclerheaderdiffutil

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BookAdapter(private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<ViewHolder>() {

    private var listUpdateCallback: MyListUpdateCallback = MyListUpdateCallback(this)

    private val books = mutableListOf<Book>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        when (view) {
            is BookView -> {
                if (position == 0) {
                    view.bind(Book("My name is Header!"))
                } else {
                    view.bind(books[position - 1])
                }
            }
        }
    }

    override fun getItemCount(): Int = books.size + 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(layoutInflater.inflate(R.layout.book_view, parent, false))

    override fun getItemViewType(position: Int): Int = if (position == 0) 0 else 1

    fun setData(list: List<Book>) {
        books.addAll(list)
        notifyDataSetChanged()
    }

    fun updateData(list: List<Book>) {
        val diff = DiffUtil.calculateDiff(BookDiffUtil(books, list))
        books.clear()
        books.addAll(list)
        diff.dispatchUpdatesTo(listUpdateCallback)
    }

    fun getData(): Collection<Book> = books

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)