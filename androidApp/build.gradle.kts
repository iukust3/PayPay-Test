plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.my.paypaytest.curencyconverter.android"
    compileSdk = Versions.targetSdk
    defaultConfig {
        applicationId = "com.my.paypaytest.curencyconverter.android"
        minSdk = Versions.minSdk
        targetSdk =Versions.targetSdk
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    with(Deps.Compose){
        implementation(ui)
        implementation(uiTooling)
        implementation(uiToolingPreview)
        implementation(foundation)
        implementation(material)
        implementation(activity)
    }
    with(Deps.Test){
        implementation(junit)
        implementation(androidXTestJUnit)
        implementation(androidXTestJUnitKtx)
        implementation(exprsso)
    }
    with(Deps.Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
        testImplementation(test)
        testImplementation(testJUnit4)
    }
    with(Deps.Test) {
        testImplementation(junit)
        androidTestImplementation(androidXTestJUnit)
        testImplementation(testCore)
        testImplementation(mockito)

        // Compose testing dependencies
        androidTestImplementation(composeUiTest)
        androidTestImplementation(composeUiTestJUnit)
        debugImplementation(composeUiTestManifest)
    }
   }