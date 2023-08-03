package com.rafaelfelipeac.contacts.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.rafaelfelipeac.contacts.ui.theme.DarkColorScheme
import com.rafaelfelipeac.contacts.ui.theme.LightColorScheme
import com.rafaelfelipeac.contacts.ui.theme.Typography

@Composable
actual fun ContactsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}