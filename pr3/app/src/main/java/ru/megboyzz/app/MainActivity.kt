package ru.megboyzz.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomToolBar(false, actionBar = {
                TopAppBar(
                    title = {
                        backgroundColor
                    },

                    backgroundColor = Color(43, 43, 43, 200),
                    contentColor = Color.White,
                    elevation = 12.dp
                )
            })
            ButtonInCenter(
                text = "TabsActivity",
                onClick = {
                    val intent = Intent(this, TabsActivity::class.java)
                    this.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun ButtonInCenter(onClick: () -> Unit, text: String){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            content = {
                      Text(text)
            },
        )
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun CustomToolBar(
    isTabEnabled: Boolean,
    actionBar: @Composable () -> Unit = {
        TopAppBar(
            title = {
                Text("ActionBar")
            }
        )
    }
) {
    val tabs = listOf(
        TabItem.Tab1,
        TabItem.Tab2,
        TabItem.Tab3
    )
    val pagerState = rememberPagerState(pageCount = tabs.size)
    Surface(modifier = Modifier.fillMaxWidth()) {
        Scaffold(
            topBar = actionBar,
            content = {
                if(isTabEnabled)
                Column {
                    Tabs(pagerState = pagerState, tabs = tabs)
                    TabsContent(pagerState = pagerState, tabs = tabs)
                }
            },
            floatingActionButton = {
                
            },
        )
    }
}

