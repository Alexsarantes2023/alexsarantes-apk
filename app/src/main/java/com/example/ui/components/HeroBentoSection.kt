package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Terminal
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
import com.example.ui.theme.CyberBorderGlass
import com.example.ui.theme.CyberBorderSubtle
import com.example.ui.theme.CyberSurfaceContainer
import com.example.ui.theme.CyberSurfaceContainerHigh
import com.example.ui.theme.CyberSurfaceGlass
import com.example.ui.theme.CyberTeal
import com.example.ui.theme.CyberTealLight
import com.example.ui.theme.ElectricIndigo
import com.example.ui.theme.ElectricIndigoLight
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroBentoSection(
    onHirePlansClick: () -> Unit,
    onLearnMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val techStacks = listOf(
        "React Native",
        "Node.js",
        "Python",
        "MongoDB",
        "Kali Linux",
        "Docker",
        "SEO Specialist"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CyberSurfaceGlass)
            .border(1.dp, CyberBorderGlass, RoundedCornerShape(16.dp))
            .padding(18.dp)
            .testTag("hero_bento_card")
    ) {
        Column {
            // Header: Category label + Terminal Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "FULLSTACK & ETHICAL HACKER",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = ElectricIndigoLight,
                            letterSpacing = 1.2.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Desenvolvedor",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                    Text(
                        text = "Web Fullstack",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = CyberTealLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                }

                // Terminal icon box
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CyberSurfaceContainer)
                        .border(1.dp, CyberBorderGlass, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Terminal,
                        contentDescription = "Terminal Icon",
                        tint = CyberTeal,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Bio description
            Text(
                text = "Desenvolvedor web fullstack é um profissional que trabalha tanto no front-end quanto no back-end de aplicações web. Cria interfaces de usuário, gerencia servidores e bancos de dados, e integra APIs. Possui habilidades em diversas linguagens de programação e frameworks.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    lineHeight = 19.sp,
                    fontSize = 12.5.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tech Stack Matrix
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = CyberBorderSubtle,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(CyberSurfaceContainer.copy(alpha = 0.5f))
                    .padding(12.dp)
            ) {
                Text(
                    text = "STACK & AMBIENTES",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextMuted,
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    techStacks.forEach { tech ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(CyberSurfaceContainerHigh)
                                .border(1.dp, CyberBorderSubtle, RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = tech,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 11.sp,
                                    color = TextPrimary
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onHirePlansClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("hero_hire_button"),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ElectricIndigo,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "CONTRATAR PLANOS",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp,
                            fontSize = 12.sp
                        )
                    )
                }

                OutlinedButton(
                    onClick = onLearnMoreClick,
                    modifier = Modifier
                        .height(44.dp)
                        .testTag("hero_learn_more_button"),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = CyberSurfaceContainerHigh,
                        contentColor = CyberTealLight
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(CyberTeal.copy(alpha = 0.4f))
                    )
                ) {
                    Text(
                        text = "SAIBA MAIS",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}
