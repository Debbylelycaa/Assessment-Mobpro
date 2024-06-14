package org.d3if0152.bakuapp.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.d3if0152.bakuapp.R
import org.d3if0152.bakuapp.ui.theme.BaKuAppTheme


@Composable
fun ConfirmDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    if (openDialog) {
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF2ECBE))

            ) {
                Column(
                    modifier = Modifier.padding(16.dp).background(Color(0xFFF2ECBE)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(R.string.pesan_hapus),
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = Color(0xFF9A3B3B)

                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedButton(
                            onClick = { onDismissRequest() },
                            modifier = Modifier.padding(8.dp),
                            border = BorderStroke(1.dp, Color(0xFFE2C799))

                        ) {
                            Text(text = stringResource(R.string.batal),
                                color = Color(0xFFE2C799))

                        }
                        OutlinedButton(
                            onClick = { onConfirmation() },
                            modifier = Modifier.padding(8.dp),
                            border = BorderStroke(1.dp, Color(0xFF9A3B3B))

                        ) {
                            Text(text = stringResource(R.string.tombol_hapus),
                                color = Color(0xFF9A3B3B))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DisplayAlertDialogPreview() {
    BaKuAppTheme {
        ConfirmDialog(
            openDialog = true,
            onDismissRequest = {},
            onConfirmation = {}
        )
    }
}