    plugins {
        id("com.android.application")
        kotlin("android")
    }

    android {
        namespace = "com.example.myapplication"
        compileSdk = 35

        defaultConfig {
            applicationId = "com.example.myapplication"
            minSdk = 24
            targetSdk = 35
            versionCode = 1
            versionName = "1.0"
        }

        buildFeatures {
            dataBinding = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        kotlinOptions {
            jvmTarget = "17"
        }
        buildToolsVersion = "34.0.0"
        ndkVersion = "27.0.12077973"
    }

    dependencies {
        implementation("androidx.core:core-ktx:1.12.0")
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("com.google.android.material:material:1.11.0")

        // Verander 2.3.1 naar 2.1.4 (2.3.1 bestaat niet)
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")

        // Verander 1.2.2 naar 1.2.1 of 1.3.0
        implementation("androidx.media3:media3-exoplayer:1.2.1")
        implementation("androidx.media3:media3-ui:1.2.1")

        implementation("androidx.recyclerview:recyclerview:1.3.2")

        // Handmatige versie voor navigatie (ipv libs)
        implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
        implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    }