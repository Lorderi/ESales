package ru.lorderi.airtickets.ui.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OffsetDecoration(
    @Px private val offsetTop: Int,
    @Px private val offsetLeft: Int,
    @Px private val offsetBottom: Int,
    @Px private val offsetRight: Int,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top += offsetTop
        outRect.left += offsetLeft
        outRect.right += offsetRight

        val lastVisibleItemPosition =
            (parent.layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()

        val lastItemPosition = parent.adapter?.itemCount?.minus(1)

        if (lastVisibleItemPosition == lastItemPosition) {
            outRect.bottom += offsetBottom
        }
    }
}
