package com.my.paypaytest.curencyconverter.android.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.my.paypaytest.curencyconverter.android.viewModels.CurrencyListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TextFieldDemo(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
): (String)->Unit{
    val text = remember { mutableStateOf(TextFieldValue()) }

   val calback:(value:String)->Unit ={
      text.value= TextFieldValue(it)
    }
    OutlinedTextField(value = text.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
modifier = modifier.fillMaxWidth(),
        onValueChange = { text.value = it
            onValueChange(text.value.text)
        },
        label = {  })
    return  calback;
}
@Composable
fun Spinner(
    onSelectionChanged: (myData: Pair<String,Any>) -> Unit,
    modifier: Modifier = Modifier
){
    val viewModel: CurrencyListViewModel = getViewModel<CurrencyListViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    SpinnerSample(data = uiState.items,  onSelectionChanged =onSelectionChanged,modifier=modifier )
}

@Composable
fun SpinnerSample(
    data:Map<String,String>,
    onSelectionChanged: (myData: Pair<String,Any>) -> Unit,
    modifier: Modifier = Modifier
) {

    var selected by remember { mutableStateOf("USD" to "USD") }
    var expanded by remember { mutableStateOf(false) } // initial value
    LaunchedEffect(true) {
        onSelectionChanged(selected)
    }

    Card(
        modifier = modifier
            .width(200.dp)
            .clickable {
            expanded = !expanded
        }
    ) {


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {

            Text(
                text = selected.first,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Icon(Icons.Outlined.ArrowDropDown, null, modifier = Modifier.padding(8.dp))

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxHeight()
                    .width(100.dp)// delete this modifier and use .wrapContentWidth() if you would like to wrap the dropdown menu around the content
            ) {
                data.forEach { listEntry ->

                    DropdownMenuItem(
                        onClick = {
                            selected = Pair(listEntry.key,listEntry.value)
                            expanded = false
                            onSelectionChanged(selected)
                        },
                    ){
                        Text(
                            text = listEntry.key,
                            modifier = Modifier
                                //.wrapContentWidth()  //optional instad of fillMaxWidth
                                .fillMaxWidth()

                        )
                    }
                }
            }

        }
    }
}

var showProgressDilog = mutableStateOf(false)

@Composable
fun progressDilog() {
    if (showProgressDilog.value) {
        Log.e("TAG","Show Progress")
        AlertDialog(
            onDismissRequest = {
                showProgressDilog.value = false
            },
            title = {
                Column() {
                    Text(text = "Loading data...")
                    LoadingCircular()
                }

            },
            confirmButton = {},


            )
    }
}

@Composable
fun LoadingCircular(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SpinnerSample_Preview() {
    MaterialTheme {
        val myData = mapOf<String,String>("PKR" to "Pkr", "USD" to "USD")

        SpinnerSample(
            myData,
            onSelectionChanged = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
