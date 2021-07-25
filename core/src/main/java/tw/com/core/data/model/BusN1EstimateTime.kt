package tw.com.core.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusN1EstimateTime(
    val StopUID: String,
    val StopName: NameType,
    val EstimateTime: Int?,
    val StopStatus: Int // [0:'正常',1:'尚未發車',2:'交管不停靠',3:'末班車已過',4:'今日未營運']
)
