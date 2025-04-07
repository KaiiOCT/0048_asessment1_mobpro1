package com.faris0048.asessment1.navigation

sealed class Screen (val route: String){
    data object Home: Screen("mainScreen")
    data object Suhu: Screen("suhuScreen")
    data object Panjang: Screen("panjangScreen")
    data object Berat: Screen("beratScreen")
    data object AboutSuhu: Screen("aboutSuhuScreen")
    data object AboutPanjang : Screen("aboutPanjangScreen")
    data object AboutBerat : Screen("aboutBeratScreen")
}