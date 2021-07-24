package tw.com.core.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusRoute(
    val RouteUID: String,
    val RouteName: NameType,
    val SubRoutes: List<BusSubRoute>,
    val DepartureStopNameZh: String,
    val DestinationStopNameZh: String
)