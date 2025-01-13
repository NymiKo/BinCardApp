package com.example.bincardapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bincardapp.features.bin_history.ui.BinHistoryRoute
import com.example.bincardapp.features.bin_history.ui.BinHistoryScreen
import com.example.bincardapp.features.bin_lookup.ui.BinLookupRoute
import com.example.bincardapp.features.bin_lookup.ui.BinLookupScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = BinLookupRoute,
    ) {
        composable<BinLookupRoute> {
            BinLookupScreen(
                onBinHistoryScreen = { navController.navigate(BinHistoryRoute) }
            )
        }
        composable<BinHistoryRoute> {
            BinHistoryScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}