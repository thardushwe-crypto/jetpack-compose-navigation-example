package com.td.testchangescreeneasy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToDetailWithParam: (Int, String) -> Unit,
    onNavigateToDetailWithoutParam: () -> Unit,
    onNavigateToDetailWithParamString: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            onNavigateToDetailWithParam(101, "Aung Aung")
        }) {
            Text("Detail Screen သို့သွားမည် (ID: 101, Name: Aung Aung)")
        }

        Button(onClick = {
            onNavigateToDetailWithoutParam()
        }) { Text("Nice")}

        Button(onClick = {
            onNavigateToDetailWithParamString("Ok")
        }) { Text("Detail To Go With OK")}
    }
}