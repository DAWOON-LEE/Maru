package com.shoebill.maru.ui.component.bottomsheet.landmark

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.shoebill.maru.viewmodel.BottomSheetNavigatorViewModel
import com.shoebill.maru.viewmodel.LandmarkPictureListViewModel

@Composable
fun LandmarkPictureList(
    landmarkPictureListViewModel: LandmarkPictureListViewModel = hiltViewModel(),
    bottomSheetNavigatorViewModel: BottomSheetNavigatorViewModel = hiltViewModel(),
    landmarkId: Long
) {
    landmarkPictureListViewModel.initLandmarkId(landmarkId)
    val pictureList = landmarkPictureListViewModel.getLandmarkPicturePagination().collectAsLazyPagingItems()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(pictureList.itemCount) { idx ->
            AsyncImage(
                model = pictureList[idx]!!.imageUrl,
                contentDescription = "landmark picture",
                modifier = Modifier
                    .size(120.dp)
                    .clickable { bottomSheetNavigatorViewModel.navController?.navigate("spot/detail/${pictureList[idx]!!.id}") },
                contentScale = ContentScale.Crop
            )
        }
    }
}