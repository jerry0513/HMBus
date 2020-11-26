package tw.com.hmbus.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusRoute(val RouteUID: String, val RouteName: NameType, val SubRoutes: List<BusSubRoute>)