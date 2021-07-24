package tw.com.core

object Keys {
    init {
        System.loadLibrary("keys-lib")
    }

    external fun ptxL1AppId(): String
    external fun ptxL1Key(): String
    external fun ptxL2AppId(): String
    external fun ptxL2Key(): String
}