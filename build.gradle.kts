// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hiltAndroid) apply false
    alias(libs.plugins.kotlinAndroidKsp) apply false

    id("androidx.navigation.safeargs.kotlin") version "2.8.4" apply false
    id("androidx.room") version "2.6.1" apply false
    alias(libs.plugins.android.library) apply false
}