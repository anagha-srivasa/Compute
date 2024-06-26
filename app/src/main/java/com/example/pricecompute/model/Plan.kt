package com.example.pricecompute.model

import android.text.format.Time
import java.text.SimpleDateFormat
import java.time.LocalDate

data class Plan(
    val cpuLimit: Int?,
    val gpuLimit: Int?,
    val ssd:Int?,
    val hdd:Int?,
    val expiryDate: LocalDate = LocalDate.now(),
    var isExpired: Boolean = LocalDate.now() > expiryDate
)
