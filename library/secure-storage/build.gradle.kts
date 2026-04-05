plugins {
    alias(libs.plugins.iamkarim.android.library)
}

android {
    namespace = "com.abdulkarim.securestorage"
}

dependencies {
    implementation(libs.androidx.security.crypto)
}