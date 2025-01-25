package com.example.cardsscore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardsscore.ui.theme.CardsScoreTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardsScoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppLayout(modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                    )
                }
            }
        }
    }
}



@Composable
fun AppLayout(modifier: Modifier = Modifier) {
    // State to track player information
    val playerInfo = remember { mutableStateMapOf<String, Int>() }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Add player UI
        AddPlayer(playerInfo)

        Spacer(modifier = Modifier.height(16.dp))

        // Display the mutable scoreboard
        MutableScoreBoard(info = playerInfo)
    }
}

@Composable
fun AddPlayer(
    info: MutableMap<String, Int>, // MutableMap for dynamic updates
) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var playerName by remember { mutableStateOf(TextFieldValue("")) }

    fun addPlayer(name: String) {
        if (name.isNotEmpty()) {
            info[name] = 0 // Initialize the new player with a score of 0
        }
        playerName = TextFieldValue("") // Clear input
        isDialogOpen = false // Close dialog
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = { isDialogOpen = true }) {
            Text("Add Player")
        }

        if (isDialogOpen) {
            AlertDialog(
                onDismissRequest = { isDialogOpen = false },
                title = { Text("Enter Player Name") },
                text = {
                    TextField(
                        value = playerName,
                        onValueChange = { playerName = it },
                        singleLine = true
                    )
                },
                confirmButton = {
                    Button(onClick = { addPlayer(playerName.text) }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { isDialogOpen = false }) {
                        Text("Cancel")
                    }
                },
                properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
            )
        }
    }
}

@Composable
fun MutableScoreBoard(
    info: MutableMap<String, Int>, // MutableMap for dynamic updates
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        info.forEach { (key, value) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = key,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = value.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardsScoreTheme {
        AppLayout()
    }
}