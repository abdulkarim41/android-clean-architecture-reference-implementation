package com.abdulkarim

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension,
) {
    commonExtension.apply {
        compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

        defaultConfig.apply {
            minSdk = libs.findVersion("minSdk").get().toString().toInt()
        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            isCoreLibraryDesugaringEnabled = true
        }
    }

    configureKotlin()

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("desugar").get())
    }
}

/// Configure base Kotlin options for JVM (non-Android)
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    configureKotlin()
}

/// Configure base Kotlin options
private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)

            // Treat all Kotlin warnings as errors based on a project property.
            val warningsAsErrors: String? by project
            allWarningsAsErrors.set(warningsAsErrors.toBoolean())

            /// These flags tell the compiler: “I know I’m using experimental APIs; please allow it without errors.”
            freeCompilerArgs.addAll(
                //Allows using any API annotated with @RequiresOptIn (Kotlin’s opt-in mechanism).
                "-opt-in=kotlin.RequiresOptIn",
                //Allows using experimental coroutine APIs (things that aren’t stable yet).
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                //Allows using preview APIs in Flow (again, not fully stable).
                "-opt-in=kotlinx.coroutines.FlowPreview",
            )
        }
    }
}
