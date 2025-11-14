plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.week10activity"
    compileSdk = 36

    // 移除重複的 buildFeatures 區塊，或確保它們正確合併
    buildFeatures {
        viewBinding = true // 舊程式碼可能需要
        compose = true     // 啟用 Compose
    }

    defaultConfig {
        applicationId = "com.example.week10activity"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

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
    kotlinOptions {
        jvmTarget = "11"
    }
    // 已經在上方合併或應該移除這個重複區塊
    /* buildFeatures {
        compose = true
    } */
}

dependencies {

    // 原有的依賴項
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // ⬇️ 關鍵新增：Compose Navigation 導航 (必需)
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ⬇️ 關鍵新增：ViewModel Compose 整合 (必需)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")

    // 測試依賴項
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}