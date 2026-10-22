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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.NavDestination
import com.example.ui.theme.CyberBorderGlass
import com.example.ui.theme.CyberSurfaceGlass
import com.example.ui.theme.CyberTealLight
import com.example.ui.theme.ElectricIndigoLight
import com.example.ui.theme.TextSecondary

@Composable
fun BottomNavBar(
    currentDestination: NavDestination,
    onNavigate: (NavDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(CyberSurfaceGlass)
            .border(width = 1.dp, color = CyberBorderGlass)
            .navigationBarsPadding()
            .testTag("bottom_nav_bar")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavDestination.entries.forEach { destination ->
                val isSelected = destination == currentDestination
                val activeColor = if (destination == NavDestination.HOME) ElectricIndigoLight else CyberTealLight
                val color = if (isSelected) activeColor else TextSecondary

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onNavigate(destination) }
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                        .testTag("bottom_nav_${destination.name.lowercase()}"),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = getNavIcon(destination),
                        contentDescription = destination.label,
                        tint = color,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = destination.label,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 11.sp,
                            color = color
                        )
                    )
                }
            }
        }
    }
}

private fun getNavIcon(destination: NavDestination): ImageVector {
    return when (destination) {
        NavDestination.HOME -> Icons.Default.Home
        NavDestination.SERVICOS -> Icons.Default.Terminal
        NavDestination.PLANOS -> Icons.Default.Shield
        NavDestination.PAGAMENTO -> Icons.Default.QrCode2
        NavDestination.CONTATO -> Icons.Default.AlternateEmail
    }
}
