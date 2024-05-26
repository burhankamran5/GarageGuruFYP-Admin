package com.bkcoding.garagegurufyp_admin.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bkcoding.garagegurufyp_admin.R
import com.bkcoding.garagegurufyp_admin.dto.ApprovalStatus
import com.bkcoding.garagegurufyp_admin.dto.Garage
import com.bkcoding.garagegurufyp_admin.ui.component.CircularProgress
import com.bkcoding.garagegurufyp_admin.ui.theme.GarageGuruFYPAdminTheme
import com.bkcoding.garagegurufyp_admin.util.Result

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    var isLoading = false
    var data = listOf<Garage>()
    when (val homeUiState = homeViewModel.homeScreenUIState) {
        Result.Loading -> {
            isLoading = true
            Log.i("TAG", "HomeScreen: loading")
        }

        is Result.Success -> {
            data = homeUiState.data
        }

        is Result.Failure -> {}
        else -> {}
    }
    HomeScreen(
        isLoading = isLoading,
        data = data,
        onDecline = {
            homeViewModel.updateGarageStatus(it, ApprovalStatus.DECLINED.name)
        },
        onApproved = {
            homeViewModel.updateGarageStatus(it, ApprovalStatus.APPROVED.name)
        }
    )
}

@Composable
private fun HomeScreen(isLoading: Boolean, data: List<Garage>, onDecline: (String) -> Unit, onApproved: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome_back_admin),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.home),
            fontSize = 20.sp,
            color = colorResource(id = R.color.spanish_gray)
        )
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(data) {
                if(it.approvalStatus == ApprovalStatus.PENDING.name){
                    GarageCard(modifier = Modifier, it, onDecline = {id-> onDecline(id) }, onApproved = {id -> onApproved(id) })
                }
            }
        }
        if (isLoading) {
            CircularProgress(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
private fun GarageCard(modifier: Modifier = Modifier, garage: Garage, onDecline: (String) -> Unit, onApproved: (String) -> Unit) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = garage.images.getOrNull(0),
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            error = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(45.dp)
                .clip(shape = CircleShape)
        )
        Column(modifier = Modifier
            .weight(1f)
            .padding(horizontal = 10.dp, vertical = 5.dp)) {
            Text(
                text = garage.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
            )
            Text(
                text = garage.city,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
            )
        }
        Box(
            modifier = modifier
                .width(60.dp)
                .height(30.dp)
                .padding(end = 15.dp)
                .border(
                    width = 1.dp,
                    color = Color.Blue,
                    shape = RoundedCornerShape(250.dp)
                )
                .clickable { onDecline(garage.id) }
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "",
                modifier = Modifier.align(Alignment.Center).padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }
        Box(
            modifier = modifier
                .width(50.dp)
                .height(30.dp)
                .border(
                    width = 1.dp,
                    color = Color.Blue,
                    shape = RoundedCornerShape(250.dp)
                )
                .clickable { onApproved(garage.id) }
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "",
                modifier = Modifier.align(Alignment.Center).padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    GarageGuruFYPAdminTheme {
        val garage = Garage(
            id = "0",
            name = "Garage",
            city = "Lahore"
        )
        HomeScreen(isLoading = true, data = listOf(garage), onDecline = {}, onApproved = {})
    }
}