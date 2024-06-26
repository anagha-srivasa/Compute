package com.example.pricecompute.provider

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pricecompute.model.MachineConfig
import com.example.pricecompute.screens.ai.ChatMessage
import com.example.pricecompute.screens.ai.ChatUiState
import com.example.pricecompute.screens.ai.Participant
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerationConfig
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ComputeViewModel:ViewModel() {

    private val currentMachine:MutableStateFlow<MachineConfig> = MutableStateFlow(MachineConfig.currentMachine)

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = "AIzaSyBC2NOarS10QxveyVYgAkhG4ESL5UfCaTA",
        generationConfig = generationConfig { temperature = 0.7f }
    )

    private val chat = generativeModel.startChat()

    private val _uiState:MutableStateFlow<ChatUiState> = MutableStateFlow(
        ChatUiState(
            messages = listOf(
                ChatMessage(
                    text = "Hi, I am gemini, how may I help you?",
                    participant = Participant.MODEL,
                    isPending = false
                )
            )
        )
    )
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()


    fun sendMessage(userMsg:String){
        // add pending message
        _uiState.value.addMessage(
            ChatMessage(
                text = userMsg,
                participant = Participant.USER,
                isPending = true
            )
        )

        viewModelScope.launch {
            try {
                val response = chat.sendMessage(userMsg)
                _uiState.value.replaceLastPendingMessage()

                response.text?.let {msg->
                    _uiState.value.addMessage(
                        ChatMessage(
                            text = msg,
                            participant = Participant.MODEL,
                            isPending = false
                        )
                    )
                }

            }
            catch (e:Exception){
                _uiState.value.replaceLastPendingMessage()
                _uiState.value.addMessage(
                    ChatMessage(
                        text = e.message?:"Unknown error has occurred",
                        participant = Participant.ERROR,
                        isPending = false
                    )
                )
            }
        }
    }

    fun changePlan(machineConfig: MachineConfig){
        currentMachine.value = machineConfig
    }

}