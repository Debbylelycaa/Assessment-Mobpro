package org.d3if0152.bakuapp.ui.screen

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.d3if0152.bakuapp.R
import org.d3if0152.bakuapp.ui.theme.BaKuAppTheme

@Composable
fun DisplayAlertDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    val maroonColor = Color(0xFF9A3B3B)

    if (openDialog){
        AlertDialog(
            text = { Text(text = stringResource(R.string.pesan_hapus), color = maroonColor              )},
            confirmButton = {
                TextButton(onClick = { onConfirmation()}) {
                    Text(text = stringResource(R.string.tombol_hapus), color = maroonColor)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissRequest()}) {
                    Text(text = stringResource(R.string.tombol_batal), color = maroonColor)
                }
            },
            onDismissRequest = { onDismissRequest ()}
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogPreview() {
    BaKuAppTheme {
        DisplayAlertDialog(
            openDialog = true,
            onDismissRequest = { },
            onConfirmation = {}
        )
    }
}