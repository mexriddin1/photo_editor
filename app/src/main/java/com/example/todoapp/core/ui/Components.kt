package com.example.todoapp.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W800
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.core.ui.theme.MyTheme

@Composable
fun AppButton(
    text: String,
    modifier: Modifier,
    click: () -> Unit
) {
    Button(
        { click.invoke() },
        modifier = modifier
            .height(44.dp),
        colors = ButtonColors(
            containerColor = MyTheme.colorScheme.primary1,
            contentColor = MyTheme.colorScheme.neutral1,
            disabledContainerColor = MyTheme.colorScheme.neutral1,
            disabledContentColor = MyTheme.colorScheme.neutral1,
        ),
        shape = MyTheme.shape.large
    ) {

        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = W800
            ),
            color = MyTheme.colorScheme.neutral1
        )
    }
}

@Composable
fun AppInput(
    listener: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    BasicTextField(
        value = text, onValueChange = {
            text = it
            listener.invoke(text)
        },
        textStyle = MyTheme.typography.body3,
        modifier = Modifier
            .background(Color.Transparent, shape = MyTheme.shape.large)
            .border(1.dp, MyTheme.colorScheme.neutral4, shape = MyTheme.shape.large)
            .height(44.dp),
        singleLine = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Surface(modifier = Modifier.align(Alignment.CenterStart), color = Color.Unspecified) {
                it()
            }
        }
    }
}

