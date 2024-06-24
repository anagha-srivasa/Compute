package com.example.pricecompute.model

data class Plan(
    val cpuLimit: Int?,
    val gpuLimit: Int?,
    val ssd:Int?,
    val hdd:Int?
){
    companion object{
        val currentPlan = Plan(
            12, null, null, 128
        )
    }
}
