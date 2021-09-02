import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.repositories

class ProjectPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            allprojects {
                repositories {
                    google()
                    mavenCentral()
                }
            }

            tasks.register("clean", Delete::class) {
                delete(rootProject.buildDir)
            }
        }
    }
}
