pluginManagement {
    includeBuild("build-logic")
    repositories {
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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "android-clean-architecture-reference-implementation"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":core:design-system")
include(":core:ui")
include(":core:data")
include(":core:cache")
include(":core:model:apiresponse")
include(":core:model:entity")
include(":core:domain")
include(":common")
include(":core:di")
include(":feature-product:products")

include(":feature-common:notification")
include(":feature-credential:login")
include(":feature-credential:splash")
include(":library:sharedpref")
include(":library:secure-storage")
include(":feature-common:profile")
include(":feature-cart:carts")
