package com.example.huertohogarappev2.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.huertohogarappev2.R
import kotlinx.coroutines.delay

val imagenes = listOf(
    R.drawable.banner,
    R.drawable.mercadoonline,
    R.drawable.frescoorganico,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarruselSimple(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        imagenes.size
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val next = (pagerState.currentPage + 1) % imagenes.size
            pagerState.animateScrollToPage(next)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 28.dp),
        pageSpacing = 16.dp
    ) { page ->
        Box(
            modifier = Modifier.height(150.dp).clip(RoundedCornerShape(16.dp))
        ) {
            Image(painter = painterResource(id = imagenes[page]), contentDescription = null, contentScale = ContentScale.Crop)
        }
    }
}
