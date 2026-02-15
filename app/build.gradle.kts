plugins {
    alias(libs.plugins.iamkarim.android.application)
    alias(libs.plugins.iamkarim.android.application.flavors)
    alias(libs.plugins.iamkarim.android.hilt)
    alias(libs.plugins.iamkarim.android.retrofit)
    alias(libs.plugins.iamkarim.android.navigation)
}

android {
    namespace = "com.abdulkarim.android_clean_architecture"

    defaultConfig {
        applicationId = "com.abdulkarim.clean_architecture"
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(projects.common)
    implementation(projects.core.designSystem)
    implementation(projects.core.ui)
    implementation(projects.core.di)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.model.apiresponse)
    implementation(projects.core.model.entity)

    implementation(projects.feature.postList)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}