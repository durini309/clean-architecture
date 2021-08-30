plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
}

android {
    compileSdk = Android.compileSdk
    buildToolsVersion = Android.buildTools

    defaultConfig {
        applicationId = Android.appId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies{
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycleVmKtx)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.fragmentNavigation)
    implementation(AndroidX.uiNavigation)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson)

    implementation(Kotlinx.coroutinesCore)

    implementation(Hilt.android)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Hilt.compiler)

    implementation(platform(Firebase.bom))
    implementation(Firebase.firestore)
    implementation(Firebase.crashlytics)
    implementation(Firebase.auth)

    implementation(Google.material)
}









