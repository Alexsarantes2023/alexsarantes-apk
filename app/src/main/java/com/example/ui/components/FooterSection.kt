package com.example.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.R
import com.example.ui.theme.CyberBorderSubtle
import com.example.ui.theme.CyberSurfaceElevated
import com.example.ui.theme.CyberTeal
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun FooterSection(
    onOpenUrl: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 24.dp)
            .testTag("footer_section"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.alex_avatar)
                .crossfade(true)
                .error(R.drawable.alex_avatar)
                .build(),
            placeholder = painterResource(R.drawable.alex_avatar),
            contentDescription = "Foto de perfil de Alexsandro Arantes",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .border(1.5.dp, CyberTeal, CircleShape)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Alexsandro Antonio Arantes",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                fontSize = 16.sp
            )
        )

        Text(
            text = "Developer & CyberSecurity",
            style = MaterialTheme.typography.labelMedium.copy(
                color = CyberTeal,
                fontSize = 11.sp
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Contact info line
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "alexsarantes2023@gmail.com",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            )
            Text(text = "•", color = TextMuted)
            Text(
                text = "13 98223-7027",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            )
            Text(text = "•", color = TextMuted)
            Text(
                text = "Santos - SP",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Social / Link Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledIconButton(
                onClick = { onOpenUrl("https://wa.link/qu0ysc") },
                modifier = Modifier
                    .size(38.dp)
                    .border(1.dp, CyberBorderSubtle, CircleShape)
                    .testTag("footer_whatsapp_button"),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = CyberSurfaceElevated,
                    contentColor = TextSecondary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "WhatsApp",
                    modifier = Modifier.size(17.dp)
                )
            }

            FilledIconButton(
                onClick = { onOpenUrl("https://github.com/Alexsarantes2023") },
                modifier = Modifier
                    .size(38.dp)
                    .border(1.dp, CyberBorderSubtle, CircleShape)
                    .testTag("footer_github_button"),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = CyberSurfaceElevated,
                    contentColor = TextSecondary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Code,
                    contentDescription = "GitHub",
                    modifier = Modifier.size(17.dp)
                )
            }

            FilledIconButton(
                onClick = { onOpenUrl("https://br.linkedin.com/in/alexsandro-antonio-arantes") },
                modifier = Modifier
                    .size(38.dp)
                    .border(1.dp, CyberBorderSubtle, CircleShape)
                    .testTag("footer_linkedin_button"),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = CyberSurfaceElevated,
                    contentColor = TextSecondary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Link,
                    contentDescription = "LinkedIn",
                    modifier = Modifier.size(17.dp)
                )
            }

            FilledIconButton(
                onClick = { onOpenUrl("mailto:alexsarantes2023@gmail.com") },
                modifier = Modifier
                    .size(38.dp)
                    .border(1.dp, CyberBorderSubtle, CircleShape)
                    .testTag("footer_email_button"),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = CyberSurfaceElevated,
                    contentColor = TextSecondary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Mail,
                    contentDescription = "Email",
                    modifier = Modifier.size(17.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "© 2025-2026 Alexsandro A. Arantes. Todos os direitos reservados.",
            style = MaterialTheme.typography.bodySmall.copy(
                color = TextMuted,
                fontSize = 10.5.sp
            )
        )
    }
}
