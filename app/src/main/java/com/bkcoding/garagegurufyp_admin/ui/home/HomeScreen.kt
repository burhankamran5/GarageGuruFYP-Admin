package com.bkcoding.garagegurufyp_admin.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_admin.R
import com.bkcoding.garagegurufyp_admin.extension.showToast
import com.bkcoding.garagegurufyp_admin.navigation.Screen
import com.bkcoding.garagegurufyp_admin.ui.theme.GarageGuruFYPAdminTheme

@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            context.showToast("Permission Granted!")
        } else {
            context.showToast("Permission denied!")
        }
    }
    LaunchedEffect(key1 = Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    HomeScreen(
        onMenuClick = {
            when(it){
                Menu.GARAGE.label -> navController.navigate(Screen.GarageScreen.route)
                Menu.CUSTOMER.label -> navController.navigate(Screen.CustomerScreen.route)
            }
        }
    )
}

@Composable
private fun HomeScreen(onMenuClick: (String) -> Unit) {
    val menuList = listOf("Garage", "Customer")

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
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(menuList) { name ->
                    MenuCard(modifier = Modifier.clickable { onMenuClick(name) }, name = name)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun MenuCard(modifier: Modifier = Modifier, name: String) {
    Box(
        modifier = modifier
            .size(60.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    GarageGuruFYPAdminTheme {
        HomeScreen(onMenuClick = {})
    }
}

enum class Menu(val label: String){
    GARAGE("Garage"),
    CUSTOMER("Customer")
}