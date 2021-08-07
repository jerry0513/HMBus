plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "tw.com.hmbus"
        minSdk = Versions.minSdk
        targetSdk = Versions.compileSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":core"))

    implementation(Dependencies.Kotlin.stdlib)
    coreLibraryDesugaring(Dependencies.androidDesugarJdkLibs)

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

    implementation(Dependencies.Dagger.hiltAndroid)
    kapt(Dependencies.Dagger.hiltAndroidCompiler)

    debugImplementation(Dependencies.leakCanary)

    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Test.core)
    testImplementation(Dependencies.Test.coreKtx)
    testImplementation(Dependencies.Test.extJunit)
    androidTestImplementation(Dependencies.Test.extJunit)
    androidTestImplementation(Dependencies.Test.espresso)

    implementation(Dependencies.OkHttp3.loggingInterceptor)
    implementation(Dependencies.Retrofit2.retrofit)
}
