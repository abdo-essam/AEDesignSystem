package com.ae.designsystem.sample.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun QuickNoteExample() {
    var noteText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Personal") }

    Card(
        modifier = Modifier.fillMaxWidth(),
        label = "Quick Note",
    ) {
        CardHeader {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Quick Note", variant = TextVariant.H4)
                Kbd(text = "\u2318N")
            }
        }

        CardContent {
            Textarea(
                value = noteText,
                onValueChange = { noteText = it },
                placeholder = "Write something…",
                label = "Note",
                modifier = Modifier.fillMaxWidth(),
            )
        }

        CardFooter {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ToggleGroup {
                    ToggleGroupItem(
                        text = "Personal",
                        selected = selectedCategory == "Personal",
                        onClick = { selectedCategory = "Personal" },
                    )
                    ToggleGroupItem(
                        text = "Work",
                        selected = selectedCategory == "Work",
                        onClick = { selectedCategory = "Work" },
                    )
                    ToggleGroupItem(
                        text = "Ideas",
                        selected = selectedCategory == "Ideas",
                        onClick = { selectedCategory = "Ideas" },
                    )
                }
                Button(text = "Save", onClick = { }, size = ButtonSize.Sm)
            }
        }
    }
}
