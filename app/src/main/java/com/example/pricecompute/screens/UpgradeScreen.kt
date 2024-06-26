package com.example.pricecompute.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pricecompute.model.MachineConfig
import com.example.pricecompute.ui.theme.PriceComputeTheme

@Composable
fun UpgradeScreen(
    modifier: Modifier = Modifier,
    onMachineClick: (MachineConfig) -> Unit = {}
) {

    LazyColumn(modifier=modifier.fillMaxSize().background(Color.Black)) {
        items(MachineConfig.machineList){
           MachineCard(machineConfig = it) {conf->
               onMachineClick(conf)
           }
        }
    }
}

@Composable
fun MachineCard(
    modifier: Modifier = Modifier,
    machineConfig: MachineConfig,
    onMachineClick: (MachineConfig) -> Unit
) {
    Column(modifier = modifier
        .padding(16.dp).background(Color.Black)
        .clickable { onMachineClick(machineConfig) }) {
        Text(text = machineConfig.machineName,style = MaterialTheme.typography.titleMedium, color = Color.White)
        Text(text = machineConfig.desc,style = MaterialTheme.typography.titleMedium, color = Color.White)
        Text(text = "price: ${machineConfig.price} per hour",style = MaterialTheme.typography.titleMedium, color = Color.White)
        Text(text = "CPU:${machineConfig.plan.cpuLimit}  GPU:${machineConfig.plan.gpuLimit}  SSD:${machineConfig.plan.ssd}  HDD:${machineConfig.plan.hdd}",
            style = MaterialTheme.typography.titleMedium, color = Color.White
        )
        HorizontalDivider()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun UpgradeScreenPrev() {
    PriceComputeTheme {
        UpgradeScreen()
    }
}