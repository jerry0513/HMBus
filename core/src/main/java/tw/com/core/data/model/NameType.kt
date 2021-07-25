package tw.com.core.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NameType(val Zh_tw: String, val En: String)