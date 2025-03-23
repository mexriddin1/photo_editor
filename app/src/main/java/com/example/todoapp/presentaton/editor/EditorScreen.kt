package com.example.todoapp.presentaton.editor

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.todoapp.R
import com.example.todoapp.core.ui.AppButton
import com.example.todoapp.core.ui.theme.MyTheme
import com.example.todoapp.presentaton.editor.component.ButtonBarIcon
import com.example.todoapp.presentaton.editor.component.Data
import com.example.todoapp.presentaton.editor.component.EditTextContent
import com.example.todoapp.presentaton.editor.component.ImageContent
import com.example.todoapp.presentaton.editor.component.MyStack
import kotlinx.parcelize.Parcelize
import org.orbitmvi.orbit.compose.collectAsState

@Parcelize
class EditorScreen(private val uri: Uri) : Screen, Parcelable {
    @Composable
    override fun Content() {
        val viewModel: EditorContract.ViewModel = getViewModel<EditorViewModel>()
        val uiState = viewModel.collectAsState()
        EditorScreenContent(uiState, viewModel::onEventDispatcher, uri)
    }
}

val undo = ArrayDeque<MyStack>()
val redo = ArrayDeque<MyStack>()

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun EditorScreenContent(
    uiState: State<EditorContract.UiState> = remember {
        mutableStateOf(
            EditorContract.UiState()
        )
    },
    onEventDispatcher: (intent: EditorContract.Intent) -> Unit = {}, uri: Uri = "".toUri()
) {
    val list = remember { mutableStateListOf<Data>() }
    var seleced by remember { mutableIntStateOf(-1) }
    var show by remember { mutableStateOf(false) }
    fun hide(index: Int) {
        if (seleced != -1) list[seleced] = list[seleced].copy(selected = false)
        list[index] = list[index].copy(selected = true)
        seleced = index
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            list.add(Data(uri = uri, selected = true))
            hide(list.size - 1)
            undo.add(MyStack.Add(Data(uri = uri, selected = true)))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyTheme.colorScheme.neutral1)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .height(60.dp)
                .background(MyTheme.colorScheme.neutral1)
        ) {
            Text(
                "Unnamed 1",
                color = MyTheme.colorScheme.neutral6,
                style = MyTheme.typography.title2,
                modifier = Modifier.padding(start = 15.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            AppButton(
                text = "Save",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .width(80.dp)
            ) {}
        }

        Box(
            modifier = Modifier
                .background(MyTheme.colorScheme.neutral2)
                .fillMaxWidth()
                .weight(1f), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            list.forEachIndexed { index, item ->
                if (item.uri != null) {
                    ImageContent(
                        data = item,
                        bool = item.selected,
                        change = {
                            list[index] = it
                            undo.add(MyStack.Update(it, index))
                        },
                        click = { hide(index) }
                    ) {
                        list.removeAt(index)
                        undo.add(MyStack.Remove(item))
                        if (seleced == index) seleced = -1
                    }
                } else {
                    EditTextContent(
                        data = item,
                        bool = item.selected,
                        change = {
                            list[index] = it
                            undo.add(MyStack.Update(it, index))
                        },
                        click = { hide(index) }
                    ) {
                        list.removeAt(index)
                        undo.add(MyStack.Remove(item))
                        if (seleced == index) seleced = -1
                    }
                }
            }
        }

        if (show) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(MyTheme.colorScheme.neutral2)
            ) {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MyTheme.colorScheme.neutral2)
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    item {
                        Spacer(
                            modifier = Modifier.width(5.dp)
                        )
                    }

                    items(
                        listOf(
                            R.drawable.smile,
                            R.drawable.addition,
                            R.drawable.add,
                            R.drawable.ic_launcher_background,
                            R.drawable.smile,
                            R.drawable.smile,
                            R.drawable.smile,
                            R.drawable.smile,
                            R.drawable.smile,
                            R.drawable.smile,
                            R.drawable.smile,
                            R.drawable.smile,
                            R.drawable.smile,
                            R.drawable.smile,
                        )
                    ) {
                        val context = LocalContext.current
                        val uri =
                            Uri.parse("android.resource://${context.packageName}/${it}")

                        Image(painter = painterResource(it),
                            contentDescription = null,
                            modifier = Modifier.clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                list.add(
                                    Data(
                                        uri, selected = true
                                    )
                                )
                                hide(list.size - 1)
                            }
                        )
                    }

                    item {
                        Spacer(
                            modifier = Modifier.width(5.dp)
                        )
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MyTheme.colorScheme.neutral1)
        ) {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    ButtonBarIcon(R.drawable.smile, "Striker") {
                        show = !show
                    }
                }

                item {
                    ButtonBarIcon(R.drawable.text, "Text") {
                        undo.add(
                            MyStack.Add(
                                Data(text = "Your Text", selected = true)
                            )
                        )
                        list.add(Data(text = "Your Text", selected = true))
                        hide(index = list.size - 1)
                        show = false
                    }
                }

                item {
                    ButtonBarIcon(R.drawable.addition, "Add") {
                        launcher.launch("image/*")
                        show = false
                    }
                }

                item {
                    val context = LocalContext.current
                    ButtonBarIcon(R.drawable.crop, "Crop") {
                        Toast.makeText(context, "Soon", Toast.LENGTH_SHORT).show()
                        show = false
                    }
                }

                item {
                    ButtonBarIcon(R.drawable.undo, "Undo") {
                        show = false
                        Log.d("TAG", "EditorScreenContent: $undo")
                        if (undo.isNotEmpty()) {
                            Log.d("TAG", "EditorScreenContent: $undo")
                            when (val item = undo.last()) {
                                is MyStack.Add -> {
                                    list.remove(item.data)
                                    redo.add(MyStack.Remove(item.data))
                                }

                                is MyStack.Update -> {
                                    list[item.index] = item.data
                                    redo.add(MyStack.Update(item.data, item.index))
                                }

                                is MyStack.Remove -> {
                                    if (!list.contains(item.data)) {
                                        list.add(item.data)
                                        redo.add(MyStack.Add(item.data))
                                    }
                                }
                            }
                            undo.removeLast()
                        }
                    }
                }

                item {
                    ButtonBarIcon(R.drawable.redo, "Redo") {
                        show = false

                        if (redo.isNotEmpty()) {
                            when (val item = redo.last()) {
                                is MyStack.Add -> {
                                    list.remove(item.data)
                                    undo.add(MyStack.Remove(item.data))
                                }

                                is MyStack.Update -> {
                                    list[item.index] = item.data
                                    undo.add(MyStack.Update(item.data, item.index))
                                }

                                is MyStack.Remove -> {
                                    if (!list.contains(item.data)) {
                                        list.add(item.data)
                                        undo.add(MyStack.Add(item.data))
                                    }
                                }
                            }
                            redo.removeLast()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditorScreenPreview() {
    EditorScreenContent()
}