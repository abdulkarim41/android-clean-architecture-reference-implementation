plugins {
    alias(libs.plugins.iamkarim.android.library)
}

android {
    namespace = "com.abdulkarim.designsystem"
}

dependencies {
    implementation(libs.androidx.material)
    implementation(libs.dimension.sdp)
    implementation(libs.dimension.ssp)
}