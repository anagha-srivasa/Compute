package com.example.pricecompute.provider

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class ComputeViewModel(
    private val apiKey:String
):ViewModel() {
    var response by mutableStateOf("")

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = apiKey
    )

    fun generatePrompt(prompt:String){
        viewModelScope.launch{
            response = try{
                generativeModel.generateContent(prompt).text ?: "no output was found"
            }catch (e:Exception){
                e.localizedMessage?:"Unknown error"
            }
        }
    }
}