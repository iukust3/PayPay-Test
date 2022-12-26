import org.gradle.kotlin.dsl.`kotlin-dsl`
dependencies {
    implementation(kotlin("script-runtime"))
}
plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}