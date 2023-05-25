package com.alex.carexpenses3.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Decorator(private val offset: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            top = if (parent.getChildAdapterPosition(view) == 0) offset else DEFAULT_OFFSET
            bottom = offset
        }
    }

    companion object{
        private const val DEFAULT_OFFSET = 0
    }
}