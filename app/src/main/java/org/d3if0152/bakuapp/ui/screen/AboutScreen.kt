package org.d3if0152.bakuapp.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0152.bakuapp.R
import org.d3if0152.bakuapp.ui.theme.BaKuAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController){
    val brownColor = Color(0xFFE2C799)
    val maroonColor = Color(0xFF9A3B3B)
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
                    Text(text = stringResource(id = R.string.about_app))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = brownColor,
                    titleContentColor = maroonColor
                )
            )
        }
    ){
            padding ->
        Column(modifier = Modifier.padding(padding)) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.baku),
                contentDescription = stringResource(R.string.baku_image_description),
                modifier = Modifier.fillMaxWidth().height(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.copyright),
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = stringResource(R.string.about_dev),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutScreenPreview(){
    BaKuAppTheme {
        AboutScreen(rememberNavController())
    }
}