package tw.com.hmbus.ui.home

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.com.hmbus.data.remote.BusRoute
import tw.com.hmbus.databinding.BusRouteItemBinding
import tw.com.hmbus.utility.dp

class BusRouteAdapter : RecyclerView.Adapter<BusRouteViewHolder>() {

    var data = listOf<BusRoute>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusRouteViewHolder =
        BusRouteViewHolder.create(parent)

    override fun onBindViewHolder(holder: BusRouteViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}

class BusRouteViewHolder(private val binding: BusRouteItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BusRoute) {
        binding.route.text = item.RouteName.Zh_tw
        binding.depDesStopName.text = "${item.DepartureStopNameZh} - ${item.DestinationStopNameZh}"
    }

    companion object {
        fun create(parent: ViewGroup): BusRouteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = BusRouteItemBinding.inflate(inflater, parent, false)
            return BusRouteViewHolder(binding)
        }
    }
}

class BusRouteItemDecoration : RecyclerView.ItemDecoration() {
    private val OFFSET_LEFT = 8.dp()
    private val OFFSET_TOP = 16.dp()
    private val OFFSET_RIGHT = 8.dp()
    private val OFFSET_BOTTOM = 16.dp()
    private val DIVIDER_HEIGHT = 1.dp()

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
            outRect.top = OFFSET_TOP
        outRect.bottom = OFFSET_BOTTOM
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        for (i in 0 until parent.childCount) {
            if (i == 0) continue

            val view = parent.getChildAt(i)
            val dividerOffset = OFFSET_TOP / 2

            val left = parent.paddingLeft
            val top = view.top - dividerOffset
            val right = parent.width - parent.paddingRight
            val bottom = view.top - dividerOffset + DIVIDER_HEIGHT
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), dividerPaint)
        }
    }
}
