package com.fahmi0003.asesmen2.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fahmi0003.asesmen2.R
import com.fahmi0003.asesmen2.ui.theme.Asesmen2Theme


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutScreenPreview() {
    Asesmen2Theme {
        AboutScreen(rememberNavController())
    }
}

@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            AboutTopAppBar(navController)
        }
    ) { padding ->
        AboutContent(padding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutTopAppBar(navController: NavHostController) {
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
            Text(text = stringResource(id = R.string.tentang_aplikasi))
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Composable
fun AboutContent(padding: PaddingValues) {
    Text(
        text = stringResource(R.string.copyright),
        modifier = Modifier
            .padding(padding)
            .padding(16.dp)
    )
}
