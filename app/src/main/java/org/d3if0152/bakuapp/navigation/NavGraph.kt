package org.d3if0152.bakuapp.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if0152.bakuapp.ui.screen.AboutScreen
import org.d3if0152.bakuapp.ui.screen.AddScreen
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
    }
}