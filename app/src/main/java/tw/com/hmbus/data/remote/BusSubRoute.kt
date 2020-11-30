package tw.com.hmbus.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusSubRoute(
    val SubRouteUID: String,
    val SubRouteName: NameType
)
