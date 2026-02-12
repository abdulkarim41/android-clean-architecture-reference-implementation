
plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationConvention") {
            id = "iamkarim.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibraryConvention") {
            id = "iamkarim.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("jvmLibraryConvention") {
            id = "iamkarim.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}
