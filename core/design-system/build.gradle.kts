plugins {
    alias(libs.plugins.iamkarim.android.library)
}

android {
    namespace = "com.abdulkarim.designsystem"
}

dependencies {
    //implementation(libs.androidx.core.ktx)
    //implementation(libs.androidx.appcompat)
    implementation(libs.material)
}