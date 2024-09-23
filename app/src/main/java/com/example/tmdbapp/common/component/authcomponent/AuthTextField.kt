package com.example.tmdbapp.common.component.authcomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.tmdbapp.feature.auth.data.model.RegisterEvent
import com.example.tmdbapp.feature.auth.data.model.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(labelText: String, stateString:String,onValueChange:(String)->Unit, textFieldSize: Float = 0.9f) {

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.9f),
        value = stateString,
        onValueChange = {
           onValueChange(it)
        },
        label = {
            Text(text = labelText)
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            disabledPlaceholderColor = Color.White,
            focusedPlaceholderColor = Color.White,
            unfocusedPlaceholderColor = Color.White,
            disabledLabelColor = Color.White,
            disabledBorderColor = Color.White,
            cursorColor = Color.White,
            disabledTrailingIconColor = Color.White,
            disabledLeadingIconColor = Color.White
        ))



}