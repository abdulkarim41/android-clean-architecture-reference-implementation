import com.iamkarim.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationFirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
            }

            dependencies {
                add(
                    configurationName= "implementation",
                    dependencyNotation = platform(libs.findLibrary("firebase-bom").get())
                )
                add(
                    configurationName = "implementation",
                    dependencyNotation = libs.findLibrary("firebase.messaging").get()
                )
                add(
                    configurationName = "implementation",
                    dependencyNotation = libs.findLibrary("firebase.analytics").get()
                )
                add(
                    configurationName = "implementation",
                    dependencyNotation = libs.findLibrary("firebase.crashlytics").get()
                )
            }
        }
    }
}
