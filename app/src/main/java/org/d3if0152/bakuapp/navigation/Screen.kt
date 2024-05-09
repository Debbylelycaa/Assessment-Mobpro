package org.d3if0152.bakuapp.navigation

import org.d3if0152.bakuapp.ui.screen.KEY_ID_BUKU

sealed class Screen (val route: String){
    data object  Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Add: Screen("addScreen")
    data object  FormBaru: Screen("detailScreen")

    data object FormUbah: Screen("detailScreen/{$KEY_ID_BUKU}"){
        fun withId(id:Long) = "detailScreen/$id"
    }
}