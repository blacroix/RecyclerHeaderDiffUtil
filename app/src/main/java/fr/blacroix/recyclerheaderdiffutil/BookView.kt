package fr.blacroix.recyclerheaderdiffutil

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.book_view.view.*

class BookView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr) {

    fun bind(book: Book) {
        stringText.text = book.name
    }

}