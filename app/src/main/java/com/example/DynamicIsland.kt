package com.example

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class IslandState {
    IDLE,
    EXPANDED,
    MUSIC,
    CALL
}

@Composable
fun DynamicIsland(
    modifier: Modifier = Modifier,
    islandState: IslandState,
    onStateChange: (IslandState) -> Unit
) {
    val width by animateDpAsState(
        targetValue = when (islandState) {
            IslandState.IDLE -> 120.dp
            IslandState.EXPANDED -> 300.dp
            IslandState.MUSIC -> 280.dp
            IslandState.CALL -> 320.dp
        },
        animationSpec = spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow), label = "width"
    )

    val height by animateDpAsState(
        targetValue = when (islandState) {
            IslandState.IDLE -> 35.dp
            IslandState.EXPANDED -> 180.dp
            IslandState.MUSIC -> 120.dp
            IslandState.CALL -> 80.dp
        },
        animationSpec = spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow), label = "height"
    )

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(32.dp))
            .background(Color.Black)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                if (islandState == IslandState.IDLE) {
                    onStateChange(IslandState.EXPANDED)
                } else {
                    onStateChange(IslandState.IDLE)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = islandState == IslandState.IDLE,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
              Box(modifier = Modifier.size(16.dp).clip(RoundedCornerShape(8.dp)).background(Color.DarkGray))
              Box(modifier = Modifier.size(16.dp).clip(RoundedCornerShape(8.dp)).background(Color.DarkGray))
            }
        }

        AnimatedVisibility(
            visible = islandState == IslandState.EXPANDED,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Dynamic Island AI",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { onStateChange(IslandState.MUSIC) }) {
                        Icon(imageVector = Icons.Default.MusicNote, contentDescription = "Music", tint = Color.White)
                    }
                    IconButton(onClick = { onStateChange(IslandState.CALL) }) {
                        Icon(imageVector = Icons.Default.Call, contentDescription = "Call", tint = Color.Green)
                    }
                    IconButton(onClick = { /* AI */ }) {
                        Icon(imageVector = Icons.Default.Face, contentDescription = "AI", tint = Color.Cyan)
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = islandState == IslandState.MUSIC,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                 Box(modifier = Modifier.size(48.dp).clip(RoundedCornerShape(8.dp)).background(Color.DarkGray))
                 Spacer(modifier = Modifier.width(16.dp))
                 Column(modifier = Modifier.weight(1f)) {
                     Text("Now Playing", color = Color.White, fontWeight = FontWeight.Bold)
                     Text("Chill Vibes", color = Color.Gray, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                 }
                 IconButton(onClick = { /* Play/Pause */ }) {
                     Icon(imageVector = Icons.Default.Pause, contentDescription = "Pause", tint = Color.White)
                 }
            }
        }

        AnimatedVisibility(
             visible = islandState == IslandState.CALL,
             enter = fadeIn(),
             exit = fadeOut()
        ) {
             Row(
                 modifier = Modifier.fillMaxWidth().padding(16.dp),
                 horizontalArrangement = Arrangement.SpaceBetween,
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Row(verticalAlignment = Alignment.CenterVertically) {
                     Icon(imageVector = Icons.Default.Call, contentDescription = "Call", tint = Color.Green)
                     Spacer(modifier = Modifier.width(8.dp))
                     Text("00:45", color = Color.White)
                 }
                 Icon(imageVector = Icons.Default.Call, contentDescription = "End", tint = Color.Red, modifier = Modifier.size(24.dp))
             }
        }
    }
}
