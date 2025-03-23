package com.example.todoapp.presentaton.editor.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.core.ui.theme.MyTheme

@Composable
fun ButtonBarIcon(
    @DrawableRes icon: Int, title: String, click: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 15.dp, bottom = 21.dp)
            .clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                click.invoke()
            }
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = MyTheme.colorScheme.neutral6,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = title, style = MyTheme.typography.body3, color = Color.White
        )
    }
}