package org.d3if0152.bakuapp.ui.screen

import MainViewModel
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0152.bakuapp.R
import org.d3if0152.bakuapp.database.BooksDb
import org.d3if0152.bakuapp.model.Books
import org.d3if0152.bakuapp.navigation.Screen
import org.d3if0152.bakuapp.ui.theme.BaKuAppTheme
import org.d3if0152.bakuapp.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    val brownColor = Color(0xFFE2C799)
    val maroonColor = Color(0xFF9A3B3B)
    Scaffold(
        modifier = Modifier.background(Color(0xFFFEECE2)),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = brownColor,
                    titleContentColor = maroonColor,
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.about_app),
                            tint = maroonColor
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(Screen.FormBaru.route) },
                Modifier.background(brownColor)
            )
            {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.tambah_buku),
                    tint = maroonColor
                )
            }
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding), navController)
    }
}
@Composable
fun ScreenContent(modifier: Modifier, navController: NavHostController){

    val context = LocalContext.current
    val db = BooksDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel : MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()

    if (data.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.listkosong),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = stringResource(id = R.string.list_kosong),
//                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
    }  else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 84.dp)
        ){
            items(data){
                BooksList(books = it){
                  navController.navigate(Screen.FormUbah.withId(it.id))
                }
                Divider()
            }
        }
    }
}

@Composable
fun BooksList(books: Books, onClick: () -> Unit){
    val yellow = Color(0xFFF2ECBE)
    val maroonColor = Color(0xFF9A3B3B)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
            .background(yellow),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(id = R.string.judul) + books.judul,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            color = maroonColor
        )
        Text(
            text = stringResource(id = R.string.penulis) + books.penulis,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = maroonColor

        )
        Text(
            text = stringResource(id = R.string.kategoriList) + books.kategori,
            color = maroonColor

        )
        Text(
            text = "${books.dibaca}/${books.totalHalaman} ${stringResource(id = R.string.laman)}",
            color = maroonColor

        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    BaKuAppTheme {
        MainScreen(navController = rememberNavController())
    }
}