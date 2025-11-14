// SetBirthdayScreen.kt
package com.example.week10activity.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun SetBirthdayScreen(
    navController: NavController,
    viewModel: ZodiacViewModel = viewModel()
) {
    val context = LocalContext.current
    var monthInput by remember { mutableStateOf("12") }
    var dayInput by remember { mutableStateOf("12") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 提示文字 (取代 R.string.input_prompt)
        Text(
            text = PROMPT_TEXT,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = monthInput,
            onValueChange = { monthInput = it.filter { it.isDigit() }.take(2) }, // 限制只輸入數字
            label = { Text(MONTH_LABEL) }, // 取代 R.string.month
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dayInput,
            onValueChange = { dayInput = it.filter { it.isDigit() }.take(2) }, // 限制只輸入數字
            label = { Text(DAY_LABEL) }, // 取代 R.id.day (假設您指的是 Label)
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val intMonth = monthInput.toIntOrNull()
                val intDay = dayInput.toIntOrNull()

                // 驗證邏輯
                if (intMonth == null || intDay == null || intMonth < 1 || intMonth > 12 || intDay < 1 || intDay > 31) {
                    // 錯誤提示 (取代 R.string.input_error)
                    Toast.makeText(context, INPUT_ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.updateBirthday(intMonth, intDay)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = GET_ZODIAC_BUTTON) // 取代 R.string.get_zodiac
        }
    }
}