import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.abdulkarim.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)

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

        register("androidHiltConvention") {
            id = "iamkarim.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidRetrofitConvention") {
            id = "iamkarim.android.retrofit"
            implementationClass = "AndroidRetrofitConventionPlugin"
        }

        register("androidFeatureConvention") {
            id = "iamkarim.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidNavigationComponentConvention") {
            id = "iamkarim.android.navigation"
            implementationClass = "AndroidNavigationComponentConventionPlugin"
        }

        register("androidApplicationFirebaseConvention") {
            id = "iamkarim.android.firebase"
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }
    }
}
