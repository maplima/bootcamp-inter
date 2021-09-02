import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Project '$name' is not an Android module")

class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            applyAndroid()
            applyDependencies()
        }
    }
}

private fun Project.applyPlugins() {
    with(plugins) {
        apply("com.android.library")
        apply("kotlin-android")
    }
}

fun Project.applyAndroid() {
    with(android) {
        compileSdkVersion(30)

        defaultConfig {
            minSdkVersion(21)
            targetSdkVersion(30)
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            getByName("release") {
                minifyEnabled(false)
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        buildFeatures.viewBinding = true
    }
}

fun Project.applyDependencies() {
    dependencies {
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib:${Deps.Version.kotlin}")
        "implementation"("androidx.core:core-ktx:1.6.0")
        "implementation"("androidx.appcompat:appcompat:1.3.1")
        "implementation"("com.google.android.material:material:1.4.0")
        "implementation"("androidx.constraintlayout:constraintlayout:2.1.0")
        "testImplementation"("junit:junit:4.+")
        "androidTestImplementation"("androidx.test.ext:junit:1.1.3")
        "androidTestImplementation"("androidx.test.espresso:espresso-core:3.4.0")
    }
}
