package com.ae.designsystem.sample.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.checkbox.AECheckbox
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun FeedbackFormExample() {
    var rating by remember { mutableStateOf(0) }
    var feedbackText by remember { mutableStateOf("") }
    var contactMe by remember { mutableStateOf(false) }

    Card {
        CardHeader {
            Text(text = "Rate Your Experience", variant = TextVariant.H4)
        }

        CardContent {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md)) {
                Row(horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xs)) {
                    for (star in 1..5) {
                        Button(
                            onClick = { rating = star },
                            variant = ButtonVariant.Ghost,
                            size = ButtonSize.Sm,
                        ) {
                            AEIcon(
                                token = AEIcons.Star,
                                contentDescription = "Rate $star stars",
                                tint = if (star <= rating) AETheme.colors.accent else AETheme.colors.textMuted,
                            )
                        }
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.xs)) {
                    Label(text = "Tell us more")
                    Textarea(
                        value = feedbackText,
                        onValueChange = { feedbackText = it },
                        placeholder = "What could we improve?",
                        label = "Tell us more",
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                AECheckbox(
                    checked = contactMe,
                    onCheckedChange = { contactMe = it },
                    label = "Contact me about my feedback",
                )

                Button(
                    text = "Submit Feedback",
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
