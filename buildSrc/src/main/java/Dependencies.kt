object Dependencies {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val androidDesugarJdkLibs =
        "com.android.tools:desugar_jdk_libs:${Versions.androidDesugarJdkLibs}"

    const val material = "com.google.android.material:material:${Versions.material}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object Coroutines {
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val coreKTX = "androidx.core:core-ktx:${Versions.AndroidX.coreKTX}"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.AndroidX.viewpager2}"

        object Lifecycle {
            const val liveDataKtx =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycle}"
            const val viewModelKtx =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
            const val viewModelSavedState =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.AndroidX.lifecycle}"
            const val commonJava8 =
                "androidx.lifecycle:lifecycle-common-java8:${Versions.AndroidX.lifecycle}"
        }

        object Navigation {
            const val fragment =
                "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
            const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
            const val safeArgsGradlePlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.AndroidX.navigation}"
        }
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val mockk = "io.mockk:mockk:${Versions.Test.mockk}"
        const val core = "androidx.arch.core:core-testing:${Versions.Test.core}"
        const val coreKtx = "androidx.test:core-ktx:${Versions.Test.coreKtx}"
        const val extJunit = "androidx.test.ext:junit:${Versions.Test.extJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
    }

    object OkHttp3 {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Retrofit2 {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val kotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Dagger {
        const val hiltAndroidGradlePlugin =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
    }
}
