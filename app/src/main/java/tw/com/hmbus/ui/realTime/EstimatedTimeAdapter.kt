package tw.com.hmbus.ui.realTime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.com.hmbus.data.remote.BusN1EstimateTime
import tw.com.hmbus.databinding.EstimatedTimeItemBinding
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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
        binding.estimatedTime.text = if (item.EstimateTime == null) {
            when(item.StopStatus) {
                1 -> "尚未發車"
                2 -> "交管不停靠"
                3 -> "末班車已過"
                4 -> "今日不營運"
                else -> throw IllegalStateException("unknown stop status")
            }
        } else {
            val remainingMinute = Math.ceil(item.EstimateTime / 60.0).toLong()
            when {
                remainingMinute >= 60 -> {
                    val now = ZonedDateTime.now(ZoneId.of("UTC+8"))
                    val estimatedTime = now.plusMinutes(remainingMinute)
                    val dateTimeformat = DateTimeFormatter.ofPattern("hh:mm")
                    estimatedTime.format(dateTimeformat)
                }
                remainingMinute <= 2 -> "即將到站"
                else -> "${remainingMinute}分"
            }
        }
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