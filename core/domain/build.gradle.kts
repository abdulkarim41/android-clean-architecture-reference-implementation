plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
}

android {
    namespace = "com.abdulkarim.domain"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(projects.core.model.entity)
    implementation(projects.common)
}