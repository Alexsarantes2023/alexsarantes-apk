package com.example.ui.components

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Toll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PaymentType
import com.example.ui.theme.CyberBorderGlass
import com.example.ui.theme.CyberBorderSubtle
import com.example.ui.theme.CyberSurfaceContainer
import com.example.ui.theme.CyberSurfaceContainerHigh
import com.example.ui.theme.CyberSurfaceElevated
import com.example.ui.theme.CyberSurfaceGlass
import com.example.ui.theme.CyberTeal
import com.example.ui.theme.DefenseGreen
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun PaymentModuleSection(
    selectedPayment: PaymentType,
    onSelectPayment: (PaymentType) -> Unit,
    onCopyAddress: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("payment_module_section")
    ) {
        // Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Métodos de Pagamento",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                )
                Text(
                    text = "Pix Instantâneo ou Criptoativos Descentralizados",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                )
            }

            Icon(
                imageVector = Icons.Default.QrCode2,
                contentDescription = null,
                tint = CyberTeal,
                modifier = Modifier.size(26.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Segmented Tabs (PIX | Bitcoin | Ethereum)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CyberSurfaceElevated)
                .border(1.dp, CyberBorderSubtle, RoundedCornerShape(12.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            PaymentType.entries.forEach { type ->
                val isSelected = type == selectedPayment
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isSelected) type.accentColor else Color.Transparent
                        )
                        .clickable { onSelectPayment(type) }
                        .padding(vertical = 8.dp)
                        .testTag("tab_${type.name.lowercase()}"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = type.displayName,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.Black else TextSecondary
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Content Panel
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(CyberSurfaceGlass)
                .border(1.dp, CyberBorderGlass, RoundedCornerShape(16.dp))
                .padding(18.dp)
                .testTag("payment_panel")
        ) {
            Column {
                // Header inside card
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = getPaymentIcon(selectedPayment),
                            contentDescription = null,
                            tint = selectedPayment.accentColor,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Pagar com ${selectedPayment.displayName}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = selectedPayment.accentColor,
                                fontSize = 14.sp
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(selectedPayment.accentColor.copy(alpha = 0.12f))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = selectedPayment.badge,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                color = selectedPayment.accentColor,
                                fontSize = 10.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Stylized QR Display Mockup Canvas
                Box(
                    modifier = Modifier
                        .size(175.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(14.dp))
                        .background(CyberSurfaceElevated)
                        .border(1.dp, CyberBorderGlass, RoundedCornerShape(14.dp))
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val accent = selectedPayment.accentColor
                    Canvas(modifier = Modifier.size(140.dp)) {
                        val gridSize = 5
                        val cellSize = size.width / gridSize
                        val gap = 4.dp.toPx()
                        val dotSize = cellSize - gap

                        for (r in 0 until gridSize) {
                            for (c in 0 until gridSize) {
                                val isCorner = (r == 0 && c == 0) || (r == 0 && c == gridSize - 1) || (r == gridSize - 1 && c == 0)
                                val isFilled = (r * 3 + c * 2) % 2 == 0 || isCorner
                                val alpha = if (isCorner) 1f else if (isFilled) 0.75f else 0.25f

                                drawRoundRect(
                                    color = accent.copy(alpha = alpha),
                                    topLeft = Offset(c * cellSize + gap / 2, r * cellSize + gap / 2),
                                    size = Size(dotSize, dotSize),
                                    cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
                                )
                            }
                        }
                    }

                    // Floating Center Icon Badge
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(selectedPayment.accentColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = getPaymentIcon(selectedPayment),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Copy address input container
                Text(
                    text = selectedPayment.addressLabel,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(CyberSurfaceContainer)
                        .border(1.dp, CyberBorderSubtle, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedPayment.addressValue,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.5.sp,
                            color = TextPrimary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    IconButton(
                        onClick = {
                            onCopyAddress(selectedPayment.addressValue, selectedPayment.displayName)
                        },
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(CyberSurfaceContainerHigh)
                            .testTag("copy_address_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copiar chave",
                            tint = selectedPayment.accentColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun getPaymentIcon(type: PaymentType): ImageVector {
    return when (type) {
        PaymentType.PIX -> Icons.Default.Bolt
        PaymentType.BITCOIN -> Icons.Default.CurrencyBitcoin
        PaymentType.ETHEREUM -> Icons.Default.Toll
    }
}
