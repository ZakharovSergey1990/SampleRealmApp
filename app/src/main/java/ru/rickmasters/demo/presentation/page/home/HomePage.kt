package ru.rickmasters.demo.presentation.page.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.Coil
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.rickmasters.demo.R
import ru.rickmasters.demo.domain.model.CamerasState
import ru.rickmasters.demo.domain.model.DoorsState
import ru.rickmasters.demo.domain.model.HomeEvent
import ru.rickmasters.demo.presentation.page.home.component.CameraScreen
import ru.rickmasters.demo.presentation.page.home.component.DoorScreen
import kotlin.math.roundToInt

@Composable
fun HomePage(vm: HomeViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        topBar = {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(stringResource(R.string.my_house), modifier = Modifier.padding(8.dp), color = Color.DarkGray, style = MaterialTheme.typography.headlineMedium)
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            TabRow(selectedTabIndex = state.tabPosition, contentColor = Color.DarkGray, containerColor = MaterialTheme.colorScheme.primary,
                indicator = { tabPositions ->
                    if (state.tabPosition < tabPositions.size) {
                        TabRowDefaults.Indicator(
                            Modifier.tabIndicatorOffset(tabPositions[state.tabPosition]),
                            color = Color.Cyan
                        )
                    }
                }
                    ) {
                Tab(selected = state.tabPosition == 0, onClick = { vm.onEvent(HomeEvent.SetTab(0)) }) {
                    Text(text = stringResource(R.string.cameras), modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.bodyLarge)
                }
                Tab(selected = state.tabPosition == 1, onClick = { vm.onEvent(HomeEvent.SetTab(1)) }) {
                    Text(text = stringResource(R.string.doors), modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.bodyLarge)
                }
            }
            when (state.tabPosition) {
                0 -> CameraScreen(state.camerasState, vm::onEvent)
                else -> DoorScreen(state.doorsState, vm::onEvent)
            }
        }
    }
}






