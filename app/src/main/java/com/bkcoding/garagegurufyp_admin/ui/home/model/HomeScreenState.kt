package com.bkcoding.garagegurufyp_admin.ui.home.model

import com.bkcoding.garagegurufyp_admin.navigation.Screen
import com.google.android.gms.common.internal.Objects

sealed class HomeScreenState{
    data object Loading: HomeScreenState()
    data class Error(val message: String): HomeScreenState()

}
