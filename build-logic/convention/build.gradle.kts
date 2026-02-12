
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
            id = "abdulkarim.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibraryConvention") {
            id = "abdulkarim.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}
