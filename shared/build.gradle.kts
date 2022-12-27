plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Deps.Ktor) {
                    implementation(clientCore)
                    implementation(clientJson)
                    implementation(clientLogging)
                    implementation(contentNegotiation)
                    implementation(json)
                }

                with(Deps.Kotlinx) {
                    implementation(coroutinesCore)
                    implementation(serializationCore)
                    implementation(dateTime)
                    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                }

              /*  with(Deps.SqlDelight) {
                    implementation(runtime)
                    implementation(coroutineExtensions)
                }*/

                with(Deps.Koin) {
                    api(core)
                    api(test)
                }

                with(Deps.Log) {
                    api(kermit)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(Deps.Koin.test)
                implementation(Deps.Kotlinx.coroutinesTest)
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting{
            dependencies {
                implementation(Deps.Ktor.clientAndroid)
             //   implementation(Deps.SqlDelight.androidDriver)
            }
        }
        val androidTest by getting{
            dependencies {
                implementation(Deps.Ktor.clientAndroid)
                //implementation(Deps.SqlDelight.androidDriver)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Deps.Ktor.clientDarwin)
                //implementation(Deps.SqlDelight.nativeDriver)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.my.paypaytest.curencyconverter"
    compileSdk = Versions.targetSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.my.paypaytest.curencyconverter.android"
}