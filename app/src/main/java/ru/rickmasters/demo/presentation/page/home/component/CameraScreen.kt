package ru.rickmasters.demo.presentation.page.home.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.rickmasters.demo.R
import ru.rickmasters.demo.domain.model.CamerasState
import ru.rickmasters.demo.domain.model.HomeEvent
import ru.rickmasters.demo.domain.model.ViewState
import ru.rickmasters.demo.presentation.common.ViewStateContent
import ru.rickmasters.demo.presentation.theme.paddings

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CameraScreen(state: CamerasState, onEvent: (HomeEvent) -> Unit) {
    val defaultPadding = MaterialTheme.paddings.default
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
            onRefresh = { onEvent(HomeEvent.RefreshCameras) },
        ) {
            ViewStateContent(state = state.rooms) { cameras ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = defaultPadding)
            ) {
                items(cameras) { camera ->
                    Column {
                        val density = LocalDensity.current
                        val defaultActionSize = 30.dp
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
                        camera.room?.let {
                            Text(
                                it,
                                color = Color.Gray,
                                modifier = Modifier.padding(horizontal = defaultPadding)
                            )
                        }
                        DraggableBox(state = state,
                            endAction = {
                                RoundIcon(
                                    R.drawable.star_2,
                                    modifier = Modifier.padding(end = defaultPadding),
                                    iconColor = Color.Yellow
                                ) {

                                }
                            }
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Gray
                                )
                            ) {
                                Column {
                                    ConstraintLayout {
                                        val (rec, image, fav) = createRefs()
                                        AsyncImage(
                                            model = camera.snapshot,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .constrainAs(image) {
                                                    top.linkTo(parent.top)
                                                },
                                            contentScale = ContentScale.FillWidth
                                        )
                                        if (camera.rec) {
                                            Icon(
                                                painterResource(id = R.drawable.group_141),
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .constrainAs(rec) {
                                                        top.linkTo(parent.top, defaultPadding)
                                                        start.linkTo(parent.start, defaultPadding)
                                                    },
                                                contentDescription = null,
                                                tint = Color.Red
                                            )
                                        }
                                        if (camera.favorites) {
                                            Icon(
                                                painterResource(id = R.drawable.star_23),
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .constrainAs(fav) {
                                                        top.linkTo(parent.top, defaultPadding)
                                                        end.linkTo(parent.end, defaultPadding)
                                                    },
                                                contentDescription = null,
                                                tint = Color.Yellow
                                            )
                                        }
                                    }
                                    Text(camera.name, modifier = Modifier.padding(defaultPadding))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
