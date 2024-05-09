package org.d3if0152.bakuapp.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if0152.bakuapp.ui.screen.AboutScreen
import org.d3if0152.bakuapp.ui.screen.AddScreen
import org.d3if0152.bakuapp.ui.screen.EditScreen
import org.d3if0152.bakuapp.ui.screen.KEY_ID_BUKU
import org.d3if0152.bakuapp.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            MainScreen(navController)
        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }
        composable(route = Screen.Add.route){
            AddScreen(navController)
        }
        composable(route = Screen.Edit.route, arguments = listOf(navArgument(KEY_ID_BUKU){
            type = NavType.LongType}
        )
        ){navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_BUKU)
            EditScreen(navController, id)
        }
    }
}