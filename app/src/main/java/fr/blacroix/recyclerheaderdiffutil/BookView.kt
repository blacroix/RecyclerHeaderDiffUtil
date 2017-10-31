package fr.blacroix.recyclerheaderdiffutil

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import kotlinx.android.synthetic.main.book_view.view.*

class BookView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr) {

    fun bind(book: Book) {
        stringText.text = book.name
    }

}