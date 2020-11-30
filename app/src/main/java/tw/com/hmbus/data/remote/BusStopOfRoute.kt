package tw.com.hmbus.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusStopOfRoute(
    val RouteUID: String,
    val RouteName: NameType,
    val Direction: Int,
    val Stops: List<Stop>
)

@JsonClass(generateAdapter = true)
data class Stop(
    val StopUID: String,
    val StopName: NameType,
    val StopBoarding: Int,
    val StopSequence: Int
)
