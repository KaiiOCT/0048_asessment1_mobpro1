package com.faris0048.asessment1.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.faris0048.asessment1.R
import com.faris0048.asessment1.navigation.Screen
import com.faris0048.asessment1.ui.theme.Asessment1Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuhuScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = colorResource(R.color.white)
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.judul_suhu))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(R.color.dark_blue),
                    titleContentColor = colorResource(R.color.white)
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.AboutSuhu.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_suhu),
                            tint = colorResource(R.color.white)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        SuhuContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun SuhuContent(modifier: Modifier = Modifier) {
    // List Suhu
    val listSuhu = listOf(stringResource(R.string.pilih), "Celsius", "Fahrenheit", "Kelvin")

    // Dropdown Suhu Kiri
    var isDropDownExpanded1 by rememberSaveable { mutableStateOf(false) }
    var itemsPosition1 by rememberSaveable { mutableIntStateOf(0) }

    // Dropdown Suhu Kanan
    var isDropDownExpanded2 by rememberSaveable { mutableStateOf(false) }
    var itemsPosition2 by rememberSaveable { mutableIntStateOf(0) }

    // Error
    var itemsPosition1Error by rememberSaveable { mutableStateOf(false) }
    var itemsPosition2Error by rememberSaveable { mutableStateOf(false) }
    var inputSuhuError by rememberSaveable { mutableStateOf(false) }

    // Icon Konversi
    var isArrowReversed by rememberSaveable { mutableStateOf(false) }

    // Input dan hasil
    var inputSuhu by rememberSaveable { mutableStateOf("") }
    var hasil by rememberSaveable { mutableStateOf("") }

    // Bagikan Hasil
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Dropdown Kiri
            Box(
                modifier = Modifier
                    .weight(3f)
                    .border(
                        1.dp,
                        color = colorResource(R.color.blue_primary),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { isDropDownExpanded1 = true }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = listSuhu[itemsPosition1],
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(R.string.dropdown),
                        tint = colorResource(R.color.blue_primary)
                    )
                }
                DropdownMenu(
                    expanded = isDropDownExpanded1,
                    onDismissRequest = { isDropDownExpanded1 = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                ) {
                    listSuhu.forEachIndexed { index, suhu ->
                        DropdownMenuItem(
                            text = { Text(text = suhu) },
                            onClick = {
                                itemsPosition1 = index
                                isDropDownExpanded1 = false
                            }
                        )
                    }
                }
            }


            // Box untuk Icon Convert
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .clickable { isArrowReversed = !isArrowReversed },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = if (isArrowReversed) Icons.AutoMirrored.Filled.ArrowBack else Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = stringResource(R.string.convert),
                    modifier = Modifier.fillMaxWidth(),
                    tint = colorResource(R.color.blue_primary)
                )
            }

            // Dropdown Kanan
            Box(
                modifier = Modifier
                    .weight(3f)
                    .border(
                        1.dp,
                        color = colorResource(R.color.blue_primary),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { isDropDownExpanded2 = true }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = listSuhu[itemsPosition2],
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(R.string.dropdown),
                        tint = colorResource(R.color.blue_primary)
                    )
                }
                DropdownMenu(
                    expanded = isDropDownExpanded2,
                    onDismissRequest = { isDropDownExpanded2 = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                ) {
                    listSuhu.forEachIndexed { index, suhu ->
                        DropdownMenuItem(
                            text = { Text(text = suhu) },
                            onClick = {
                                itemsPosition2 = index
                                isDropDownExpanded2 = false
                            }
                        )
                    }
                }
            }
        }

        // Input Suhu
        OutlinedTextField(
            value = inputSuhu,
            onValueChange = { inputSuhu = it },
            label = { Text(stringResource(R.string.input_suhu)) },
            trailingIcon = { IconPickerSuhu(inputSuhuError) },
            supportingText = { ErrorHintSuhu(inputSuhuError) },
            isError = inputSuhuError,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = TextFieldDefaults.colors(
                focusedLabelColor = colorResource(R.color.blue_primary),
                unfocusedIndicatorColor = colorResource(R.color.blue_primary),
                focusedIndicatorColor = colorResource(R.color.blue_primary),
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                cursorColor = colorResource(R.color.blue_primary),
            )
        )

        // Button Konversi
        Button(
            onClick = {
                itemsPosition1Error = itemsPosition1 == 0
                itemsPosition2Error = itemsPosition2 == 0
                inputSuhuError = inputSuhu == ""

                if (itemsPosition1Error || itemsPosition2Error || inputSuhuError) return@Button

                hasil =
                    if(!isArrowReversed){
                        konversiSuhu(inputSuhu, listSuhu[itemsPosition1], listSuhu[itemsPosition2])
                    } else {
                        konversiSuhu(inputSuhu, listSuhu[itemsPosition2], listSuhu[itemsPosition1])
                    }
            },
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue),
                contentColor = colorResource(R.color.white)
            )
        ) {
            Text(text = stringResource(R.string.konversi))
        }

        if(hasil.isNotEmpty()){
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )

            // Hasil Konversi
            Text(
                text = stringResource(R.string.hasil, hasil),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            Button(
                onClick = {
                    val dariSuhu = if (!isArrowReversed) listSuhu[itemsPosition1] else listSuhu[itemsPosition2]
                    val keSuhu = if (!isArrowReversed) listSuhu[itemsPosition2] else listSuhu[itemsPosition1]

                    val pesan = context.getString(
                        R.string.bagikan_hasil,
                        inputSuhu, dariSuhu, keSuhu, hasil
                    )
                    shareData(context, pesan)
                },
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.dark_blue),
                    contentColor = colorResource(R.color.white)
                )
            ) {
                Text(text = stringResource(R.string.bagikan))
            }
        }

    }
}

@SuppressLint("DefaultLocale")
fun konversiSuhu(input: String, dari: String, ke: String): String {
    val suhu = input.toFloat()

    val hasil = when {
        // Dari Celsius
        dari == "Celsius" && ke == "Fahrenheit" -> String.format("%.2f", (suhu * 9 / 5) + 32) + satuanSingkatSuhu(ke)
        dari == "Celsius" && ke == "Kelvin" -> String.format("%.2f", suhu + 273.15) + satuanSingkatSuhu(ke)

        // Dari Farenheit
        dari == "Fahrenheit" && ke == "Celsius" -> String.format("%.2f", (suhu - 32) * 5 / 9) + satuanSingkatSuhu(ke)
        dari == "Fahrenheit" && ke == "Kelvin" -> String.format("%.2f", (suhu - 32) * 5 / 9 + 273.15) + satuanSingkatSuhu(ke)

        // Dari Kelvin
        dari == "Kelvin" && ke == "Celsius" -> String.format("%.2f", suhu - 273.15) + satuanSingkatSuhu(ke)
        dari == "Kelvin" && ke == "Fahrenheit" -> String.format("%.2f", (suhu - 273.15) * 9 / 5 + 32) + satuanSingkatSuhu(ke)

        // Jika Sama
        dari == ke -> String.format("%.2f", suhu) + " " + satuanSingkatSuhu(ke)
        else -> "Pilih suhu yang valid"
    }
    return hasil
}

fun satuanSingkatSuhu(satuan: String): String {
    return when (satuan) {
        "Celsius" -> "°C"
        "Fahrenheit" -> "°F"
        "Kelvin" -> "K"
        else -> satuan
    }
}

@Composable
fun IconPickerSuhu(isError: Boolean) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }
}

@Composable
fun ErrorHintSuhu(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun SuhuScreenPreview() {
    Asessment1Theme {
        SuhuScreen(rememberNavController())
    }
}