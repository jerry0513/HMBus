import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate

object Dependencies {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.1"

    const val material = "com.google.android.material:material:${Versions.material}"

    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val coreKTX = "androidx.core:core-ktx:${Versions.AndroidX.coreKTX}"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
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

    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
}

fun Project.importAppDependencies() {

    /**
     * use xxx by configurations
     * or "xxx", xxx is function name
     *
     * source: https://github.com/gradle/kotlin-dsl-samples/issues/843
     * */

    val implementation by configurations
    val kapt by configurations
    val testImplementation by configurations
    val androidTestImplementation by configurations

    dependencies {
        "implementation"(Dependencies.Kotlin.stdlib)

        implementation(Dependencies.AndroidX.coreKTX)
        implementation(Dependencies.AndroidX.appcompat)
        implementation(Dependencies.material)
        implementation(Dependencies.AndroidX.constraintlayout)

        implementation(Dependencies.OkHttp3.okhttp)
        implementation(Dependencies.OkHttp3.loggingInterceptor)
        implementation(Dependencies.Retrofit2.retrofit)
        implementation(Dependencies.Retrofit2.converterMoshi)
        implementation(Dependencies.moshi)

        testImplementation(Dependencies.Test.junit)
        androidTestImplementation(Dependencies.Test.extJunit)
        androidTestImplementation(Dependencies.Test.espresso)
    }
}