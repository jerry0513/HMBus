import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate

object Dependencies {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.3"

    const val material = "com.google.android.material:material:${Versions.material}"
    const val klock = "com.soywiz.korlibs.klock:klock-android:${Versions.klock}"
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
        const val extJunit = "androidx.test.ext:junit:${Versions.Test.extJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
        const val runner = "androidx.test:runner:${Versions.Test.runner}"
        const val rules = "androidx.test:rules:${Versions.Test.rules}"
    }

    object OkHttp3 {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    }

    object Retrofit2 {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val kotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Dagger {
        const val hiltAndroidGradlePlugin =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
        const val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.dagger}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
    }
}

fun Project.importAppDependencies() {

    /**
     * use xxx by configurations
     * or "xxx", xxx is function name
     *
     * source: https://github.com/gradle/kotlin-dsl-samples/issues/843
     * */

    val implementation by configurations
    val debugImplementation by configurations
    val kapt by configurations
    val testImplementation by configurations
    val androidTestImplementation by configurations

    dependencies {
        "implementation"(Dependencies.Kotlin.stdlib)

        implementation(Dependencies.AndroidX.coreKTX)
        implementation(Dependencies.AndroidX.appcompat)
        implementation(Dependencies.material)
        implementation(Dependencies.AndroidX.constraintlayout)
        implementation(Dependencies.AndroidX.viewpager2)
        implementation(Dependencies.AndroidX.Lifecycle.liveDataKtx)
        implementation(Dependencies.AndroidX.Lifecycle.viewModelKtx)
        implementation(Dependencies.AndroidX.Lifecycle.viewModelSavedState)
        implementation(Dependencies.AndroidX.Lifecycle.commonJava8)
        implementation(Dependencies.AndroidX.Navigation.fragment)
        implementation(Dependencies.AndroidX.Navigation.ui)

        implementation(Dependencies.Coroutines.android)
        testImplementation(Dependencies.Coroutines.test)

        implementation(Dependencies.OkHttp3.okhttp)
        implementation(Dependencies.OkHttp3.loggingInterceptor)

        implementation(Dependencies.Retrofit2.retrofit)
        implementation(Dependencies.Retrofit2.converterMoshi)

        implementation(Dependencies.Moshi.moshi)
        kapt(Dependencies.Moshi.kotlinCodegen)

        implementation(Dependencies.Dagger.hiltAndroid)
        kapt(Dependencies.Dagger.hiltAndroidCompiler)

        implementation(Dependencies.klock)
        debugImplementation(Dependencies.leakCanary)

        testImplementation(Dependencies.Test.junit)
        androidTestImplementation(Dependencies.Test.extJunit)
        androidTestImplementation(Dependencies.Test.espresso)
        androidTestImplementation(Dependencies.Test.runner)
        androidTestImplementation(Dependencies.Test.rules)
    }
}