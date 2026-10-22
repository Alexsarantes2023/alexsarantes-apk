package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.AccentYellow
import com.example.ui.theme.BlueTeamDefense
import com.example.ui.theme.CyberTeal
import com.example.ui.theme.ElectricIndigo
import com.example.ui.theme.RedTeamPentest

enum class CategoryFilter(val label: String, val dotColor: Color? = null) {
    WEB_FULLSTACK("Web Fullstack"),
    DESKTOP_MOBILE("Desktop & Mobile"),
    BLUE_TEAM("Blue Team (Defesa)", BlueTeamDefense),
    RED_TEAM("Red Team (Pentest)", RedTeamPentest),
    TRAFEGO_PAGO("Tráfego Pago", AccentYellow)
}

data class ServiceCard(
    val id: String,
    val title: String,
    val description: String,
    val detailedScope: String,
    val accentColor: Color,
    val category: CategoryFilter
)

data class PricingPlan(
    val id: String,
    val tag: String,
    val title: String,
    val subtitle: String,
    val price: String,
    val isPopular: Boolean = false,
    val features: List<String>,
    val waText: String
)

data class TrafficPlan(
    val title: String,
    val price: String,
    val features: List<String>,
    val waText: String
)

enum class PaymentType(
    val displayName: String,
    val badge: String,
    val addressLabel: String,
    val addressValue: String,
    val accentColor: Color
) {
    PIX(
        displayName = "PIX",
        badge = "24/7 Sem Taxas",
        addressLabel = "Chave Pix Copia e Cola:",
        addressValue = "41e15c1e-e784-45f6-92f7-8b397686b5cd",
        accentColor = CyberTeal
    ),
    BITCOIN(
        displayName = "Bitcoin",
        badge = "Rede Principal (BTC)",
        addressLabel = "Endereço da Carteira BTC:",
        addressValue = "bc1qqm8hllrvt3ve8n8nwtp6s8f85vnjazl33ze6vh",
        accentColor = AccentYellow
    ),
    ETHEREUM(
        displayName = "Ethereum",
        badge = "Ethereum ERC20",
        addressLabel = "Endereço da Carteira ETH:",
        addressValue = "0xbEccfd3AEb196D11a3c91c5D5aEC42cbf4d4B908",
        accentColor = ElectricIndigo
    )
}

data class FaqItem(
    val id: Int,
    val question: String,
    val answer: String
)

enum class NavDestination(val label: String) {
    HOME("Home"),
    SERVICOS("Serviços"),
    PLANOS("Planos"),
    PAGAMENTO("Pagamento"),
    CONTATO("Contato")
}
