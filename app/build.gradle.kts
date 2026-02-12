plugins {
    alias(libs.plugins.iamkarim.android.application)
}

android {
    namespace = "com.abdulkarim.android_clean_architecture"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.abdulkarim.clean_architecture"
        minSdk = 27
        targetSdk = 36
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
}

dependencies {

    implementation(projects.core.designSystem)
    implementation(projects.core.ui)
    implementation(projects.core.data)
    implementation(projects.core.model.apiresponse)
    implementation(projects.core.model.entity)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}