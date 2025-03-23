package com.example.todoapp.presentaton.editor

import com.example.todoapp.utils.navigation.AppNavigator
import javax.inject.Inject

class EditorDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : EditorContract.Direction {

}
