package tw.com.hmbus.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.com.core.data.model.BusRoute
import tw.com.hmbus.databinding.BusRouteItemBinding

class BusRouteAdapter : RecyclerView.Adapter<BusRouteViewHolder>() {

    var data = listOf<BusRoute>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClickListener: (BusRoute) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusRouteViewHolder =
        BusRouteViewHolder.create(parent)

    override fun onBindViewHolder(holder: BusRouteViewHolder, position: Int) =
        holder.bind(data[position], onItemClickListener)

    override fun getItemCount(): Int = data.size
}

class BusRouteViewHolder(private val binding: BusRouteItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BusRoute, onItemClickListener: (BusRoute) -> Unit) {
        binding.route.text = item.RouteName.Zh_tw
        binding.depDesStopName.text = "${item.DepartureStopNameZh} - ${item.DestinationStopNameZh}"
        itemView.setOnClickListener {
            onItemClickListener.invoke(item)
        }
    }

    companion object {
        fun create(parent: ViewGroup): BusRouteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = BusRouteItemBinding.inflate(inflater, parent, false)
            return BusRouteViewHolder(binding)
        }
    }
}

