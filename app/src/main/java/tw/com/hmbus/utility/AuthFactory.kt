package tw.com.hmbus.utility

import android.util.Base64
import tw.com.hmbus.Keys
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class AuthFactory {
    companion object {
        /**
         * https://ptxmotc.gitbooks.io/ptx-api-documentation/content/api-shi-yong/hmac.html
         * */
        fun createPtxAuth(): PtxAuth {
            val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z")
            val xDate = ZonedDateTime.now(ZoneOffset.UTC).format(formatter).toString()

            val signingKey = SecretKeySpec(Keys.ptxL1Key().toByteArray(), "HmacSHA1")
            val rawHmac = Mac.getInstance("HmacSHA1").run {
                init(signingKey)
                doFinal("x-date: $xDate".toByteArray())
            }
            val signature = Base64.encodeToString(rawHmac, Base64.NO_WRAP)
            val auth = "hmac username=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\""
                .format(Keys.ptxL1AppId(), "hmac-sha1", "x-date", signature)

            return PtxAuth(auth, xDate)
        }
    }
}

data class PtxAuth(val auth: String, val xDate: String)
