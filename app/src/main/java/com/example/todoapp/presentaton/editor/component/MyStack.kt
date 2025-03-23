package com.example.todoapp.presentaton.editor.component

import android.net.Uri
import androidx.compose.ui.geometry.Offset

sealed interface MyStack {
    data class Add(val data: Data) : MyStack
    data class Update(val data: Data, val index: Int) : MyStack
    data class Remove(val data: Data) : MyStack
}

data class Data(
    val uri: Uri? = null,
    val text: String? = null,
    val selected: Boolean,
    var offset: Offset = Offset(0f, 0f),
    var scale: Float = 1f,
    var rotate: Float = 1f
)