package com.jujus.vitalix.features.legalnotice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jujus.vitalix.core.navigation.FeatureNavGraph
import com.jujus.vitalix.core.navigation.Info
import com.jujus.vitalix.features.legalnotice.di.LegalNoticeModule
import com.jujus.vitalix.features.legalnotice.presentation.screens.LegalNoticeScreen


class InfoNavGraph (private val legalNoticeModule: LegalNoticeModule) : FeatureNavGraph {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<Info> {
            LegalNoticeScreen(navController = navController)
        }
    }
}

