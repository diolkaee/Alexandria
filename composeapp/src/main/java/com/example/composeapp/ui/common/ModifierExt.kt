package com.example.composeapp.ui.common

import androidx.compose.ui.Modifier

/** Convenience helper for conditionally applying modifiers. */
inline fun Modifier.applyIf(predicate: Boolean, builder: Modifier.() -> Modifier) =
    then(if (predicate) builder() else Modifier)
