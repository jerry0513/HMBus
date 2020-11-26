package tw.com.hmbus.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusN1EstimateTime(val StopName: String, val EstimateTime: Int)