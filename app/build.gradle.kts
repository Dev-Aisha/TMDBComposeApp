import com.android.tools.r8.T

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.tmdbccomposeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tmdbccomposeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val TMDB_API_KEY = properties["TMDB_API_KEY"]
        buildConfigField("String", "TMDB_API_KEY", "\"$TMDB_API_KEY\"")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    android.buildFeatures.buildConfig= true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.datastore.core.android)
    implementation(libs.androidx.datastore.preferences.core.jvm)
    implementation(libs.play.services.tflite.acceleration.service)
    testImplementation(libs.junit)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation( libs.okhttp)
    implementation(libs.logging.interceptor)
    // KotlinX Serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    //json
    implementation (libs.gson)

    //coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.kotlinx.coroutines.android)

    //glide
    implementation (libs.glide)
    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")

    //coil
    implementation (libs.coil.compose)

    //paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    //data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //chucker
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")
}