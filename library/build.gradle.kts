import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.vanniktech.mavenPublish)
}

val libraryName = "Turbawself"
val idLibraryName = libraryName.lowercase()

group = "ink.literate"

version = "1.0.0"

kotlin {
  jvm()
  androidTarget {
    publishLibraryVariants("release")
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions { jvmTarget.set(JvmTarget.JVM_1_8) }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(libs.ktor.client.core)
        implementation(libs.ktor.client.okhttp)

        implementation(libs.kotlinx.serialization.json)
        implementation(libs.kotlinx.serialization.core)

        implementation(libs.kotlinx.datetime)
      }
    }
    val commonTest by getting { dependencies { implementation(libs.kotlin.test) } }
  }
}

android {
  namespace = "org.jetbrains.kotlinx.multiplatform.library.template"
  compileSdk = libs.versions.android.compileSdk.get().toInt()
  defaultConfig { minSdk = libs.versions.android.minSdk.get().toInt() }
}
