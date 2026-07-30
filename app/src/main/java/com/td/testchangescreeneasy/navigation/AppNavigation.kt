package com.td.testchangescreeneasy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.td.testchangescreeneasy.ui.screens.DetailScreen
import com.td.testchangescreeneasy.ui.screens.HomeScreen
import com.td.testchangescreeneasy.ui.theme.NiceScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        // Home Screen
        composable<HomeRoute> {
            HomeScreen(
                onNavigateToDetailWithParam = { id, name ->
                    // Parameter ပါတဲ့ နေရာကနေ ခေါ်ချင်ရင်
                    navController.navigate(DetailRoute(id = id, name = name))
                },
                onNavigateToDetailWithoutParam = {
                    //  Parameter မပါဘဲ အလွတ်ခေါ်ချင်ရင် ဒီလိုသွားပါ
                    navController.navigate(DetailRoute())
                },
                onNavigateToDetailWithParamString = { name ->
                    navController.navigate(NiceDetailRoute(name = name))
                }
            )
        }

        // Detail Screen
        composable<DetailRoute> { backStackEntry ->
            val args = backStackEntry.toRoute<DetailRoute>()

            DetailScreen(
                id = args.id,
                name = args.name,
                onBack = { navController.popBackStack() }
            )
        }
        composable<NiceDetailRoute> { backStackEntry ->
            val args = backStackEntry.toRoute<NiceDetailRoute>()

            NiceScreen(name = args.name,onBack = { navController.popBackStack() })
        }
    }
}