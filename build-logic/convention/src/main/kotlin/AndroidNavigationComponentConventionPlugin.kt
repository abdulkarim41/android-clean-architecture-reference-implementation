import com.iamkarim.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidNavigationComponentConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("androidx.navigation.safeargs.kotlin")

            dependencies {
                add(
                    configurationName = "implementation",
                    dependencyNotation = libs.findLibrary("androidx.navigation.ktx").get()
                )
                add(
                    configurationName = "implementation",
                    dependencyNotation = libs.findLibrary("androidx.navigation.ui").get()
                )
                add(
                    configurationName = "implementation",
                    dependencyNotation = libs.findLibrary("androidx.navigation.hilt").get()
                )
            }
        }
    }

}