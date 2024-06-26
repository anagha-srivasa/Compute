package com.example.pricecompute.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pricecompute.model.MachineConfig
import com.example.pricecompute.provider.ComputeViewModel


@Composable
fun ScreenFlow(
    viewModel: ComputeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "home") {
        composable("home"){
            HomeScreen(onUpgradeClick = {
                navController.navigate("upg")},
                onFabClick = {
                    navController.navigate("ai")
                }
            )
        }
        composable("upg"){
            UpgradeScreen(onMachineClick = {
                MachineConfig.currentMachine = it
                navController.navigate("plan")
            }
            )
        }
        composable("plan"){
            PlanScreen(
                machineConfig = MachineConfig.currentMachine,
                onBuyClick = {
                    viewModel.changePlan(MachineConfig.currentMachine)
                    Toast.makeText(
                        context,
                        "Purchased",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate("home")
                }
            )
        }
        composable("ai"){
            ChatScreen()
        }
    }
}

