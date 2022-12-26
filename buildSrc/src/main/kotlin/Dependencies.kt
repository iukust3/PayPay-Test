import Versions.ktor

object Versions {
    const val minSdk = 21
    private const val compileSdk = 32
    const val targetSdk= compileSdk

    const val kotlinCoroutines = "1.6.4"
    const val koin = "3.3.0"
    const val ktor = "2.2.1"

    // Note: v1.3.2 produces iOS achiving issues
    const val kotlinxSerialization = "1.4.1"

    const val compose = "1.3.2"
    const val composeCompiler = "1.3.2"

    const val junit = "4.13.2"
    const val androidXTestJUnit = "1.1.4"
    const val testCore = "1.5.0"
    const val exprsso = "3.5.0"

    const val mockito = "4.5.1"
    const val sqlDelight = "1.5.3"

    const val material = "1.3.1"
    const val activityCompose = "1.6.1"
    const val lifecycleKtx = "2.6.0-alpha01"
    const val lifecycleRuntimeKtx = lifecycleKtx
    const val lifecycleViewmodelKtx = lifecycleKtx
    const val slf4j = "1.7.30"
    const val logback = "1.2.3"
    const val kermit = "1.0.0"

    const val gradleVersionsPlugin = "0.39.0"
}

object Deps {
    object Gradle {
         const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionsPlugin}"
    }

    object Kotlinx {
        const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"
   }

    object Android {
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object AndroidX {
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
        const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewmodelKtx}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val metrics = "androidx.metrics:metrics-performance:1.0.0-alpha01"
        const val testEspressoCore = "androidx.test.espresso:espresso-core:${Versions.exprsso}"
        const val testExtJunit = "androidx.test.ext:junit:${Versions.junit}"
        const val testUiautomator = "androidx.test.uiautomator:uiautomator:2.3.0-alpha01"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val androidXTestJUnit = "androidx.test.ext:junit:${Versions.androidXTestJUnit}"
        const val androidXTestJUnitKtx = "androidx.test.ext:junit-ktx:${Versions.androidXTestJUnit}"
        const val testCore = "androidx.test:core:${Versions.testCore}"
        const val composeUiTest = "androidx.compose.ui:ui-test:${Versions.compose}"
        const val composeUiTestJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
       // const val exprsso = "androidx.test.espresso:espresso-core:${Versions.exprsso}"
        const val mockito = "org.mockito:mockito-inline:${Versions.mockito}"
    }

    object Compose {
        const val compiler = "androidx.compose.compiler:compiler:${Versions.composeCompiler}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
      //  const val foundation= "androidx.compose.foundation:foundation:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val testJUnit4 = "io.insert-koin:koin-test-junit4:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Ktor {
        const val serverCore = "io.ktor:ktor-server-core:$ktor"
        const val serverNetty = "io.ktor:ktor-server-netty:$ktor"
        const val serverCors = "io.ktor:ktor-server-cors:$ktor"

        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:$ktor"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:$ktor"

        const val serverContentNegotiation = "io.ktor:ktor-server-content-negotiation:$ktor"

        const val clientCore = "io.ktor:ktor-client-core:$ktor"
        const val clientJson = "io.ktor:ktor-client-json:$ktor"
        const val clientLogging = "io.ktor:ktor-client-logging:$ktor"
        const val clientSerialization = "io.ktor:ktor-client-serialization:$ktor"
        const val clientCio = "io.ktor:ktor-client-cio:$ktor"

        const val clientAndroid = "io.ktor:ktor-client-android:$ktor"
        const val clientJava = "io.ktor:ktor-client-java:$ktor"
        const val clientDarwin = "io.ktor:ktor-client-darwin:${Versions.ktor}"
        const val clientJs = "io.ktor:ktor-client-js:$ktor"
        const val auth = "io.ktor:ktor-server-auth:$ktor"
        const val authJwt = "io.ktor:ktor-server-auth-jwt:$ktor"
    }

    object SqlDelight {
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val coroutineExtensions = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
        const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
        const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        const val nativeDriverMacos = "com.squareup.sqldelight:native-driver-macosx64:${Versions.sqlDelight}"
        const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}"
    }



    object Ok {
        const val okhttp = "com.squareup.okhttp3:okhttp:4.9.2"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.2"
    }

    object Log {
        const val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
        const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
        const val kermit = "co.touchlab:kermit:${Versions.kermit}"
    }

    object Glance {
        const val tiles = "androidx.glance:glance-wear-tiles:1.0.0-alpha03"
        const val appwidget = "androidx.glance:glance-appwidget:1.0.0-alpha03"
    }

    object Horologist {
        const val composeLayout = "com.google.android.horologist:horologist-compose-layout:0.0.17"
    }
}