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
fun BeratScreen(navController: NavController) {
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
                    Text(text = stringResource(id = R.string.judul_berat))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(R.color.dark_blue),
                    titleContentColor = colorResource(R.color.white)
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.AboutBerat.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_berat),
                            tint = colorResource(R.color.white)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        BeratContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BeratContent(modifier: Modifier = Modifier) {
    // List Berat
    val listBerat = listOf(stringResource(R.string.pilih), "Milligram", "Gram", "Kilogram", "Ounce", "Pound", "Ton", "Stone")

    // Dropdown Berat Kiri
    var isDropDownExpanded1 by rememberSaveable { mutableStateOf(false) }
    var itemsPosition1 by rememberSaveable { mutableIntStateOf(0) }

    // Dropdown Berat Kanan
    var isDropDownExpanded2 by rememberSaveable { mutableStateOf(false) }
    var itemsPosition2 by rememberSaveable { mutableIntStateOf(0) }

    // Error
    var itemsPosition1Error by rememberSaveable { mutableStateOf(false) }
    var itemsPosition2Error by rememberSaveable { mutableStateOf(false) }
    var inputBeratError by rememberSaveable { mutableStateOf(false) }

    // Icon Konversi
    var isArrowReversed by rememberSaveable { mutableStateOf(false) }

    // Input dan hasil
    var inputBerat by rememberSaveable { mutableStateOf("") }
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
                        text = listBerat[itemsPosition1],
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
                    listBerat.forEachIndexed { index, suhu ->
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
                        text = listBerat[itemsPosition2],
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
                    listBerat.forEachIndexed { index, suhu ->
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

        // Input Berat
        OutlinedTextField(
            value = inputBerat,
            onValueChange = { inputBerat = it },
            label = { Text(stringResource(R.string.input_berat)) },
            trailingIcon = { IconPickerBerat(inputBeratError) },
            supportingText = { ErrorHintBerat(inputBeratError) },
            isError = inputBeratError,
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
                inputBeratError = inputBerat == "" || inputBerat == "0"

                if (itemsPosition1Error || itemsPosition2Error || inputBeratError) return@Button

                hasil =
                    if(!isArrowReversed){
                        konversiBerat(inputBerat, listBerat[itemsPosition1], listBerat[itemsPosition2])
                    } else {
                        konversiBerat(inputBerat, listBerat[itemsPosition2], listBerat[itemsPosition1])
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
                    val dariBerat = if (!isArrowReversed) listBerat[itemsPosition1] else listBerat[itemsPosition2]
                    val keBerat = if (!isArrowReversed) listBerat[itemsPosition2] else listBerat[itemsPosition1]

                    val pesan = context.getString(
                        R.string.bagikan_hasil,
                        inputBerat, dariBerat, keBerat, hasil
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
fun konversiBerat(input: String, dari: String, ke: String): String {
    val berat = input.toFloat()

    val hasil = when {
        // Dari Milligram
        dari == "Milligram" && ke == "Gram" -> String.format("%.4f", berat / 1000) + " g"
        dari == "Milligram" && ke == "Kilogram" -> String.format("%.6f", berat / 1_000_000) + " kg"
        dari == "Milligram" && ke == "Ounce" -> String.format("%.6f", berat / 28349.5) + " oz"
        dari == "Milligram" && ke == "Pound" -> String.format("%.6f", berat / 453592.37) + " lb"
        dari == "Milligram" && ke == "Ton" -> String.format("%.9f", berat / 1_000_000_000) + " t"
        dari == "Milligram" && ke == "Stone" -> String.format("%.6f", berat / 6_350_293.18) + " st"

        // Dari Gram
        dari == "Gram" && ke == "Milligram" -> String.format("%.2f", berat * 1000) + " mg"
        dari == "Gram" && ke == "Kilogram" -> String.format("%.3f", berat / 1000) + " kg"
        dari == "Gram" && ke == "Ounce" -> String.format("%.3f", berat / 28.3495) + " oz"
        dari == "Gram" && ke == "Pound" -> String.format("%.3f", berat / 453.59237) + " lb"
        dari == "Gram" && ke == "Ton" -> String.format("%.6f", berat / 1_000_000) + " t"
        dari == "Gram" && ke == "Stone" -> String.format("%.3f", berat / 6350.29318) + " st"

        // Dari Kilogram
        dari == "Kilogram" && ke == "Milligram" -> String.format("%.2f", berat * 1_000_000) + " mg"
        dari == "Kilogram" && ke == "Gram" -> String.format("%.2f", berat * 1000) + " g"
        dari == "Kilogram" && ke == "Ounce" -> String.format("%.2f", berat * 35.274) + " oz"
        dari == "Kilogram" && ke == "Pound" -> String.format("%.2f", berat * 2.20462) + " lb"
        dari == "Kilogram" && ke == "Ton" -> String.format("%.6f", berat / 1000) + " t"
        dari == "Kilogram" && ke == "Stone" -> String.format("%.2f", berat / 6.35029) + " st"

        // Dari Ounce
        dari == "Ounce" && ke == "Milligram" -> String.format("%.2f", berat * 28349.5) + " mg"
        dari == "Ounce" && ke == "Gram" -> String.format("%.2f", berat * 28.3495) + " g"
        dari == "Ounce" && ke == "Kilogram" -> String.format("%.4f", berat / 35.274) + " kg"
        dari == "Ounce" && ke == "Pound" -> String.format("%.4f", berat / 16) + " lb"
        dari == "Ounce" && ke == "Ton" -> String.format("%.6f", berat / 35_274) + " t"
        dari == "Ounce" && ke == "Stone" -> String.format("%.4f", berat / 224) + " st"

        // Dari Pound
        dari == "Pound" && ke == "Milligram" -> String.format("%.2f", berat * 453592.37) + " mg"
        dari == "Pound" && ke == "Gram" -> String.format("%.2f", berat * 453.59237) + " g"
        dari == "Pound" && ke == "Kilogram" -> String.format("%.3f", berat / 2.20462) + " kg"
        dari == "Pound" && ke == "Ounce" -> String.format("%.2f", berat * 16) + " oz"
        dari == "Pound" && ke == "Ton" -> String.format("%.6f", berat / 2204.62) + " t"
        dari == "Pound" && ke == "Stone" -> String.format("%.2f", berat / 14) + " st"

        // Dari Ton
        dari == "Ton" && ke == "Milligram" -> String.format("%.2f", berat * 1_000_000_000) + " mg"
        dari == "Ton" && ke == "Gram" -> String.format("%.2f", berat * 1_000_000) + " g"
        dari == "Ton" && ke == "Kilogram" -> String.format("%.2f", berat * 1000) + " kg"
        dari == "Ton" && ke == "Ounce" -> String.format("%.2f", berat * 35_274) + " oz"
        dari == "Ton" && ke == "Pound" -> String.format("%.2f", berat * 2204.62) + " lb"
        dari == "Ton" && ke == "Stone" -> String.format("%.2f", berat * 157.473) + " st"

        // Dari Stone
        dari == "Stone" && ke == "Milligram" -> String.format("%.2f", berat * 6_350_293.18) + " mg"
        dari == "Stone" && ke == "Gram" -> String.format("%.2f", berat * 6350.29318) + " g"
        dari == "Stone" && ke == "Kilogram" -> String.format("%.2f", berat * 6.35029) + " kg"
        dari == "Stone" && ke == "Ounce" -> String.format("%.2f", berat * 224) + " oz"
        dari == "Stone" && ke == "Pound" -> String.format("%.2f", berat * 14) + " lb"
        dari == "Stone" && ke == "Ton" -> String.format("%.6f", berat / 157.473) + " t"

        // Jika satuan sama
        dari == ke -> String.format("%.2f", berat) + " " + satuanSingkatBerat(ke)

        else -> "Pilih satuan yang valid"
    }

    return hasil
}

fun satuanSingkatBerat(satuan: String): String {
    return when (satuan) {
        "Milligram" -> "mg"
        "Gram" -> "g"
        "Kilogram" -> "kg"
        "Ounce" -> "oz"
        "Pound" -> "lb"
        "Ton" -> "t"
        "Stone" -> "st"
        else -> satuan
    }
}

@Composable
fun IconPickerBerat(isError: Boolean) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }
}

@Composable
fun ErrorHintBerat(isError: Boolean) {
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
private fun BeratScreenPreview() {
    Asessment1Theme {
        BeratScreen(rememberNavController())
    }
}