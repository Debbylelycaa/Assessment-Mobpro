package org.d3if0152.bakuapp.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0152.bakuapp.R
import org.d3if0152.bakuapp.ui.theme.BaKuAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavHostController){
    val brownColor = Color(0xFFC08261)
    val maroonColor = Color(0xFF9A3B3B)
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = maroonColor
                        )
                    }
                },
                title = { Text(text = stringResource(id = R.string.add)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = brownColor,
                    titleContentColor = maroonColor,
                )
            )
        }
    ) { padding ->
        AddContent(Modifier.padding(padding))
    }
}

@SuppressLint("StringFormatMatches")
@Composable
fun AddContent(modifier: Modifier){
    val brownColor = Color(0xFFC08261)
    val maroonColor = Color(0xFF9A3B3B)

    var judul by remember {
        mutableStateOf("")
    }

    var judulError by remember {
        mutableStateOf(false)
    }

    var penulis by remember {
        mutableStateOf("")
    }

    var penulisError by remember {
        mutableStateOf(false)
    }

    val radioOptions = listOf(
        stringResource(id = R.string.fiksi),
        stringResource(id = R.string.nonfiksi)
    )
    var kategori by remember { mutableStateOf(radioOptions[0]) }

    var totalHalaman by remember {
        mutableStateOf("")
    }
    var totalHalamanError by remember {
        mutableStateOf(false)
    }

    var halamanDibaca by remember {
        mutableStateOf("")
    }
    var halamanDibacaError by remember {
        mutableStateOf(false)
    }

    var progres by remember {
        mutableFloatStateOf(0f)
    }
    var status by remember {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.baku_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = judul,
            onValueChange = {  judul = it},
            label = { Text (text = stringResource(R.string.judul_buku))},
            isError = judulError,
            supportingText = { ErrorHint(judulError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = penulis,
            onValueChange = { penulis = it },
            label = { Text (text = stringResource(R.string.penulis_buku))},
            isError = penulisError,
            supportingText = { ErrorHint(penulisError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ){
            radioOptions.forEach{ text ->
                KategoriOption(
                    label = text ,
                    isSelected = kategori == text,
                    modifier = Modifier
                        .selectable(
                            selected = kategori == text,
                            onClick = { kategori = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }

        OutlinedTextField(
            value = totalHalaman,
            onValueChange = { totalHalaman = it },
            label = { Text (text = stringResource(R.string.total_halaman))},
            isError = totalHalamanError,
            trailingIcon = { IconPicker(totalHalamanError, stringResource(id = R.string.laman) )},
            supportingText = { ErrorHint(totalHalamanError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = halamanDibaca,
            onValueChange = { halamanDibaca = it },
            label = { Text (text = stringResource(R.string.halaman_dibaca))},
            isError = halamanDibacaError,
            trailingIcon = { IconPicker(halamanDibacaError, stringResource(id = R.string.laman) )},
            supportingText = { ErrorHint(halamanDibacaError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                judulError = judul.isEmpty()
                penulisError = penulis.isEmpty()

                try {
                    totalHalaman.toFloat()
                    totalHalamanError = totalHalaman.isEmpty() || totalHalaman.toFloat() <= 0
                } catch (e: NumberFormatException) {
                    totalHalamanError = true
                }
                try {
                    halamanDibaca.toFloat()
                    halamanDibacaError = halamanDibaca.isEmpty() || halamanDibaca.toFloat() > totalHalaman.toFloat() || halamanDibaca.toFloat() < 0
                } catch (e: NumberFormatException) {
                    halamanDibacaError = true
                }

                if (judulError || penulisError || totalHalamanError || halamanDibacaError) return@Button
                progres = hitungProgres(totalHalaman.toFloat(), halamanDibaca.toFloat())
                status = getStatus(progres)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = brownColor,
                contentColor = maroonColor
            ),
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.simpan))
        }

        if (progres != 0f){
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(text = stringResource(R.string.judul_buku_x, judul),
                style = MaterialTheme.typography.titleLarge)

            Text(text = stringResource(R.string.penulis_buku_x, penulis),
                style = MaterialTheme.typography.bodyMedium)

            Text(text = stringResource(R.string.kategori, kategori),
                style = MaterialTheme.typography.bodyMedium)

            Text(text = stringResource(R.string.total_halaman_x, totalHalaman),
                style = MaterialTheme.typography.bodyMedium)

            Text(text = stringResource(R.string.halaman_dibaca_x, halamanDibaca),
                style = MaterialTheme.typography.bodyMedium)

            Text(text = stringResource(R.string.progres_x, progres),
                style = MaterialTheme.typography.bodyMedium)

            Text(text = stringResource(status).uppercase(),
                style = MaterialTheme.typography.bodyMedium)

            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_progress,
                            kategori, judul, progres)
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = brownColor,
                    contentColor = maroonColor
                ),
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ){
                Text(text = stringResource(R.string.share))
            }
        }
    }
}

@Composable
fun KategoriOption(label: String, isSelected: Boolean, modifier: Modifier){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

private fun hitungProgres(totalHalaman : Float, halamanDibaca : Float) : Float{
    return ((halamanDibaca / totalHalaman) * 100)
}

private fun getStatus(progres : Float) : Int {
    return if (progres < 100) {
        R.string.sedang_dibaca
    } else {
        R.string.selesai
    }
}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String){
    if(isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint (isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    BaKuAppTheme {
        AddScreen(rememberNavController())
    }
}