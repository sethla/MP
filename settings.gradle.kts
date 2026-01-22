pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()      // <--- Zorg dat deze er staat!
        mavenCentral()
    }
}

rootProject.name = "MyApplication4"
include(":app")
