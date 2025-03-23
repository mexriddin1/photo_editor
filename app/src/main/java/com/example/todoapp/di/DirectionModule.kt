package com.example.todoapp.di

import com.example.todoapp.presentaton.editor.EditorContract
import com.example.todoapp.presentaton.editor.EditorDirection
import com.example.todoapp.presentaton.home.HomeContract
import com.example.todoapp.presentaton.home.HomeDirection
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {

    @Binds
    fun bindHomeDirection(impl: HomeDirection): HomeContract.Direction

    @Binds
    fun bindEditorDirection(impl: EditorDirection): EditorContract.Direction
}