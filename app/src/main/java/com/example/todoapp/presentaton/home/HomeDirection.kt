package com.example.todoapp.presentaton.home

import android.net.Uri
import com.example.todoapp.presentaton.editor.EditorScreen
import com.example.todoapp.utils.navigation.AppNavigator
import javax.inject.Inject

class HomeDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : HomeContract.Direction {
    override suspend fun openEditor(uri: Uri) {
        appNavigator.navigateTo(EditorScreen(uri))
    }

}
