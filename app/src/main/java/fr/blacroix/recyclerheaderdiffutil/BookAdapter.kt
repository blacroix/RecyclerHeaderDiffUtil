package fr.blacroix.recyclerheaderdiffutil

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BookAdapter(private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        val BOOK_VIEW_TYPE = 1
        val HEADER_VIEW_TYPE = 0
        val HEADER_VIEW_COUNT = 1
    }

    private var listUpdateCallback: MyListUpdateCallback = MyListUpdateCallback(this)

    private val books = mutableListOf<Book>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        when (view) {
            is BookView -> {
                view.bind(books[position - HEADER_VIEW_COUNT])
            }
        }
    }

    override fun getItemCount(): Int = books.size + HEADER_VIEW_COUNT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = when (viewType) {
        HEADER_VIEW_TYPE -> ViewHolder(layoutInflater.inflate(R.layout.header_view, parent, false))
        else -> ViewHolder(layoutInflater.inflate(R.layout.book_view, parent, false))
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) HEADER_VIEW_TYPE else BOOK_VIEW_TYPE

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