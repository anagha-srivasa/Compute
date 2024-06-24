package com.example.pricecompute.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pricecompute.model.Plan

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier=modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(27, 41, 64),
                contentColor = Color.White
            )
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Current Plan", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = modifier.height(16.dp))
                Row {
                    Text(
                        text = "RAM: ${Plan.currentPlan.cpuLimit}",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.width(100.dp))
                    Text(
                        text = "GPU: ${Plan.currentPlan.gpuLimit}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text(
                        text = "SSD: ${Plan.currentPlan.ssd}",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.width(100.dp))
                    Text(
                        text = "HDD: ${Plan.currentPlan.hdd}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(27, 41, 64),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Upgrade plan", style = MaterialTheme.typography.headlineMedium)
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Home() {
    HomeScreen()
}