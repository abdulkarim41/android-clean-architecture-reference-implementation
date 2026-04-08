plugins {
    alias(libs.plugins.iamkarim.android.library)
}

android {
    namespace = "com.abdulkarim"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
}