plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "phdhtl.k63cntt1.nguyen"
    compileSdk = 34

    defaultConfig {
        applicationId = "phdhtl.k63cntt1.nguyen"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.github.PhilJay:MPAndroidChart:v3.0.2")
}
    // https://mvnrepository.com/artifact/com.github.PhilJay/MPAndroidChart
    implementation("com.github.PhilJay:MPAndroidChart:v3.0.2")
}
