package ru.megboyzz.app

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String,var screen: ComposableFun) {
    object Tab1 : TabItem("Tab1", { TextInCenter("Tab1") })
    object Tab2 : TabItem("Tab2", { TextInCenter("Tab2") })
    object Tab3 : TabItem("Tab3", { TextInCenter("Tab3") })
}
