package tw.com.core.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusSubRoute(
    val SubRouteUID: String,
    val SubRouteName: NameType
)
