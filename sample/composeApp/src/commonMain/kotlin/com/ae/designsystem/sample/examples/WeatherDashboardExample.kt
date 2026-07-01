package com.ae.designsystem.sample.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.badge.AEBadge
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun WeatherDashboardExample() {
    Card(label = "Weather") {
        CardHeader {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Weather", variant = TextVariant.H4)
                AEBadge(label = "Live", variant = AEBadgeVariant.Success)
            }
        }

        CardContent {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md)) {
                Text(text = "22°C", variant = TextVariant.H1)
                Text(text = "Partly Cloudy", variant = TextVariant.Muted)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    WeatherStat(label = "Humidity", value = "68%")
                    WeatherStat(label = "Wind", value = "14 km/h")
                    WeatherStat(label = "UV Index", value = "5")
                }

                Separator()

                Text(text = "3-Day Forecast", variant = TextVariant.Small)

                Table {
                    TableHeader {
                        TableHeaderCell(text = "Day", modifier = Modifier.weight(1f))
                        TableHeaderCell(text = "Temp", modifier = Modifier.weight(1f))
                        TableHeaderCell(text = "Condition", modifier = Modifier.weight(1f))
                    }
                    TableRow {
                        TableCell(text = "Monday", modifier = Modifier.weight(1f))
                        TableCell(text = "24°C", modifier = Modifier.weight(1f))
                        TableCell(text = "Sunny", modifier = Modifier.weight(1f))
                    }
                    TableRow {
                        TableCell(text = "Tuesday", modifier = Modifier.weight(1f))
                        TableCell(text = "19°C", modifier = Modifier.weight(1f))
                        TableCell(text = "Cloudy", modifier = Modifier.weight(1f))
                    }
                    TableRow {
                        TableCell(text = "Wednesday", modifier = Modifier.weight(1f))
                        TableCell(text = "17°C", modifier = Modifier.weight(1f))
                        TableCell(text = "Rain", modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun WeatherStat(
    label: String,
    value: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
    ) {
        Text(text = label, variant = TextVariant.Muted)
        Text(text = value, variant = TextVariant.Large)
    }
}
