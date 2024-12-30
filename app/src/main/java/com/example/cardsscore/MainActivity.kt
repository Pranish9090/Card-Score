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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardsscore.data.PlayerInfo
import com.example.cardsscore.ui.theme.CardsScoreTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
    //var playerInfo = PlayerInfo()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField()
    }
}

@Composable
fun InputField() {
    var isChecked by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Your other content here...

        // Toggle with text input in a popup
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Enable Feature:")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
            )

            // Show text input popup if toggle is checked
            if (isChecked) {
                var showPopup by remember { mutableStateOf(true) } // Initially show
                if (showPopup) {
                    AlertDialog(
                        onDismissRequest = { showPopup = false },
                        title = { Text("Enter Text") },
                        text = {
                            TextField(
                                value = text,
                                onValueChange = { text = it },
                                singleLine = true
                            )
                        },
                        confirmButton = {
                            Button(onClick = { showPopup = false }) {
                                Text("OK")
                            }
                        },
                        properties = DialogProperties(dismissOnBackPress = true) // Allow focus
                    )
                }
            }
        }
    }
}

@Composable
fun AddPlayer() {

}

@Composable
fun NameBox(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        value ="",
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        label = { Text(stringResource(R.string.name)) }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardsScoreTheme {
        AppLayout()
    }
}