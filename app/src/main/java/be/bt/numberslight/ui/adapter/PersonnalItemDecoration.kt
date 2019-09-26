package be.bt.numberslight.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * I create my ItemDecoration to create a space between the element of the RecyclerView
 *
 * @property verticalspace the distance between two item
 */
class VerticalSpaceItemDecoration(private var verticalspace: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1) {
            outRect.bottom = verticalspace
        }

    }
}