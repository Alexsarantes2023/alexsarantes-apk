package com.example.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.ui.theme.CyberBorderGlass
import com.example.ui.theme.CyberSurfaceElevated
import com.example.ui.theme.CyberSurfaceGlass
import com.example.ui.theme.CyberTeal
import com.example.ui.theme.CyberVoid
import com.example.ui.theme.DefenseGreen
import com.example.ui.theme.ElectricIndigoLight
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun TopBarSection(
    modifier: Modifier = Modifier
) {
    var showSecurityDialog by remember { mutableStateOf(false) }

    // Pulsing online dot animation
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_transition")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(CyberSurfaceGlass)
            .border(width = 1.dp, color = CyberBorderGlass)
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .testTag("top_bar_section")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar with verified badge
            Box(
                modifier = Modifier.size(42.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
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
                        .size(38.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, CyberTeal, CircleShape)
                )

                // Online indicator
                Box(
                    modifier = Modifier
                        .size(11.dp)
                        .clip(CircleShape)
                        .background(CyberVoid)
                        .padding(1.5.dp)
                        .clip(CircleShape)
                        .background(DefenseGreen)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Name & Availability status
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Alexsandro A. Arantes",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    ),
                    maxLines = 1
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(DefenseGreen)
                            .alpha(pulseAlpha)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "DISPONÍVEL PARA PROJETOS",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = CyberTeal,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.8.sp,
                            fontSize = 9.5.sp
                        )
                    )
                }
            }

            // Security Info Button
            IconButton(
                onClick = { showSecurityDialog = true },
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(CyberSurfaceElevated)
                    .border(1.dp, CyberBorderGlass, RoundedCornerShape(8.dp))
                    .testTag("security_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = "Segurança e Verificação",
                    tint = ElectricIndigoLight,
                    modifier = Modifier.size(19.dp)
                )
            }
        }
    }

    if (showSecurityDialog) {
        AlertDialog(
            onDismissRequest = { showSecurityDialog = false },
            title = {
                Text(
                    text = "Perfil Verificado & Seguro",
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextPrimary
                )
            },
            text = {
                Column {
                    Text(
                        text = "Profissional especialista em Cyber Threat Intelligence, Red Team Pentest e Engenharia FullStack.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "• Comunicação criptografada ponta a ponta via WhatsApp\n• Chaves públicas e métodos de pagamento verificados\n• Contratos com conformidade LGPD e confidencialidade",
                        style = MaterialTheme.typography.bodySmall,
                        color = CyberTeal
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showSecurityDialog = false }) {
                    Text("OK", color = ElectricIndigoLight)
                }
            },
            containerColor = CyberSurfaceElevated,
            shape = RoundedCornerShape(16.dp)
        )
    }
}
