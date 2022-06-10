package ru.megboyzz.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import ru.megboyzz.app.ui.theme.Pr3Theme

class TabsActivity : ComponentActivity() {
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomToolBar(true) {
                val activity: TabsActivity = this
                TopAppBar{
                    IconButton(onClick = {
                        activity.back()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                    Text("TabActivity")
                    Spacer(Modifier.weight(1f, true))
                    IconButton(onClick = {
                        Toast.makeText(this@TabsActivity, "Инфа", Toast.LENGTH_LONG).show();
                    }){
                        Icon(Icons.Filled.Info, "info")
                    }
                }
            }
        }
    }

    private fun back(){
        super.onBackPressed()
    }

    override fun onBackPressed() {}
}



@Composable
fun TextInCenter(text: String){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }

}


@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen()
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            // OR Tab()
            LeadingIconTab(
                icon = {  },
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}