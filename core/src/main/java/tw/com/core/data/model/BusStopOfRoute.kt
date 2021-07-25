package tw.com.core.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusStopOfRoute(
    val RouteUID: String,
    val RouteName: NameType,
    val Direction: Int, // [0:'去程',1:'返程',2:'迴圈',255:'未知']
    val Stops: List<Stop>
)

@JsonClass(generateAdapter = true)
data class Stop(
    val StopUID: String,
    val StopName: NameType,
    val StopBoarding: Int,
    val StopSequence: Int
)
