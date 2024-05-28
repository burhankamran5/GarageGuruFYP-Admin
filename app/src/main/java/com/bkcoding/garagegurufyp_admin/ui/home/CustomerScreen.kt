package com.bkcoding.garagegurufyp_admin.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.bkcoding.garagegurufyp_admin.ui.component.CircularProgress
import com.bkcoding.garagegurufyp_admin.ui.home.model.Customer
import com.bkcoding.garagegurufyp_admin.ui.theme.GarageGuruFYPAdminTheme
import com.bkcoding.garagegurufyp_admin.util.Result

@Composable
fun CustomerScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        homeViewModel.getCustomer()
    }

    var isLoading = false
    var data = listOf<Customer>()
    when (val homeUiState = homeViewModel.getCustomerResponse) {
        Result.Loading -> {
            isLoading = true
            Log.i("TAG", "CustomerScreenInfo: loading")
        }

        is Result.Success -> {
            data = homeUiState.data
            Log.i("TAG", "CustomerScreenInfo: $data")
        }

        is Result.Failure -> {}
        else -> {}
    }
    CustomerScreen(
        isLoading = isLoading,
        data = data
    )
}

@Composable
private fun CustomerScreen(
    isLoading: Boolean,
    data: List<Customer>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.customer),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(data) {
                CustomerCard(modifier = Modifier, it)
            }
        }
        if (isLoading) {
            CircularProgress(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
private fun CustomerCard(modifier: Modifier = Modifier, customer: Customer) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = customer,
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            error = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(45.dp)
                .clip(shape = CircleShape)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = customer.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
            )
            Text(
                text = customer.city,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun CustomerScreenPreview() {
    GarageGuruFYPAdminTheme {
        val customer = Customer(
            id = "",
            name = "Customer",
            city = "Lahore"
        )
        CustomerScreen(isLoading = true, data = listOf(customer))
    }
}