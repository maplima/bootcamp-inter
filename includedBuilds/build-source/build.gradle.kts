plugins {
    `kotlin-dsl`
    id("my-build-configuration-plugin")
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("my-project-plugin") {
            id = "my-project-plugin"
            implementationClass = "ProjectPlugin"
        }
        register("my-library-plugin") {
            id = "my-library-plugin"
            implementationClass = "LibraryPlugin"
        }
        register("my-application-plugin") {
            id = "my-application-plugin"
            implementationClass = "ApplicationPlugin"
        }
    }
}

dependencies {
    implementation("com.example.gradle:build-configuration:1.0.0")
    implementation("com.android.tools.build:gradle:${Deps.Version.agp}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${Deps.Version.kotlin}")
}