plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.fifteen11.jetpacksamplelibrary"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fifteen11.jetpacksamplelibrary"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.fifteen11.jetpacksamplelibrary.CustomRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
        freeCompilerArgs = listOf("-Xjvm-default=all")
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

    sourceSets {
        getByName("androidTest") {
            java.srcDirs("src/sharedTest/java")
        }
        getByName("test") {
            java.srcDirs("src/sharedTest/java")
        }
    }
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
    implementation(libs.material)

    testImplementation(libs.junit) //JUnit
    testImplementation(libs.androidx.core.testing) //Core Testing
    testImplementation(libs.mockito.core) //Mockito for test mock data
    testImplementation(libs.mockito.kotlin) //Mockito for Kotlin
    testImplementation(libs.kotlinx.coroutines.test) //Testing for Coroutines in Kotlin
    //testImplementation(libs.mockwebserver) //For testing Retrofit API calls and Create a Mock Web Server
    testImplementation("com.google.dagger:hilt-android-testing:2.52") // Hilt Dependency Injection Testing
    kaptTest("com.google.dagger:hilt-android-compiler:2.51.1") // Hilt Dependency Injection Testing

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core) //Espresso for Android UI Testing
    androidTestImplementation(libs.androidx.espresso.intents) //Espresso Intent for Android UI Testing with Intent
    androidTestImplementation(libs.androidx.core.testing) //Core Testing for Android
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.52") // Hilt Dependency Injection Testing
    androidTestImplementation("app.cash.turbine:turbine:1.1.0") //Turbine is for testing Flow when we use Coroutines and flow is continuously emitting data
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.51.1") // Hilt Dependency Injection Testing

    //SPLASHSCREEN
    implementation(libs.androidx.core.splashscreen)

    //VIEWMODEL
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //NAVIGATION
    implementation(libs.androidx.navigation.compose)

    //GSON
    implementation(libs.gson)

    //ROOM
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)//KTX Extensions/Coroutines for Room
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.room.testing) //Testing Room Database features
    androidTestImplementation(libs.room.testing) //Testing Room Database features

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")

    // Moshi for JSON parsing
    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // MockWebServer for testing
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")

    //GLIDE
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
}