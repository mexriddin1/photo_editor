package com.example.todoapp.presentaton.editor.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.rememberAsyncImagePainter
import com.example.todoapp.R
import com.example.todoapp.core.ui.theme.MyTheme

@Composable

fun ImageContent(
    data: Data,
    bool: Boolean = false,
    change: (Data) -> Unit,
    click: () -> Unit = {}, delete: () -> Unit
) {
    var scale by remember { mutableFloatStateOf(data.scale) }
    var rotation by remember { mutableFloatStateOf(data.rotate) }
    var offset by remember { mutableStateOf(data.offset) }
    var show: Boolean by remember { mutableStateOf(bool) }

    LaunchedEffect(data) {
        scale = data.scale
        rotation = data.rotate
        offset = data.offset
    }

    LaunchedEffect(bool) {
        show = bool
    }

    Box(
        modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation,
                translationX = offset.x,
                translationY = offset.y,
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        click.invoke()
                    }
                )
            }
            .transformable(state = rememberTransformableState { zoomChange: Float, offsetChange: Offset, rotationChange: Float ->
                scale *= zoomChange
                rotation += rotationChange
                offset += offsetChange
                change.invoke(data.copy(scale = scale, rotate = rotation, offset = offset))
            })
            .zIndex(if (bool) 10f else 2f)
    ) {
        Image(painter = rememberAsyncImagePainter(data.uri!!),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(9.dp)
                .background(Color.Transparent)
                .drawWithContent {
                    drawContent()
                    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(7f, 5f), 0f)
                    drawRoundRect(
                        color = if (show) Color.Gray else Color.Transparent,
                        style = Stroke(width = 2f, pathEffect = pathEffect)
                    )
                }
                .padding(5.dp))

        if (show) {
            Box(modifier = Modifier
                .align(Alignment.TopEnd)
                .size(18.dp)
                .background(MyTheme.colorScheme.neutral7, shape = RoundedCornerShape(5.dp))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    delete.invoke()
                }) {
                Icon(
                    painter = painterResource(R.drawable.trash_bin),
                    contentDescription = null,
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.Center),
                    tint = Color.White
                )
            }
        }
    }
}