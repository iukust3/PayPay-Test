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
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    testImplementation("org.testng:testng:6.9.6")
    with(Deps.Compose){
        implementation(ui)
        implementation(uiTooling)
        implementation(uiToolingPreview)
        implementation(Deps.Compose.foundationLayout)
        implementation(material)
        implementation(activity)
    }
    with(Deps.Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
        testImplementation(test)
        testImplementation(testJUnit4)
    }
    with(Deps.AndroidX) {
        implementation(lifecycleRuntimeCompose)
        implementation(lifecycleRuntimeKtx)
        implementation(lifecycleViewmodelKtx)
        implementation(activityCompose)
    }
    with(Deps.Test) {
        testImplementation(junit)
        androidTestImplementation(androidXTestJUnit)
        testImplementation(testCore)
        testImplementation(mockito)
        testImplementation(robolectric)
        // Compose testing dependencies
        androidTestImplementation(composeUiTest)
        androidTestImplementation(composeUiTestJUnit)
        implementation(androidXTestJUnitKtx)
        debugImplementation(composeUiTestManifest)
        implementation(Deps.Koin.test)
        implementation(Deps.Kotlinx.coroutinesTest)
        implementation(kotlin("test"))

    }
   }