package com.ae.designsystem.sample.docs.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.checkbox.AECheckbox
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.radio.AERadioButton
import com.ae.designsystem.components.ui.slider.AESlider
import com.ae.designsystem.components.ui.switch.AESwitch
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.components.ui.textfield.AETextField
import com.ae.designsystem.components.ui.textfield.AETextFieldState
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme

@Composable
internal fun InputsCard(modifier: Modifier = Modifier) {
    val spacing = AETheme.spacing

    var text      by remember { mutableStateOf("") }
    var checked1  by remember { mutableStateOf(true) }
    var switchOn  by remember { mutableStateOf(true) }
    var radioSel  by remember { mutableStateOf("Option A") }
    var sliderVal by remember { mutableFloatStateOf(0.4f) }

    AECard(modifier = modifier, header = { AEText("Form Inputs", style = AETheme.typography.headingSmall) }) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xl)) {
            TextFieldsGroup(text = text, onTextChange = { text = it })
            AEDivider()
            CheckboxGroup(checked = checked1, onCheckedChange = { checked1 = it })
            AEDivider()
            SwitchGroup(checked = switchOn, onCheckedChange = { switchOn = it })
            AEDivider()
            RadioGroup(selected = radioSel, onSelect = { radioSel = it })
            AEDivider()
            SliderGroup(value = sliderVal, onValueChange = { sliderVal = it })
        }
    }
}

@Composable
private fun TextFieldsGroup(text: String, onTextChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md)) {
        AEText("Text Fields", style = AETheme.typography.headingSmall)
        AETextField(value = text, onValueChange = onTextChange, label = "Username", placeholder = "Enter username", leadingIcon = AEIcons.User)
        AETextField(value = "", onValueChange = {}, label = "Error Field", placeholder = "Invalid input", state = AETextFieldState.Error, supportingText = "Required")
        AETextField(value = "Disabled", onValueChange = {}, label = "Disabled", enabled = false)
    }
}

@Composable
private fun CheckboxGroup(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
        AEText("Checkboxes", style = AETheme.typography.headingSmall)
        Row(horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xl)) {
            AECheckbox(checked = checked, onCheckedChange = onCheckedChange, label = "Accept terms")
            AECheckbox(checked = true, onCheckedChange = {}, label = "Disabled", enabled = false)
        }
    }
}

@Composable
private fun SwitchGroup(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
        AEText("Switches", style = AETheme.typography.headingSmall)
        AESwitch(checked = checked, onCheckedChange = onCheckedChange, label = "Notifications")
    }
}

@Composable
private fun RadioGroup(selected: String, onSelect: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
        AEText("Radio Buttons", style = AETheme.typography.headingSmall)
        Row(horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xl)) {
            listOf("Option A", "Option B", "Option C").forEach { opt ->
                AERadioButton(selected = selected == opt, onClick = { onSelect(opt) }, label = opt)
            }
        }
    }
}

@Composable
private fun SliderGroup(value: Float, onValueChange: (Float) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
        AEText("Slider — ${(value * 100).toInt()}%", style = AETheme.typography.headingSmall)
        AESlider(value = value, onValueChange = onValueChange)
    }
}
