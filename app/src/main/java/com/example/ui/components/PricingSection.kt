package com.example.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PricingPlan
import com.example.ui.theme.CyberBorderGlass
import com.example.ui.theme.CyberBorderSubtle
import com.example.ui.theme.CyberSurfaceContainer
import com.example.ui.theme.CyberSurfaceContainerHigh
import com.example.ui.theme.CyberSurfaceGlass
import com.example.ui.theme.CyberTeal
import com.example.ui.theme.CyberTealLight
import com.example.ui.theme.DefenseGreen
import com.example.ui.theme.ElectricIndigo
import com.example.ui.theme.ElectricIndigoLight
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun PricingSection(
    plans: List<PricingPlan>,
    onSelectPlan: (PricingPlan) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("pricing_section")
    ) {
        // Section Title & Subtitle
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Valores de outros planeta",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Nossos preços cabem no seu orçamento, até os mais modestos.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cards list
        plans.forEach { plan ->
            PricingCard(
                plan = plan,
                onHire = { onSelectPlan(plan) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp)
            )
        }
    }
}

@Composable
private fun PricingCard(
    plan: PricingPlan,
    onHire: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (plan.isPopular) ElectricIndigo else CyberBorderGlass
    val borderWidth = if (plan.isPopular) 2.dp else 1.dp
    val priceColor = when (plan.id) {
        "light" -> CyberTealLight
        "premium" -> ElectricIndigoLight
        "empresarial" -> DefenseGreen
        else -> ElectricIndigoLight
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(CyberSurfaceGlass)
            .border(borderWidth, borderColor, RoundedCornerShape(16.dp))
            .padding(16.dp)
            .testTag("pricing_card_${plan.id}")
    ) {
        Column {
            // "Mais Popular" badge
            if (plan.isPopular) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(9999.dp))
                            .background(ElectricIndigo)
                            .padding(horizontal = 10.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "MAIS POPULAR",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.sp,
                                letterSpacing = 0.8.sp
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            // Top info: Tag + Title + Price
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(CyberSurfaceContainer)
                            .border(1.dp, CyberBorderSubtle, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = plan.tag.uppercase(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                color = TextSecondary,
                                fontSize = 9.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = plan.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = TextPrimary
                        )
                    )

                    Text(
                        text = plan.subtitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            fontSize = 11.5.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = plan.price,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = priceColor,
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "/Pg único",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            color = TextSecondary,
                            fontSize = 9.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Checklist
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                plan.features.forEach { feature ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = DefenseGreen,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = feature,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextSecondary,
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // CTA Button
            if (plan.isPopular) {
                Button(
                    onClick = onHire,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .testTag("hire_${plan.id}"),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ElectricIndigo,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Contratar",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            } else {
                OutlinedButton(
                    onClick = onHire,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .testTag("hire_${plan.id}"),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = CyberSurfaceContainerHigh,
                        contentColor = if (plan.id == "light") CyberTealLight else TextPrimary
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(
                            if (plan.id == "light") CyberTeal.copy(alpha = 0.4f) else CyberBorderSubtle
                        )
                    )
                ) {
                    Text(
                        text = "Contratar",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}
