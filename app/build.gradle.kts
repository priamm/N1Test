plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")

}

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = "com.priamm.n1test"

        minSdkVersion(19)
        targetSdkVersion(29)

        versionName = "1.0"
        versionCode = 1

        buildToolsVersion = "29.0.2"


        buildTypes {

            getByName("release") {
                isMinifyEnabled = true

                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    file("proguard-rules.pro")
                )
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

androidExtensions {
        isExperimental = true
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.61")
    implementation("androidx.appcompat:appcompat:1.2.0-alpha02")
    implementation("androidx.core:core-ktx:1.3.0-alpha01")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("com.google.android.material:material:1.2.0-alpha05")
    implementation("com.squareup.retrofit2:retrofit:2.7.2")
    implementation("com.squareup.retrofit2:converter-gson:2.7.2")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0-RC8")

    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.paging:paging-runtime:2.1.1")
    implementation("com.google.dagger:dagger-android-support:2.26")
    kapt("com.google.dagger:dagger-android-processor:2.26")
    implementation("com.google.dagger:dagger:2.26")
    kapt("com.google.dagger:dagger-compiler:2.26")
    implementation("androidx.navigation:navigation-fragment:2.3.0-alpha02")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.0-alpha02")
}

