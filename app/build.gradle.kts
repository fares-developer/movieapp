import org.gradle.kotlin.dsl.testImplementation // This import is fine, but not strictly necessary here.

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Consider aligning this version with your main Kotlin plugin version if possible,
    // or managing it via the version catalog.
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    id("kotlin-parcelize")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.movieapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Jetpack Compose BOM
    implementation(platform(libs.androidx.compose.bom))

    // Jetpack Compose UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Jetpack Compose Foundation, Material, Icons, etc.
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)

    // Retrofit , Gson, Coil , Firebase
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.firebase.auth)
    implementation(libs.coil.compose)


    // Testing
    // For local unit tests
    testImplementation(libs.junit) // Use the version from libs catalog
    // Optional: For Mockito if you are using mocking
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    // In your build.gradle.kts (Module :app)
    testImplementation(libs.kotlinx.coroutines.test) // Or the latest stable version

    // For Android Instrumented tests
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // DebugImplementation for Compose tooling
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // Other dependencies...
    // Ensure any other dependencies are correctly placed here.

    // Removed:
    // val testImplementation = null
    // testImplementation "junit:junit:4.13.2" // This was a duplicate

    // For AndroidX Test libraries (if you're using them, though less common for pure unit tests)
    // testImplementation "androidx.test:core-ktx:1.4.0"
    // testImplementation "androidx.test.ext:junit-ktx:1.1.3"
}