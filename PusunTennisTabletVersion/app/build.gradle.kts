plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.pusun.pusuntennis"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pusun.pusuntennis"
        minSdk = 23
        targetSdk = 35
        versionCode = 11
        versionName = "2.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    
    // Decompiled app dependencies
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")
    implementation("org.greenrobot:greendao:3.3.0")
    implementation("com.kyleduo.switchbutton:library:2.1.0")
    implementation("com.zhy:okhttputils:2.6.2")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}