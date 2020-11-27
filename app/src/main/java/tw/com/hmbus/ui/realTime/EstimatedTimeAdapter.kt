package tw.com.hmbus.ui.realTime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.com.hmbus.data.remote.BusN1EstimateTime
import tw.com.hmbus.databinding.EstimatedTimeItemBinding

class EstimatedTimeAdapter : RecyclerView.Adapter<EstimatedTimeViewHolder>() {

    var data = listOf<BusN1EstimateTime>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstimatedTimeViewHolder =
        EstimatedTimeViewHolder.create(parent)

    override fun onBindViewHolder(holder: EstimatedTimeViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}

class EstimatedTimeViewHolder(private val binding: EstimatedTimeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BusN1EstimateTime) {
        val estimateTime = Math.ceil(item.EstimateTime / 60.0).toInt()
        binding.estimatedTime.text = "$estimateTime min"
        binding.stopName.text = item.StopName.Zh_tw
    }

    companion object {
        fun create(parent: ViewGroup): EstimatedTimeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = EstimatedTimeItemBinding.inflate(inflater, parent, false)
            return EstimatedTimeViewHolder(binding)
        }
    }
}
