package com.example.exoplayertutorial2

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.exoplayertutorial2.ui.theme.ExoplayerTutorial2Theme
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExoplayerTutorial2Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    VideoScreen()
                }
            }
        }
    }
}

@Composable
fun VideoScreen() {
    val playWhenReady by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val mediaItem = MediaItem.fromUri("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
    val playerView = StyledPlayerView(context)
    val player = ExoPlayer.Builder(context).build()
    player.setMediaItem(mediaItem)
    playerView.player = player
    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                playerView
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExoplayerTutorial2Theme {
        VideoScreen()
    }
}