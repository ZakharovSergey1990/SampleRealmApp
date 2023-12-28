package ru.rickmasters.demo.presentation.page.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun RoundIcon(res: Int, modifier: Modifier, iconColor: Color, onClick: ()-> Unit) {
    Surface(shape = CircleShape, border = BorderStroke(1.dp, Color.LightGray), modifier = modifier
        .clickable { onClick() }) {
        Icon(painter = painterResource(id = res), contentDescription = null, modifier = Modifier
            .padding(8.dp)
            .size(24.dp),
            tint = iconColor
        )
    }
}