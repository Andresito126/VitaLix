package com.jujus.vitalix.features.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jujus.vitalix.core.navigation.FeatureNavGraph
import com.jujus.vitalix.core.navigation.Home
import com.jujus.vitalix.features.home.di.HomeModule
import com.jujus.vitalix.features.home.presentation.HomeScreen

class HomeNavGraph (private val homeModule: HomeModule): FeatureNavGraph {
    override fun registerGraph(navGraphBuilder : NavGraphBuilder, navController: NavHostController){
        navGraphBuilder.composable<Home>{
            HomeScreen(
                navController
            )
        }
    }
}