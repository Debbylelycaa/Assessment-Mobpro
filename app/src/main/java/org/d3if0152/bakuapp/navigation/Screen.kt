package org.d3if0152.bakuapp.navigation

sealed class Screen (val route: String){
    data object  Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Add: Screen("addScreen")
}