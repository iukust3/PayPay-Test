package com.my.paypaytest.curencyconverter.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import com.my.paypaytest.curencyconverter.android.viewModels.CurrencyListViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@Composable
fun CurrincesListView(
    navigateToPerson: (String) -> Unit,
    onRefresh: () -> Unit
) {
    val viewModel: CurrencyListViewModel = getViewModel<CurrencyListViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(500)
        onRefresh()
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    PeopleInSpaceGradientBackground {
        Scaffold(
            topBar = {
                PeopleInSpaceTopAppBar(
                    titleRes = R.string.people_in_space,
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.semantics { contentDescription = "PeopleInSpace" }
                )
            },
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) { innerPadding ->

            Box(Modifier.pullRefresh(state)) {
                LazyColumn(
                    modifier = Modifier.testTag(PersonListTag)
                        .padding(innerPadding)
                        .consumedWindowInsets(innerPadding)
                        .fillMaxSize()
                ) {
                    if (!refreshing) {
                        items(uiState.items) { person ->
                            PersonView(person, navigateToPerson)
                        }
                    }
                }

                PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
            }
        }
    }
}
