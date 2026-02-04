package com.youxiang8727.streamletmultiplatform.core.ui

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


typealias Resource = StringResource

sealed class UiText {
    @Composable
    abstract fun asString(): String

    data class DynamicString(val value: String): UiText() {
        @Composable
        override fun asString(): String {
            return value
        }
    }

    class StringResource(val resources: Resource, vararg val args: Any): UiText() {
        @Composable
        override fun asString(): String {
            return stringResource(resources, *args)
        }
    }
}