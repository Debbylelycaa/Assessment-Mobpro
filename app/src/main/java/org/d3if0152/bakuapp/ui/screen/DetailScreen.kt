package org.d3if0152.bakuapp.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0152.bakuapp.R
import org.d3if0152.bakuapp.database.BooksDb
import org.d3if0152.bakuapp.ui.theme.BaKuAppTheme
import org.d3if0152.bakuapp.util.ViewModelFactory

const val KEY_ID_BUKU = "idBooks"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null){
    val context = LocalContext.current
    val db = BooksDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel : DetailViewModel = viewModel(factory = factory)

    val brownColor = Color(0xFFE2C799)
    val maroonColor = Color(0xFF9A3B3B)

    var judul by remember {
        mutableStateOf("")
    }
    var penulis by remember {
        mutableStateOf("")
    }

    val radioOptions = listOf(
        stringResource(id = R.string.fiksi),
        stringResource(id = R.string.nonfiksi)
    )
    var kategori by remember { mutableStateOf(radioOptions[0]) }

    var totalHalaman by remember {
        mutableStateOf("")
    }

    var dibaca by remember {
        mutableStateOf("")
    }

    LaunchedEffect(true){
        if(id == null) return@LaunchedEffect
        val data = viewModel.getBuku(id)?: return@LaunchedEffect
        judul = data.judul
        penulis = data.penulis
        kategori = data.kategori
        totalHalaman = data.totalHalaman.toString() // Mengubah Int menjadi String
        dibaca = data.dibaca.toString() // Mengubah Int menjadi String
    }


    Scaffold(
        modifier = Modifier.background(Color(0xFFFEECE2)),
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
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_buku))
                    else
                        Text(text = stringResource(id = R.string.update))
                        },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = brownColor,
                    titleContentColor = maroonColor,
                ),
                actions = {
                    IconButton(onClick = {
                        if (judul == "" || penulis =="" || kategori =="" || totalHalaman =="" || dibaca ==""){
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (id == null){
                            viewModel.insert(judul, penulis, kategori, totalHalaman.toInt(), dibaca.toInt())
                        } else {
                            viewModel.update(judul, penulis, kategori, totalHalaman.toInt(), dibaca.toInt())
                        }
                        navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.add),
                            tint = maroonColor
                        )
                    }
                    if (id != null){
                        DeleteAction {
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }
                }
            )
        }
    ) { padding ->
FormBuku(
    title = judul,
    onTitleChange = { judul = it },
    author = penulis,
    onAuthorChange = { penulis = it },
    pages = totalHalaman,
    onPagesChange = { totalHalaman = it },
    current = dibaca ,
    onCurrentChange = { dibaca = it },
    modifier = Modifier.padding(padding)
)
    }
}


@Composable
fun FormBuku(
    title: String, onTitleChange: (String) -> Unit,
    author: String, onAuthorChange: (String) -> Unit,
    pages: String, onPagesChange: (String) -> Unit,
    current: String, onCurrentChange: (String) -> Unit,
    modifier: Modifier

){
    val maroonColor = Color(0xFF9A3B3B)

    val radioOptions = listOf(
        stringResource(id = R.string.fiksi),
        stringResource(id = R.string.nonfiksi)
    )
    var kategori by remember { mutableStateOf(radioOptions[0]) }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){

        val yellow = Color(0xFFF2ECBE)

        OutlinedTextField(
            value = title,
            onValueChange = {  onTitleChange(it)},
            label = { Text (text = stringResource(R.string.judul_buku), color = maroonColor)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(yellow)
        )

        OutlinedTextField(
            value = author,
            onValueChange = { onAuthorChange(it)},
            label = { Text (text = stringResource(R.string.penulis_buku), color = maroonColor)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(yellow)
        )

        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .background(yellow)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ){
            radioOptions.forEach{ text ->
                KategoriOpsi(
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
            value = pages,
            onValueChange = { onPagesChange(it) },
            label = { Text (text = stringResource(R.string.total_halaman), color = maroonColor)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(yellow)
        )

        OutlinedTextField(
            value = current,
            onValueChange = { onCurrentChange(it) },
            label = { Text (text = stringResource(R.string.halaman_dibaca), color = maroonColor)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(yellow)
        )
    }
}
@Composable
fun KategoriOpsi(label: String, isSelected: Boolean, modifier: Modifier){
    val maroonColor = Color(0xFF9A3B3B)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp),
            color = maroonColor
        )
    }
}


@Composable
fun DeleteAction(delete: () -> Unit){
    val maroonColor = Color(0xFF9A3B3B)

    var expanded by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.hapus), color = maroonColor)
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    BaKuAppTheme {
        DetailScreen(rememberNavController())
    }
}