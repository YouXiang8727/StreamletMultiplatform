package com.youxiang8727.streamletmultiplatform.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.youxiang8727.streamletmultiplatform.ui.home.HomeScreen
import com.youxiang8727.streamletmultiplatform.ui.home.HomeScreenViewModel
import com.youxiang8727.streamletmultiplatform.ui.transaction.TransactionScreen
import com.youxiang8727.streamletmultiplatform.ui.transaction.TransactionScreenDataSource
import com.youxiang8727.streamletmultiplatform.ui.transaction.TransactionScreenViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

sealed class Route(val showBottomBar: Boolean): NavKey

data class TransactionRoute(val transactionScreenDateSource: TransactionScreenDataSource): Route(
    showBottomBar = false
)

sealed class MainRoute(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
): Route(
    showBottomBar = true
) {
    data object HomeRoute: MainRoute(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    )
}

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier
) {
    val backStack = remember {
        mutableStateListOf<Route>(
            MainRoute.HomeRoute
        )
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (backStack.last().showBottomBar.not()) return@Scaffold
            BottomNavigation(
                modifier = Modifier.fillMaxWidth()
                    .height(80.dp),
                current = MainRoute.HomeRoute,
                navigateTo = {
                    backStack.navigateTo(it)
                }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    is MainRoute.HomeRoute -> NavEntry(key) {
                        val viewModel: HomeScreenViewModel = koinViewModel()
                        HomeScreen(
                            modifier = Modifier.fillMaxSize(),
                            viewModel = viewModel,
                            navigateToTransactionScreen = {
                                backStack.navigateTo(
                                    TransactionRoute(it)
                                )
                            }
                        )
                    }

                    is TransactionRoute -> NavEntry(key) {
                        val viewModel: TransactionScreenViewModel = koinViewModel() {
                            parametersOf(key.transactionScreenDateSource)
                        }
                        TransactionScreen(
                            modifier = Modifier.fillMaxSize(),
                            viewModel = viewModel,
                            back = {
                                backStack.removeLastOrNull()
                            }
                        )
                    }
                }
            }
        )
    }
}

@Composable
private fun BottomNavigation(
    modifier: Modifier = Modifier,
    current: MainRoute = MainRoute.HomeRoute,
    navigateTo: (MainRoute) -> Unit = {}
) {
    val routes = listOf<MainRoute>(
        MainRoute.HomeRoute
    )

    BottomAppBar(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            routes.forEach { route ->
                IconButton(
                    onClick = {
                        navigateTo(route)
                    }
                ) {
                    Icon(
                        imageVector = if (route::class == current::class) {
                            route.selectedIcon
                        } else {
                            route.unselectedIcon
                        },
                        contentDescription = null
                    )
                }
            }
        }
    }
}

private fun SnapshotStateList<Route>.navigateTo(route: Route) {
    val find = this.find { it::class == route::class }
    this.add(route)
    find?.let { this.remove(it) }
}