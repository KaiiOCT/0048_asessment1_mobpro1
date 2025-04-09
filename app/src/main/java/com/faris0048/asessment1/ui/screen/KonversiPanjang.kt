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
fun PanjangScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = colorResource(R.color.white)
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.judul_panjang))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(R.color.dark_blue),
                    titleContentColor = colorResource(R.color.white)
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.AboutPanjang.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_panjang),
                            tint = colorResource(R.color.white)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        PanjangContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun PanjangContent(modifier: Modifier = Modifier) {
    // List Panjang
    val listPanjang = listOf(stringResource(R.string.pilih), "Millimeter", "Centimeter", "Meter", "Kilometer", "Inch", "Feet", "Mile")


    // Dropdown Panjang Kiri
    var isDropDownExpanded1 by rememberSaveable { mutableStateOf(false) }
    var itemsPosition1 by rememberSaveable { mutableIntStateOf(0) }

    // Dropdown Panjang Kanan
    var isDropDownExpanded2 by rememberSaveable { mutableStateOf(false) }
    var itemsPosition2 by rememberSaveable { mutableIntStateOf(0) }

    // Error
    var itemsPosition1Error by rememberSaveable { mutableStateOf(false) }
    var itemsPosition2Error by rememberSaveable { mutableStateOf(false) }
    var inputPanjangError by rememberSaveable { mutableStateOf(false) }

    // Icon Konversi
    var isArrowReversed by rememberSaveable { mutableStateOf(false) }

    // Input dan hasil
    var inputPanjang by rememberSaveable { mutableStateOf("") }
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
                        text = listPanjang[itemsPosition1],
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
                    listPanjang.forEachIndexed { index, suhu ->
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
                        text = listPanjang[itemsPosition2],
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
                    listPanjang.forEachIndexed { index, suhu ->
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

        // Input Panjang
        OutlinedTextField(
            value = inputPanjang,
            onValueChange = { inputPanjang = it },
            label = { Text(stringResource(R.string.input_panjang)) },
            trailingIcon = { IconPickerPanjang(inputPanjangError) },
            supportingText = { ErrorHintPanjang(inputPanjangError) },
            isError = inputPanjangError,
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
                inputPanjangError = inputPanjang == "" || inputPanjang == "0"

                if (itemsPosition1Error || itemsPosition2Error || inputPanjangError) return@Button

                hasil =
                    if(!isArrowReversed){
                        konversiPanjang(inputPanjang, listPanjang[itemsPosition1], listPanjang[itemsPosition2])
                    } else {
                        konversiPanjang(inputPanjang, listPanjang[itemsPosition2], listPanjang[itemsPosition1])
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
                    val dariPanjang = if (!isArrowReversed) listPanjang[itemsPosition1] else listPanjang[itemsPosition2]
                    val kePanjang = if (!isArrowReversed) listPanjang[itemsPosition2] else listPanjang[itemsPosition1]

                    val pesan = context.getString(
                        R.string.bagikan_hasil,
                        inputPanjang, dariPanjang, kePanjang, hasil
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
fun konversiPanjang(input: String, dari: String, ke: String): String {
    val panjang = input.toFloat()

    val hasil = when {
        // Dari Centimeter
        dari == "Centimeter" && ke == "Meter" -> String.format("%.4f", panjang / 100) + " m"
        dari == "Centimeter" && ke == "Kilometer" -> String.format("%.6f", panjang / 100000) + " km"
        dari == "Centimeter" && ke == "Millimeter" -> String.format("%.2f", panjang * 10) + " mm"
        dari == "Centimeter" && ke == "Inch" -> String.format("%.4f", panjang / 2.54) + " in"
        dari == "Centimeter" && ke == "Feet" -> String.format("%.4f", panjang / 30.48) + " ft"
        dari == "Centimeter" && ke == "Mile" -> String.format("%.6f", panjang / 160934) + " mi"

        // Dari Meter
        dari == "Meter" && ke == "Centimeter" -> String.format("%.2f", panjang * 100) + " cm"
        dari == "Meter" && ke == "Kilometer" -> String.format("%.5f", panjang / 1000) + " km"
        dari == "Meter" && ke == "Millimeter" -> String.format("%.2f", panjang * 1000) + " mm"
        dari == "Meter" && ke == "Inch" -> String.format("%.2f", panjang / 0.0254) + " in"
        dari == "Meter" && ke == "Feet" -> String.format("%.2f", panjang / 0.3048) + " ft"
        dari == "Meter" && ke == "Mile" -> String.format("%.6f", panjang / 1609.34) + " mi"

        // Dari Kilometer
        dari == "Kilometer" && ke == "Centimeter" -> String.format("%.2f", panjang * 100000) + " cm"
        dari == "Kilometer" && ke == "Meter" -> String.format("%.2f", panjang * 1000) + " m"
        dari == "Kilometer" && ke == "Millimeter" -> String.format("%.2f", panjang * 1_000_000) + " mm"
        dari == "Kilometer" && ke == "Inch" -> String.format("%.2f", panjang * 39370.1) + " in"
        dari == "Kilometer" && ke == "Feet" -> String.format("%.2f", panjang * 3280.84) + " ft"
        dari == "Kilometer" && ke == "Mile" -> String.format("%.4f", panjang / 1.60934) + " mi"

        // Dari Millimeter
        dari == "Millimeter" && ke == "Centimeter" -> String.format("%.2f", panjang / 10) + " cm"
        dari == "Millimeter" && ke == "Meter" -> String.format("%.4f", panjang / 1000) + " m"
        dari == "Millimeter" && ke == "Kilometer" -> String.format("%.6f", panjang / 1_000_000) + " km"
        dari == "Millimeter" && ke == "Inch" -> String.format("%.4f", panjang / 25.4) + " in"
        dari == "Millimeter" && ke == "Feet" -> String.format("%.4f", panjang / 304.8) + " ft"
        dari == "Millimeter" && ke == "Mile" -> String.format("%.6f", panjang / 1_609_344) + " mi"

        // Dari Inch
        dari == "Inch" && ke == "Centimeter" -> String.format("%.2f", panjang * 2.54) + " cm"
        dari == "Inch" && ke == "Meter" -> String.format("%.4f", panjang * 0.0254) + " m"
        dari == "Inch" && ke == "Kilometer" -> String.format("%.6f", panjang * 0.0000254) + " km"
        dari == "Inch" && ke == "Millimeter" -> String.format("%.2f", panjang * 25.4) + " mm"
        dari == "Inch" && ke == "Feet" -> String.format("%.4f", panjang / 12) + " ft"
        dari == "Inch" && ke == "Mile" -> String.format("%.6f", panjang / 63360) + " mi"

        // Dari Feet
        dari == "Feet" && ke == "Centimeter" -> String.format("%.2f", panjang * 30.48) + " cm"
        dari == "Feet" && ke == "Meter" -> String.format("%.4f", panjang * 0.3048) + " m"
        dari == "Feet" && ke == "Kilometer" -> String.format("%.6f", panjang * 0.0003048) + " km"
        dari == "Feet" && ke == "Millimeter" -> String.format("%.2f", panjang * 304.8) + " mm"
        dari == "Feet" && ke == "Inch" -> String.format("%.2f", panjang * 12) + " in"
        dari == "Feet" && ke == "Mile" -> String.format("%.6f", panjang / 5280) + " mi"

        // Dari Mile
        dari == "Mile" && ke == "Centimeter" -> String.format("%.2f", panjang * 160934) + " cm"
        dari == "Mile" && ke == "Meter" -> String.format("%.2f", panjang * 1609.34) + " m"
        dari == "Mile" && ke == "Kilometer" -> String.format("%.2f", panjang * 1.60934) + " km"
        dari == "Mile" && ke == "Millimeter" -> String.format("%.2f", panjang * 1_609_344) + " mm"
        dari == "Mile" && ke == "Inch" -> String.format("%.2f", panjang * 63360) + " in"
        dari == "Mile" && ke == "Feet" -> String.format("%.2f", panjang * 5280) + " ft"

        // Jika sama
        dari == ke -> String.format("%.2f", panjang) + " " + satuanSingkat(ke)

        else -> "Pilih satuan yang valid"
    }
    return hasil
}

fun satuanSingkat(satuan: String): String {
    return when (satuan) {
        "Millimeter" -> "mm"
        "Centimeter" -> "cm"
        "Meter" -> "m"
        "Kilometer" -> "km"
        "Inch" -> "in"
        "Feet" -> "ft"
        "Mile" -> "mi"
        else -> satuan
    }
}

@Composable
fun IconPickerPanjang(isError: Boolean) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }
}

@Composable
fun ErrorHintPanjang(isError: Boolean) {
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
private fun PanjangScreenPreview() {
    Asessment1Theme {
        PanjangScreen(rememberNavController())
    }
}