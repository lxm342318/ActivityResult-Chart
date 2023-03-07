// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        maven{  url = uri("https://maven.aliyun.com/repository/public") }
        maven{  url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven{  url = uri("https://maven.aliyun.com/repository/central") }
        maven{  url = uri("https://maven.aliyun.com/repository/google") }
        mavenCentral()
        maven { url = uri("https://jitpack.io")}
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${ProjectProperties.agpVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects{
    repositories {
        maven{  url = uri("https://maven.aliyun.com/repository/public") }
        maven{  url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven{  url = uri("https://maven.aliyun.com/repository/central") }
        maven{  url = uri("https://maven.aliyun.com/repository/google") }
        mavenCentral()
        maven { url = uri("https://jitpack.io")}
    }
}


tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}