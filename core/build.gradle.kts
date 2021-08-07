plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.compileSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        externalNativeBuild {
            cmake {
                cppFlags("")
            }
        }
    }

    buildTypes {
        getByName("release") {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            version = Versions.cmake
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)
    coreLibraryDesugaring(Dependencies.androidDesugarJdkLibs)

    implementation(Dependencies.Coroutines.core)
    testImplementation(Dependencies.Coroutines.test)

    implementation(Dependencies.OkHttp3.okhttp)
    compileOnly(Dependencies.OkHttp3.loggingInterceptor)

    compileOnly(Dependencies.Retrofit2.retrofit)
    implementation(Dependencies.Retrofit2.converterMoshi)

    implementation(Dependencies.Moshi.moshi)
    kapt(Dependencies.Moshi.kotlinCodegen)

    implementation(Dependencies.Dagger.hiltAndroid)
    kapt(Dependencies.Dagger.hiltAndroidCompiler)

    testImplementation(Dependencies.Test.junit)
}