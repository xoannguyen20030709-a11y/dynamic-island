package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        MainScreen()
      }
    }
  }
}

@Composable
fun MainScreen() {
    var islandState by remember { mutableStateOf(IslandState.IDLE) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Main Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
             item { Spacer(modifier = Modifier.height(100.dp)) }
             item {
                 Text(
                     text = "Welcome to Dynamic Island AI",
                     style = MaterialTheme.typography.headlineMedium,
                     fontWeight = FontWeight.Bold,
                     color = MaterialTheme.colorScheme.onBackground
                 )
                 Spacer(modifier = Modifier.height(32.dp))
             }
             items(20) { index ->
                 Box(modifier = Modifier.fillMaxWidth().padding(16.dp).height(60.dp).background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)) {
                     Text("List Item $index", modifier = Modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.onSurfaceVariant)
                 }
             }
        }

        // Dynamic Island
        DynamicIsland(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            islandState = islandState,
            onStateChange = { islandState = it }
        )
    }
}

