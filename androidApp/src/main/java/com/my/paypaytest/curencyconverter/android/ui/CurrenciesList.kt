package com.my.paypaytest.curencyconverter.android.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my.paypaytest.curencyconverter.android.viewModels.CurrencyListViewModel
import com.my.paypaytest.curencyconverter.utils.toStringPair
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrincesListView(
    onClickProduct: (Pair<String,Any>) -> Unit,
    onRefresh: () -> Unit
) {
    val viewModel: CurrencyListViewModel = getViewModel<CurrencyListViewModel>()
    val uiState by viewModel.uiState.collectAsState()
var selected by remember {
    mutableStateOf("USD" to "USD")
}
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(500)
        onRefresh()
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
LazyHorizontalGrid(rows = GridCells.Fixed(4),) {

}

            Box(Modifier.pullRefresh(state)) {
                LazyVerticalGrid(columns = GridCells.Fixed(6) ,
                    modifier = Modifier
                        .testTag("CurrencyList")
                        .padding(3.dp)
                        .fillMaxSize()){
                    if (!refreshing) {
                        items(uiState.items.count()) { index ->
                          CurrencyItem(jsonPair = uiState.items.toList()[index],  onClickProduct = {
                              selected= it as Pair<String, String>;
                              onClickProduct(it);
                          },
                              isSelcted = selected.first==uiState.items.toList()[index].first
                          )
                        }
                    }
                }

                PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
            }

}
val rnd = Random()
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrencyItem(modifier: Modifier = Modifier.fillMaxWidth(), jsonPair:Pair<String,String>, onClickProduct: (Pair<String,Any>) -> Unit = {},isSelcted:Boolean=false){
val pair=jsonPair.toStringPair();
        val color =  androidx.compose.ui.graphics.Color( rnd.nextInt(256),
            rnd.nextInt(256), rnd.nextInt(256),255,)
        Card(
            onClick = {
                      onClickProduct(pair);
            },
            elevation = 4.dp
,
          modifier= Modifier
              .fillMaxWidth()
              .fillMaxHeight()
              .border(width = 1.dp,color= if(isSelcted) Color.Red else Color.White)
              .padding(5.dp)


        ) {

                Box (
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(2.dp)
                        .clip(RoundedCornerShape(10))
                        .background(color = if (isSelcted) Color.Green else color, shape = RoundedCornerShape(10))

                ){

                    Row() {
                        Text(
                            modifier= Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            text = pair.first,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize =if(isSelcted) 16.sp else 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign= TextAlign.Center,
                            color = if(isSelcted) Color.Black else Color.LightGray
                        )

                       /* Text(
                            modifier=Modifier.fillMaxSize(),
                            text = (pair.second as String ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign= TextAlign.Center,
                            color = Color.LightGray
                        )*/
                    }

                }


        }

}
