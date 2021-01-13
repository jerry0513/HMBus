package tw.com.hmbus.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tw.com.hmbus.utility.dp

class DividerItemDecoration : RecyclerView.ItemDecoration() {

    private val dividerPaint = Paint().apply {
        isAntiAlias = true
        color = Color.GRAY
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = OFFSET_VERTICAL
        outRect.bottom = OFFSET_VERTICAL
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        for (i in 0 until parent.childCount) {
            if (i == 0) continue

            val view = parent.getChildAt(i)
            val dividerOffset = OFFSET_VERTICAL / 2

            val left = parent.paddingLeft
            val top = view.top - dividerOffset
            val right = parent.width - parent.paddingRight
            val bottom = view.top - dividerOffset + DIVIDER_HEIGHT
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), dividerPaint)
        }
    }

    companion object {
        private val OFFSET_VERTICAL = 16.dp
        private val DIVIDER_HEIGHT = 1.dp
    }
}