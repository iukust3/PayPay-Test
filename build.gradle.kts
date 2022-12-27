plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.1").apply(false)
    id("com.android.library").version("7.3.1").apply(false)
    kotlin("android").version("1.7.21").apply(false)
    kotlin("multiplatform").version("1.7.21").apply(false)
    id("androidx.benchmark").version ("1.1.1"). apply( false)
    kotlin("plugin.serialization") version "1.7.21"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
