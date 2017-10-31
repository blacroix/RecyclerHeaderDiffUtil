package fr.blacroix.recyclerheaderdiffutil

import android.support.v7.util.DiffUtil
import android.support.v7.util.ListUpdateCallback

class BookDiffUtil(private val oldBooks: List<Book>, private val newBooks: List<Book>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldBooks[oldItemPosition].hashCode() == newBooks[newItemPosition].hashCode()

    override fun getOldListSize(): Int = oldBooks.size

    override fun getNewListSize(): Int = newBooks.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldBooks[oldItemPosition] == newBooks[newItemPosition]

}

class MyListUpdateCallback(private val adapter: BookAdapter) : ListUpdateCallback {

    override fun onChanged(position: Int, count: Int, payload: Any?) =
            adapter.notifyItemRangeChanged(position + 1, count, payload)

    override fun onMoved(fromPosition: Int, toPosition: Int) =
            adapter.notifyItemMoved(fromPosition + 1, toPosition + 1)

    override fun onInserted(position: Int, count: Int) =
            adapter.notifyItemRangeInserted(position + 1, count)

    override fun onRemoved(position: Int, count: Int) =
            adapter.notifyItemRangeRemoved(position + 1, count)
}