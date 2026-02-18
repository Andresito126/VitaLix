    package com.jujus.vitalix

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import com.jujus.vitalix.core.di.AppContainer
    import com.jujus.vitalix.core.navigation.NavigationWrapper
    import com.jujus.vitalix.core.ui.theme.VitaLixTheme
    import com.jujus.vitalix.features.home.di.HomeModule
    import com.jujus.vitalix.features.home.navigation.HomeNavGraph
    import com.jujus.vitalix.features.medications.di.MedicationsModule
    import com.jujus.vitalix.features.medications.navigation.MedicationsNavGraph
    import com.jujus.vitalix.features.medications.presentation.screens.MedicationScreen

    class MainActivity : ComponentActivity() {
        lateinit var appContainer: AppContainer

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            appContainer = AppContainer(this)

            val medicationsModule = MedicationsModule(appContainer)
            val homeModule = HomeModule(appContainer)
            enableEdgeToEdge()
            val navGraphs = listOf(
                MedicationsNavGraph(medicationsModule),
                HomeNavGraph(homeModule)
            )
            setContent {
                VitaLixTheme {
                    NavigationWrapper(navGraphs)
                }
            }
        }
    }
