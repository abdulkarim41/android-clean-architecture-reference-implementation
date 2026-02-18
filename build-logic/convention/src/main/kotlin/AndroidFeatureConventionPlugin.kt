
import com.android.build.api.dsl.LibraryExtension
import com.iamkarim.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import kotlin.apply

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("iamkarim.android.library")
                apply("iamkarim.android.navigation")
                apply("iamkarim.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildFeatures {
                    viewBinding = true
                }
            }

            dependencies {
                add("implementation", project(":core:di"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:model:entity"))
                add("implementation", project(":common"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:design-system"))

                add("implementation", libs.findLibrary("androidx.appcompat").get())
                add("implementation", libs.findLibrary("androidx.core.ktx").get())
                add("implementation", libs.findLibrary("androidx.constraintlayout").get())
                add("implementation", libs.findLibrary("androidx.material").get())
                add("implementation", libs.findLibrary("androidx.fragment").get())
                add("implementation", libs.findLibrary("androidx.activity").get())
                add("implementation", libs.findLibrary("androidx.cardview").get())
                add("implementation", libs.findLibrary("androidx.recyclerview").get())

                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.savedstate").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime").get())
                add("implementation", libs.findLibrary("log.timber").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
                add("implementation", libs.findLibrary("gson").get())

                //add("testImplementation", kotlin("test"))
                //add("androidTestImplementation", kotlin("test"))
                //add("implementation", libs.findLibrary("androidx.junit").get())

            }
        }
    }
}
