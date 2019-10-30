package com.ratry.android.ui.base

import androidx.navigation.NavDirections

interface BaseNavigation {
    fun navigateToDirection(direction: NavDirections)
    fun navigateToDirection(direction: Int)
    fun navigateToBack()
}