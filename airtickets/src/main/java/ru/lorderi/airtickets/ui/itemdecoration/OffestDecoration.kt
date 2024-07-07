package ru.lorderi.airtickets.ui.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OffsetDecoration(
    @Px private val offset: Int,
    @Px private val offsetRight: Int,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top += offset
        outRect.left += offset
        outRect.right += offsetRight

        val lastVisibleItemPosition =
            (parent.layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()

        val lastItemPosition = parent.adapter?.itemCount?.minus(1)

        if (lastVisibleItemPosition == lastItemPosition) {
            outRect.bottom += offset
        }
    }
}