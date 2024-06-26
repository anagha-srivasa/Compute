package com.example.pricecompute.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pricecompute.model.MachineConfig
import com.example.pricecompute.model.Plan
import com.example.pricecompute.ui.theme.PriceComputeTheme

@Composable
fun PlanScreen(
    modifier: Modifier = Modifier,
    machineConfig: MachineConfig,
    onBuyClick: (Long) -> Unit = {},
    discount:(Long)->Long = {0}
    ) {

    var duration by remember {
        mutableStateOf("")
    }
    var buy by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize().background(Color.Black), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedCard(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
            elevation = CardDefaults.outlinedCardElevation(16.dp),
            colors = CardDefaults.outlinedCardColors(containerColor = Color(27, 41, 64), contentColor = Color.White)) {
            Column(
                 modifier = modifier
                .padding(16.dp)
            ) {
                Text(text = machineConfig.machineName,style = MaterialTheme.typography.titleMedium)
                Text(text = machineConfig.desc,style = MaterialTheme.typography.titleMedium)
                Text(text = "price: ${machineConfig.price} per hour",style = MaterialTheme.typography.titleMedium)
                Text(text = "CPU:${machineConfig.plan.cpuLimit}  GPU:${machineConfig.plan.gpuLimit}  SSD:${machineConfig.plan.ssd}  HDD:${machineConfig.plan.hdd}",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = modifier.height(16.dp))

                OutlinedTextField(
                    value =duration ,
                    onValueChange ={duration = it} ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    label = {Text(text = "Duration in days", color = Color.White)},
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White
                    )

                )

                Spacer(modifier = modifier.height(16.dp))

                OutlinedButton(onClick = { buy = true }) {
                    Text(text = "Compute", style = MaterialTheme.typography.titleMedium)
                }

                if (buy) {
                    val price = machineConfig.price*24*duration.toLong() - (machineConfig.price*24*duration.toLong()*(discount(duration.toLong())/100))
                    Text(text = "Estimated price: $price")
                    Spacer(modifier = modifier.height(16.dp))
                    OutlinedButton(onClick = {onBuyClick(duration.toLong())}) {
                        Text(text = "Buy", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PlanScreenPrev() {
    PriceComputeTheme {
        PlanScreen(
            machineConfig = MachineConfig(
                "EC2", "Virtual Servers in the Cloud", 0.023,
                Plan(12, null, 128, 256)
            )
        )
    }
}