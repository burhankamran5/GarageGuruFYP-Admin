package com.bkcoding.garagegurufyp_admin.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bkcoding.garagegurufyp_admin.R

@Composable
fun NameTextFields(placeholder: String, textValue: String, trailingIcon:  @Composable (() -> Unit)?, onTextChange: (String) -> Unit){
    TextField(
        value = textValue,
        onValueChange = {
            onTextChange(it)
        },
        placeholder = {
            Text(
                text = placeholder,
                color = colorResource(id = R.color.spanish_gray),
            )
        },
        textStyle = TextStyle(Color.Black),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        trailingIcon =  trailingIcon,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(15.dp))
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(15.dp)
            )
    )
}