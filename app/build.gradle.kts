plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("io.realm.kotlin")
    kotlin("plugin.serialization")
}

android {
    namespace = "ru.rickmasters.demo"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.rickmasters.demo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    packagingOptions {
        exclude("META-INF/kotlinx-io.kotlin_module")
        exclude("META-INF/atomicfu.kotlin_module")
        exclude("META-INF/kotlinx-coroutines-io.kotlin_module")
        exclude("META-INF/kotlinx-coroutines-core.kotlin_module")
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    val realm = "1.11.0"
    implementation("com.google.dagger:hilt-android:2.50")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("io.realm.kotlin:library-base:$realm")
    implementation("io.realm.kotlin:library-sync:$realm")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    val ktor_version = "2.3.7"
    implementation ("io.ktor:ktor-client-core:$ktor_version")
    implementation( "io.ktor:ktor-client-android:$ktor_version")
    implementation( "io.ktor:ktor-serialization-gson:$ktor_version")
    implementation( "io.ktor:ktor-client-serialization:$ktor_version")
    implementation( "io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation( "io.ktor:ktor-client-logging:$ktor_version")
    implementation( "io.coil-kt:coil-compose:2.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha13")
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.32.0")

}