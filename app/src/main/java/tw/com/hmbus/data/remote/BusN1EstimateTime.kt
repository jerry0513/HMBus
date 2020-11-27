package tw.com.hmbus.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusN1EstimateTime(
    val StopName: NameType,
    val EstimateTime: Int = -1,
    val StopStatus: Int // [0:'正常',1:'尚未發車',2:'交管不停靠',3:'末班車已過',4:'今日未營運']
)

data class Estimate(
    val PlateNumb: String,
    val EstimateTime: Int,
    val IsLastBus: Boolean,
    val VehicleStopStatus: Int // [0:'離站',1:'進站']
)
