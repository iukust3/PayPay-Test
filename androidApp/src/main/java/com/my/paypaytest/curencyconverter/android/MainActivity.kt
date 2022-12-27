package com.my.paypaytest.curencyconverter.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.my.paypaytest.curencyconverter.android.ui.*
import com.my.paypaytest.curencyconverter.models.ConverterModel
import com.my.paypaytest.curencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    val repos: CurrencyRepository by inject()

    @SuppressLint("UnrememberedMutableState")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {

                                    Text(
                                        "Currency Converter",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                },
                            )
                        }) {
                        Surface(modifier = Modifier.padding(top = it.calculateTopPadding())) {
                            Column {
                                Box (Modifier.padding(start = 16.dp, end = 16.dp)){
                              update=      TextFieldDemo(onValueChange = {
                                 amount.value=it.toDouble();
                              })
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                if(result?.value!=null)
                                Text(text = " From : ${result.value?.query?.from}  To : ${result.value?.query?.to} Result:  ${result.value?.info?.quote}")
                                Spacer(modifier = Modifier.height(10.dp))
                                Row( modifier =  Modifier, horizontalArrangement = Arrangement.SpaceBetween) {
                                    Spinner(onSelectionChanged = {
                                        Log.e("TAG", "OnSelect source $it")
                                        source.value=it.first;

                                    }
                                    )
                                    Button(onClick = {
                                        Log.e("TAG","On Clikc ${source.value.isNotEmpty()} ${destentaion.value.isNotEmpty()} ")
                                        if(amount.value==0.0){
                                            Toast.makeText(this@MainActivity,"Please enter amount",Toast.LENGTH_LONG).show()
                                            return@Button
                                        }
                                        if(source.value.isNotEmpty() && destentaion.value.isNotEmpty()){
                                            showProgressDilog.value=true;
                                            GlobalScope.launch(context = CoroutineExceptionHandler { coroutineContext, throwable ->
                                                Log.e("TAG","Error "+throwable.message)
showProgressDilog.value=false;
                                            }) {
                                             var response= runBlocking {     repos.convert(source.value,destentaion.value,amount.value)}
                                                update(response.info.quote.toString())
                                                result.value=response
                                                showProgressDilog.value=false;

                                            }


                                        }else{
                                            Toast.makeText(this@MainActivity,"Please select source and destination Currency",Toast.LENGTH_LONG).show()
                                        }


                                    }) {
                                        Text(text = "Convert")
                                    }
                                }

                                CurrincesListView(onClickProduct = {
                                    Log.e("TAG", "OnSelect $it")
destentaion.value=it.first;

                                }, onRefresh = {

                                })
                            }
                            progressDilog()
                        }

                    }
                }
            }
        }
        GlobalScope.launch {
            Log.e("TAG", " get repos " + repos.getCurrencyList())
        }

    }
}

val amount= mutableStateOf(0.0)
val result= mutableStateOf<ConverterModel?>(null)
var update:(String)->Unit={}
var source =
    mutableStateOf("")
 ;
var destentaion=
    mutableStateOf("")
@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
