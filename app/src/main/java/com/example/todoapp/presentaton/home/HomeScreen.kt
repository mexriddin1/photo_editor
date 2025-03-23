package com.example.todoapp.presentaton.home

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.todoapp.core.ui.theme.MyTheme
import com.example.todoapp.R
import org.orbitmvi.orbit.compose.collectAsState

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: HomeContract.ViewModel =
            getViewModel<HomeViewModel>()
        val uiState = viewModel.collectAsState()
        HomeScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
fun HomeScreenContent(
    uiState: State<HomeContract.UiState> = remember {
        mutableStateOf(
            HomeContract.UiState()
        )
    }, onEventDispatcher: (intent: HomeContract.Intent) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MyTheme.colorScheme.neutral1)
            .padding(20.dp)
    ) {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            if (uri != null) {
                onEventDispatcher.invoke(HomeContract.Intent.OpenEditor(uri))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .clip(MyTheme.shape.medium)
                .background(MyTheme.colorScheme.primary1)
                .height(70.dp)
                .clickable(
                    indication = null, interactionSource = remember { MutableInteractionSource() }
                ) {
                    launcher.launch("image/*")
                }
        ) {
            Row(
                modifier = Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.addition),
                    contentDescription = null,
                    tint = MyTheme.colorScheme.neutral1,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Create Project",
                    style = MyTheme.typography.title2,
                    color = MyTheme.colorScheme.neutral1,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenContent()
}