plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
    alias(libs.plugins.iamkarim.android.retrofit)
}

android {
    namespace = "com.abdulkarim.data"
}

dependencies {

    implementation(projects.core.di)
    implementation(projects.core.domain)
    implementation(projects.core.model.entity)
    implementation(projects.core.cache)
    implementation(projects.core.model.apiresponse)
    implementation(projects.common)

}