package com.fahmi0003.asesmen2.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fahmi0003.asesmen2.R
import com.fahmi0003.asesmen2.navigation.Screen
import com.fahmi0003.asesmen2.ui.theme.Asesmen2Theme

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HausScreenPreview() {
    Asesmen2Theme {
        MainScreen(rememberNavController())
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HausScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.haus))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.About.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) {
        HausScreen()
    }
}

@SuppressLint("StringFormatInvalid", "StringFormatMatches", "AutoboxingStateCreation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HausScreen() {
    var name by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var kopi by remember { mutableStateOf(false) }
    var jus by remember { mutableStateOf(false) }
    var quantity by remember { mutableStateOf(0) }
    val price = calculatePrice(kopi, jus, quantity)
    var outputText by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text("Pesan Kopi") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (name.isEmpty()) {
                    Text("Masukan pilihan minuman anda!", fontSize = 18.sp)
                }
                innerTextField()
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = kopi, onCheckedChange = { kopi = it })
            Text("Kopi")
            Checkbox(checked = jus, onCheckedChange = { jus = it })
            Text("Jus")
        }
        QuantitySelector(
            quantity,
            onQuantityChange = { quantity = it },
            kopiChecked = kopi,
            jusChecked = jus,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "HARGA Rp.$price",
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = {
                when {
                    (!kopi && !jus) -> {
                        outputText = "Mohon pilih minimal satu minuman."
                    }
                    (quantity <= 0) -> {
                        outputText = "Mohon pilih jumlah minuman terlebih dahulu."
                    }
                    else -> {
                        val kopiText = if (kopi) " Kopi" else ""
                        val jusText = if (jus) " Jus" else ""
                        outputText =
                            "Pesanan Anda $quantity $kopiText$jusText seharga Rp.$price telah berhasil!"
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("PESAN SEKARANG")
        }


        Text(
            text = outputText,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = {
                shareData(
                    context = context,
                    message = context.getString(
                        R.string.bagikan_template,
                        name, kopi, jus, quantity, price
                    )
                )
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal=32.dp, vertical=16.dp)
        ) {
            Text(text = stringResource(id = R.string.bagikan))
        }
    }
}



@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    kopiChecked: Boolean,
    jusChecked: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = { if ((quantity > 0) && (kopiChecked || jusChecked )) onQuantityChange(quantity - 1) },
            enabled = (kopiChecked || jusChecked ),
            modifier = Modifier.padding(end = 30.dp)
        ) {
            Text(" - ")
        }
        Text("$quantity", fontSize = 18.sp, modifier = Modifier.widthIn(min = 40.dp))
        Button(
            onClick = { if (kopiChecked || jusChecked ) onQuantityChange(quantity + 1) },
            enabled = (kopiChecked || jusChecked )
        ) {
            Text(" + ")
        }
    }
}


fun calculatePrice(kopi: Boolean, jus: Boolean, quantity: Int): Int {
    val kopiPrice = 20000
    val jusPrice = 15000
    val kopiJusPrice = kopiPrice + jusPrice


    val additionalPrice = when {
        kopi && jus -> kopiJusPrice
        kopi -> kopiPrice
        jus -> jusPrice

        else -> 0
    }

    return (quantity * additionalPrice)
}

@SuppressLint("QueryPermissionsNeeded")
private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}