package tw.com.hmbus.utility

import android.content.res.Resources
import kotlin.math.roundToInt

val Int.dp: Int
    get() {
        val density = Resources.getSystem().displayMetrics.density
        return (this * density).roundToInt()
    }
