plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
    alias(libs.plugins.iamkarim.android.room)
}

android {
    namespace = "com.abdulkarim.cache"
}

dependencies {
    implementation(projects.core.model.entity)
}