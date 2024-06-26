package com.example.pricecompute.model

import java.time.LocalDate

data class Plan(
    val cpuLimit: Int?,
    val gpuLimit: Int?,
    val ssd:Int?,
    val hdd:Int?,
    var expiryDate: LocalDate = LocalDate.now(),
    val isExpired: Boolean = LocalDate.now() > expiryDate
)
