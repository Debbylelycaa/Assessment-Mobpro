package org.d3if0152.bakuapp.ui.screen

import android.content.res.Configuration
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.d3if0152.bakuapp.R
import org.d3if0152.bakuapp.ui.theme.BaKuAppTheme

@Composable
fun BooksDialog(
    bitmap: Bitmap?,
    onDismissRequest: () -> Unit,
    onSave: (String, String, Int, Bitmap) -> Unit
) {
    val maroonColor = Color(0xFF9A3B3B)
    val context = LocalContext.current

    var judulBuku by remember { mutableStateOf("") }
    var kategori by remember { mutableStateOf("") }
    var totalHalaman by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismissRequest() }) {

        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF2ECBE))

        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                OutlinedTextField(
                    value = judulBuku,
                    onValueChange = { judulBuku = it },
                    label = { Text(text = stringResource(id = R.string.judul), color = maroonColor)},
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    textStyle = TextStyle(color = maroonColor), // Set text color to maroon
                    modifier = Modifier.padding(top = 8.dp)
                )

                OutlinedTextField(
                    value = kategori,
                    onValueChange = { kategori = it },
                    label = { Text(text = stringResource(id = R.string.penulis), color = maroonColor)  },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next
                    ),
                    textStyle = TextStyle(color = maroonColor), // Set text color to maroon
                    modifier = Modifier.padding(top = 8.dp)
                )
//
                OutlinedTextField(
                    value = totalHalaman,
                    onValueChange = {
                        if (it.toIntOrNull() != null) {
                            totalHalaman = it
                        }
                    },
                    label = { Text(text = stringResource(id = R.string.total_halaman), color = maroonColor) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.None
                    ),
                    textStyle = TextStyle(color = maroonColor), // Set text color to maroon
                    modifier = Modifier.padding(top = 8.dp),

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
                        border = BorderStroke(1.dp, Color(0xFF9A3B3B))

                    ) {
                        Text(text = stringResource(R.string.batal),
                            color = Color(0xFF9A3B3B))
                    }
                    OutlinedButton(
                        onClick = IconButton@{
                            if (judulBuku == "" || kategori == "" || totalHalaman == "" ){
                                Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                                return@IconButton
                            }

                            val totalHalamanValue = totalHalaman.toIntOrNull() ?: 0

                            if (totalHalamanValue <= 0) {
                                Toast.makeText(context, R.string.input_invalid, Toast.LENGTH_LONG).show()
                                return@IconButton
                            }

                            onSave(judulBuku, kategori, totalHalamanValue, bitmap)
                            onDismissRequest()
                        },
//                        enabled = judulBuku.isNotEmpty() && kategori.isNotEmpty() && totalHalaman.isNotEmpty(),
                        modifier = Modifier.padding(8.dp),
                        border = BorderStroke(1.dp, Color(0xFF9A3B3B))


                    ) {
                        Text(text = stringResource(R.string.simpan),
                            color = Color(0xFF9A3B3B)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun AddDialogPreview() {
    BaKuAppTheme {
        BooksDialog(
            bitmap = null,
            onDismissRequest = {},
            onSave = { _, _, _, _ -> }
        )
    }
}
