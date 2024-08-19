package com.example.unit_converter_rev

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.unit_converter_rev.ui.theme.Unit_Converter_revTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Select") }
    var isExpanded by remember { mutableStateOf(false) }
    var conversionFactor by remember { mutableStateOf(0.0) }
    var result by remember { mutableStateOf("0.0") }

    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        if (inputValueDouble >= 0) {
            val convertedValue = when (inputUnit) {
                "Celsius to Fahrenheit" -> ((inputValueDouble * 1.8 + 32) * 100.0).roundToInt() / 100.0
                "Fahrenheit to Celsius" -> (((inputValueDouble - 32) * 5 / 9) * 100.0).roundToInt() / 100.0
                else -> (inputValueDouble * conversionFactor * 100.0).roundToInt() / 100.0
            }
            result = convertedValue.toString()
        } else {
            result = "0.0"
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnit()
            },
            label = { Text("Enter the Value")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = !isExpanded }) {
            TextField(
                value = inputUnit,
                onValueChange = { inputUnit = it },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                modifier = Modifier.menuAnchor(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent)

            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                DropdownMenuItem(
                    text = {Text ("Centimeter to Inches") },
                    onClick = {
                        inputUnit = "Centimeter to Inch"
                        isExpanded = false
                        conversionFactor = 1.0 / 2.54
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text("Inches to Centimeters") },
                    onClick = {
                        inputUnit = "Centimeter to Inch"
                        isExpanded = false
                        conversionFactor = 2.54
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text("Meters to Feet") },
                    onClick = {
                        isExpanded = false
                        inputUnit = "Meters to Feet"
                        conversionFactor = 3.28084 // conversion factor for Meters to Feet
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text("Feet to Meters") },
                    onClick = {
                        isExpanded = false
                        inputUnit = "Feet to Meters"
                        conversionFactor = 0.3048 // conversion factor for Feet to Meters
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text ("Kilometers to Miles") },
                    onClick = {
                        isExpanded = false
                        inputUnit = "Kilometers to Miles"
                        conversionFactor = 0.621371 // conversion factor for Feet to Meters
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text("Miles to Kilometers") },
                    onClick = {
                        isExpanded = false
                        inputUnit = "Miles to Kilometers"
                        conversionFactor = 1.609344 // conversion factor for Feet to Meters
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text("Grams to Ounces") },
                    onClick = {
                        isExpanded = false
                        inputUnit = "Grams to Ounces"
                        conversionFactor = 0.035274 // conversion factor for Feet to Meters
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text ("Ounces to Grams") },
                    onClick = {
                        isExpanded = false
                        inputUnit = "Ounces to Grams"
                        conversionFactor = 28.3495 // conversion factor for Feet to Meters
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text("Kilograms to Pounds") },
                    onClick = {
                        isExpanded = false
                        inputUnit = "Kilograms to Pounds"
                        conversionFactor = 2.20462 // conversion factor for Feet to Meters
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text("Pounds to Kilograms") },
                    onClick = {
                        isExpanded = false
                        inputUnit = "Pounds to Kilograms"
                        conversionFactor = 0.45359237 // conversion factor for Feet to Meters
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text("Celsius to Fahrenheit") },
                    onClick = {
                        isExpanded = false
                        inputUnit = "Celsius to Fahrenheit"
                        convertUnit()
                    })
                DropdownMenuItem(
                    text = {Text("Fahrenheit to Celsius") },
                    onClick = {
                        isExpanded = false
                        inputUnit = "Fahrenheit to Celsius"
                        convertUnit()
                    })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $result", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListPreview() {
    Unit_Converter_revTheme {
        DropDownTest()
    }
}