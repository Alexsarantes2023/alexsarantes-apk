package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.theme.CyberVoid
import com.example.ui.theme.WhatsAppGreen

@Composable
fun FloatingWhatsAppFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.testTag("whatsapp_fab_container"),
        contentAlignment = Alignment.Center
    ) {
        // Outer glow aura
        Box(
            modifier = Modifier
                .size(62.dp)
                .clip(CircleShape)
                .background(WhatsAppGreen.copy(alpha = 0.25f))
        )

        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .size(54.dp)
                .testTag("whatsapp_fab"),
            shape = CircleShape,
            containerColor = WhatsAppGreen,
            contentColor = CyberVoid,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Chat,
                contentDescription = "Falar no WhatsApp",
                modifier = Modifier.size(26.dp),
                tint = Color.White
            )
        }
    }
}
