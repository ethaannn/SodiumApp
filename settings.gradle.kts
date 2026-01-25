pluginManagement {
    repositories {
        maven { url = uri(path = "https://www.jitpack.io") }
        maven { url = uri(path = "https://maven.aliyun.com/repository/google/") }
        maven { url = uri(path = "https://maven.aliyun.com/repository/public/") }
        maven { url = uri(path = "https://maven.aliyun.com/repository/central/") }
        maven { url = uri(path = "https://maven.aliyun.com/repository/spring/") }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri(path = "https://www.jitpack.io") }
        maven { url = uri(path = "https://maven.aliyun.com/repository/google/") }
        maven { url = uri(path = "https://maven.aliyun.com/repository/public/") }
        maven { url = uri(path = "https://maven.aliyun.com/repository/central/") }
        maven { url = uri(path = "https://maven.aliyun.com/repository/spring/") }

        google()
        mavenCentral()
    }
}

rootProject.name = "SodiumApp"
include(":app")
include(":compose-ui")
include(":theme")
