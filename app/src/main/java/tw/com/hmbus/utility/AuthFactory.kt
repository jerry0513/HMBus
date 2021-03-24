package tw.com.hmbus.utility

import android.util.Base64
import tw.com.hmbus.Keys
import java.security.SignatureException
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class AuthFactory {
    companion object {
        /** source: https://ptxmotc.gitbooks.io/ptx-api-documentation/content/api-shi-yong/hmac.html */
        fun createPtxAuth(): PtxAuth {
            val xDate = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).run {
                timeZone = TimeZone.getTimeZone("GMT")
                format(Calendar.getInstance().time)
            }

            try {
                val signingKey = SecretKeySpec(Keys.ptxL1Key().toByteArray(), "HmacSHA1")
                val rawHmac = Mac.getInstance("HmacSHA1").run {
                    init(signingKey)
                    doFinal("x-date: $xDate".toByteArray())
                }
                val signature = Base64.encodeToString(rawHmac, Base64.NO_WRAP)
                val auth = "hmac username=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\""
                    .format(Keys.ptxL1AppId(), "hmac-sha1", "x-date", signature)
                return PtxAuth(auth, xDate)
            } catch (e: Exception) {
                throw SignatureException("Failed to generate HMAC: ${e.message}")
            }
        }
    }
}

data class PtxAuth(val auth: String, val xDate: String)
