package ru.rickmasters.demo.presentation.page.home.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.rickmasters.demo.R
import ru.rickmasters.demo.domain.model.DoorsState
import ru.rickmasters.demo.domain.model.HomeEvent
import ru.rickmasters.demo.domain.model.ViewState
import ru.rickmasters.demo.presentation.common.ViewStateContent
import ru.rickmasters.demo.presentation.theme.paddings


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoorScreen(state: DoorsState, onEvent: (HomeEvent) -> Unit) {

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
            onRefresh = { onEvent(HomeEvent.RefreshDoors) },
        ) {
            ViewStateContent(state = state.doors) { doors ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = MaterialTheme.paddings.default)
            ) {
                items(doors) { door ->
                    Column {
                        val density = LocalDensity.current
                        val defaultActionSize = 60.dp
                        val endActionSizePx = with(density) { (defaultActionSize * 2).toPx() }

                        val state = remember {
                            AnchoredDraggableState(
                                initialValue = DragAnchors.Center,
                                anchors = DraggableAnchors {
                                    DragAnchors.Center at 0f
                                    DragAnchors.End at endActionSizePx
                                },
                                positionalThreshold = { distance: Float -> distance * 0.5f },
                                velocityThreshold = { with(density) { 40.dp.toPx() } },
                                animationSpec = tween(),
                            )
                        }
                        DraggableBox(state = state,
                            endAction = {
                                Row {
                                    RoundIcon(
                                        res = R.drawable.edit_3,
                                        modifier = Modifier.padding(end = MaterialTheme.paddings.default),
                                        iconColor = Color.Cyan
                                    ) {}
                                    RoundIcon(
                                        R.drawable.star_2,
                                        modifier = Modifier.padding(end = MaterialTheme.paddings.default),
                                        iconColor = Color.Yellow
                                    ) {}
                                }
                            }
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Gray
                                )
                            ) {
                                Column {
                                    door.snapshot?.let {
                                        AsyncImage(
                                            model = door.snapshot,
                                            contentDescription = null,
                                            modifier = Modifier.fillMaxWidth(),
                                            contentScale = ContentScale.FillWidth
                                        )
                                    }
                                    Text(
                                        door.name,
                                        modifier = Modifier.padding(MaterialTheme.paddings.default)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

