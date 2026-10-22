package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ServiceCard
import com.example.ui.theme.CyberBorderGlass
import com.example.ui.theme.CyberSurfaceElevated
import com.example.ui.theme.CyberSurfaceGlass
import com.example.ui.theme.CyberTeal
import com.example.ui.theme.ElectricIndigo
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun SpecialtiesGridSection(
    services: List<ServiceCard>,
    onSelectQuoteForService: (ServiceCard) -> Unit,
    modifier: Modifier = Modifier
) {
    var detailService by remember { mutableStateOf<ServiceCard?>(null) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("specialties_section")
    ) {
        // Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Especialidades",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            )
            Text(
                text = "${services.size} Áreas de Atuação",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = CyberTeal,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2x2 Grid using Rows and Columns
        val chunked = services.chunked(2)
        chunked.forEachIndexed { rowIndex, rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                rowItems.forEach { service ->
                    SpecialtyCard(
                        service = service,
                        onOpenDetail = { detailService = service },
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }

    // Detail Dialog
    detailService?.let { service ->
        AlertDialog(
            onDismissRequest = { detailService = null },
            icon = {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(service.accentColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = getServiceIcon(service.id),
                        contentDescription = service.title,
                        tint = service.accentColor,
                        modifier = Modifier.size(26.dp)
                    )
                }
            },
            title = {
                Text(
                    text = service.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextPrimary
                )
            },
            text = {
                Column {
                    Text(
                        text = service.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Escopo & Tecnologias:",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = service.accentColor,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = service.detailedScope,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            lineHeight = 18.sp
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val chosen = service
                        detailService = null
                        onSelectQuoteForService(chosen)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricIndigo)
                ) {
                    Text("Solicitar Orçamento")
                }
            },
            dismissButton = {
                TextButton(onClick = { detailService = null }) {
                    Text("Fechar", color = TextSecondary)
                }
            },
            containerColor = CyberSurfaceElevated,
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
private fun SpecialtyCard(
    service: ServiceCard,
    onOpenDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CyberSurfaceGlass)
            .border(1.dp, CyberBorderGlass, RoundedCornerShape(12.dp))
            .clickable { onOpenDetail() }
            .padding(14.dp)
            .testTag("service_card_${service.id}")
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(service.accentColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = getServiceIcon(service.id),
                        contentDescription = service.title,
                        tint = service.accentColor,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = service.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.5.sp,
                        color = TextPrimary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = service.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 11.sp,
                        lineHeight = 16.sp,
                        color = TextSecondary
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Text(
                    text = "Saiba Mais",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = service.accentColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )
                Spacer(modifier = Modifier.width(3.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = service.accentColor,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}

private fun getServiceIcon(id: String): ImageVector {
    return when (id) {
        "desktop_mobile" -> Icons.Default.Devices
        "blue_team" -> Icons.Default.Shield
        "red_team" -> Icons.Default.BugReport
        "trafego_pago" -> Icons.Default.AdsClick
        else -> Icons.Default.Shield
    }
}
