package fr.upjv.projetandroidccmv1.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import fr.upjv.projetandroidccmv1.ui.model.ItemUi
import fr.upjv.projetandroidccmv1.ui.theme.ProjetAndroidCCMV1Theme
import fr.upjv.projetandroidccmv1.ui.viewmodel.AndroidVersionViewModel

private fun getVersionLogoUrl(versionName: String): String? {
    return when (versionName) {
        "test" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Android_robot.svg/767px-Android_robot.svg.png?20180121030125"
        else -> "https://img.freepik.com/vecteurs-premium/icone-remplissage-noire-android_1076610-103538.jpg?semt=ais_hybrid&w=740&q=80"
    }
}

private fun getVersionUrl(versionName: String): String {
    return when (versionName) {
        "HoneyComb" -> "https://developer.android.com/about/versions/honeycomb"
        "Ice Cream Sandwich" -> "https://developer.android.com/about/versions/ics"
        "Jelly Bean" -> "https://developer.android.com/about/versions/jelly-bean"
        "Kitkat" -> "https://developer.android.com/about/versions/kitkat"
        "Lollipop" -> "https://developer.android.com/about/versions/lollipop"
        "Marshmallow" -> "https://developer.android.com/about/versions/marshmallow"
        "Nougat" -> "https://developer.android.com/about/versions/nougat"
        "Oreo" -> "https://developer.android.com/about/versions/oreo"
        else -> "https://www.android.com/"
    }
}

@Composable
private fun GifImageSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(data = "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExbmkxdnQyb2pocnRpYnRtc2xvaGxxNnphMndsdnYxaXZwNDY4Y3JheSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/OOeKVbtXrd0cJhJFHh/giphy.gif")
                .crossfade(true)
                .build()
        )
        Image(
            modifier = Modifier.size(128.dp),
            painter = painter,
            contentDescription = "Loading indicator gif",
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun ScreenFooter(totalItemsCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp)
            .heightIn(min = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Total d'items : $totalItemsCount",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "© 2025 UPJV",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

@Composable
fun AnimatedHeader(title: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val url = getVersionUrl(title)
    val logoUrl = getVersionLogoUrl(title)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.1f else 1.0f,
        label = "headerScaleAnimation"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
            .padding(bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        logoUrl?.let {
            val logoPainter = rememberAsyncImagePainter(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = it)
                    .build()
            )
            Image(
                painter = logoPainter,
                contentDescription = "$title logo",
                modifier = Modifier
                    .size(36.dp)
                    .scale(scale)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.scale(scale)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navigateBack: () -> Unit) {
    val viewModel: AndroidVersionViewModel = viewModel()
    val listOfResult = viewModel.androidVersionList.collectAsState().value
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Navigation example")
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar {
                Button(
                    modifier = Modifier.weight(1f),
                    content = { Text("Ajouter") },
                    onClick = { viewModel.insertAndroidVersion() }
                )
                Button(
                    modifier = Modifier.weight(1f),
                    content = { Text("Clean") },
                    onClick = { viewModel.deleteAllAndroidVersion() }
                )
            }
        }
    ) { innerPadding ->
        MyScreenContent(
            modifier = Modifier.padding(innerPadding),
            listOfResult = listOfResult,
        )
    }
}



@Composable
fun MyScreenContent(
    modifier: Modifier = Modifier,
    listOfResult: List<ItemUi>
) {
    val totalItemsCount = listOfResult.count { it is ItemUi.Item }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        GifImageSection()

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            items(listOfResult) { item ->
                when (item) {
                    is ItemUi.Header -> {
                        Spacer(modifier = Modifier.height(16.dp))
                        AnimatedHeader(title = item.title)
                    }

                    is ItemUi.Item -> Text(
                        text = "Version : ${item.versionNumber}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp, top = 2.dp, bottom = 2.dp)
                    )

                    is ItemUi.Footer -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 16.dp)
                        ) {
                            Divider(
                                color = MaterialTheme.colorScheme.outlineVariant,
                                thickness = 1.dp,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Text(
                                text = "Total de cette section : ${item.count} éléments",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, end = 16.dp)
                                    .align(Alignment.End)
                            )
                        }
                    }
                }
            }
        }
        ScreenFooter(totalItemsCount = totalItemsCount)
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    ProjetAndroidCCMV1Theme {
        SecondScreen(navigateBack = {})
    }
}