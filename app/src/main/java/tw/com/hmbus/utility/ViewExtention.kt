package tw.com.hmbus.utility

import android.content.res.Resources
import kotlin.math.roundToInt

fun Int.dp(): Int {
    val density = Resources.getSystem().displayMetrics.density
    return (this * density).roundToInt()
}
