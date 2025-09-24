pluginManagement {
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

rootProject.name = "act_clase_PF"
include(":app")
include(":ft-Foro")
include(":ft-Rank")
include(":ft-Cuentas")
include(":Data-core")
include(":ft-Spalsh")
include(":ft-Comentarios")
include(":ft-FavyLikes")
include(":ft-ConfigCuenta")
include(":PantallaSplah")
