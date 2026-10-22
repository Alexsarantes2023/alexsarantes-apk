package com.example.viewmodel

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.QuoteEntity
import com.example.model.CategoryFilter
import com.example.model.FaqItem
import com.example.model.NavDestination
import com.example.model.PaymentType
import com.example.model.PricingPlan
import com.example.model.ServiceCard
import com.example.model.TrafficPlan
import com.example.ui.theme.AccentYellow
import com.example.ui.theme.BlueTeamDefense
import com.example.ui.theme.CyberTeal
import com.example.ui.theme.RedTeamPentest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class QuoteFormState(
    val name: String = "",
    val email: String = "",
    val message: String = "",
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)

class PortfolioViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val quoteDao = db.quoteDao()

    val savedQuotes: StateFlow<List<QuoteEntity>> = quoteDao.getAllQuotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _currentNavDestination = MutableStateFlow(NavDestination.HOME)
    val currentNavDestination: StateFlow<NavDestination> = _currentNavDestination.asStateFlow()

    private val _selectedCategory = MutableStateFlow(CategoryFilter.WEB_FULLSTACK)
    val selectedCategory: StateFlow<CategoryFilter> = _selectedCategory.asStateFlow()

    private val _selectedPaymentType = MutableStateFlow(PaymentType.PIX)
    val selectedPaymentType: StateFlow<PaymentType> = _selectedPaymentType.asStateFlow()

    private val _expandedFaqIds = MutableStateFlow<Set<Int>>(setOf(1)) // default open first item
    val expandedFaqIds: StateFlow<Set<Int>> = _expandedFaqIds.asStateFlow()

    private val _quoteForm = MutableStateFlow(QuoteFormState())
    val quoteForm: StateFlow<QuoteFormState> = _quoteForm.asStateFlow()

    private val _selectedServiceDetail = MutableStateFlow<ServiceCard?>(null)
    val selectedServiceDetail: StateFlow<ServiceCard?> = _selectedServiceDetail.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent.asSharedFlow()

    // Pre-configured services from stitch & design
    val services = listOf(
        ServiceCard(
            id = "desktop_mobile",
            title = "Desktop & Mobile",
            description = "Criação de aplicativos para computadores e smartphones, otimizando interfaces touch e alto rendimento.",
            detailedScope = "Desenvolvimento nativo e híbrido com Jetpack Compose, Kotlin e React Native. Otimização de renderização, suporte offline, consumo inteligente de bateria e integração com sensores e APIs em tempo real.",
            accentColor = CyberTeal,
            category = CategoryFilter.DESKTOP_MOBILE
        ),
        ServiceCard(
            id = "blue_team",
            title = "Blue Team",
            description = "Defesa cibernética proativa, monitoramento, mitigação de ameaças e análise forense digital de incidentes.",
            detailedScope = "Arquitetura de segurança defensiva, SIEM, análise de logs, hardening de servidores Linux/Windows, resposta a incidentes críticos e proteção perimetral com firewalls e WAFs.",
            accentColor = BlueTeamDefense,
            category = CategoryFilter.BLUE_TEAM
        ),
        ServiceCard(
            id = "red_team",
            title = "Red Team Pentest",
            description = "Simulação de ataques ofensivos para identificar e explorar brechas de segurança antes de invasores reais.",
            detailedScope = "Testes de intrusão autorizados em redes, aplicações web e mobile, engenharia social técnica, bypass de autenticação e relatórios executivos com recomendações de correção imediata.",
            accentColor = RedTeamPentest,
            category = CategoryFilter.RED_TEAM
        ),
        ServiceCard(
            id = "trafego_pago",
            title = "Tráfego Pago",
            description = "Google ADS, Meta ADS e YouTube ADS. Estratégias táticas para escalar leads e tráfego qualificado.",
            detailedScope = "Estruturação de funis de conversão de alto impacto, copywriting persuasivo, pixel tracking avançado, testes A/B de criativos e otimização de custo por aquisição (CPA) com ROI mensurável.",
            accentColor = AccentYellow,
            category = CategoryFilter.TRAFEGO_PAGO
        )
    )

    // Pricing Plans
    val pricingPlans = listOf(
        PricingPlan(
            id = "light",
            tag = "Econômico",
            title = "Plano Light",
            subtitle = "Front-end Plano Economico e Responsivo.",
            price = "R$ 1.200",
            isPopular = false,
            features = listOf(
                "Página Simples Personalizada",
                "Inclui SEO Google",
                "Tags de Ranqueamento",
                "Pré Suporte e Registro"
            ),
            waText = "Olá, gostaria de contratar o *Plano Light* de R$ 1200."
        ),
        PricingPlan(
            id = "premium",
            tag = "Standard",
            title = "Plano Premium",
            subtitle = "FrontEnd Modo Standart mais elaborado.",
            price = "R$ 2.200",
            isPopular = true,
            features = listOf(
                "Front-end Completo e Responsivo",
                "Desenvolvimento padrão SEO avançado",
                "Envio com formulário ao email",
                "Inclusão Google Analytics",
                "Pré Suporte e Registro"
            ),
            waText = "Olá, gostaria de contratar o *Plano Premium* de R$ 2200."
        ),
        PricingPlan(
            id = "empresarial",
            tag = "FullStack Pro",
            title = "Plano Empresarial",
            subtitle = "Plano FullStack o mais completo.",
            price = "R$ 4.400",
            isPopular = false,
            features = listOf(
                "Front-end e Back-end estruturados",
                "Login, Cadastro e Autenticação",
                "Integração de Banco de Dados",
                "Tags de Ranqueamento & Google Analytics"
            ),
            waText = "Olá, gostaria de contratar o *Plano Empresarial* de R$ 4400."
        )
    )

    val trafficPlan = TrafficPlan(
        title = "Plano Light Tráfego",
        price = "R$ 500,00",
        features = listOf(
            "Campanhas no Google ADS",
            "Campanhas no YouTube ADS",
            "Campanhas no Meta ADS",
            "Análise de Resultados das Campanhas"
        ),
        waText = "Olá, gostaria de contratar o *Plano Light* de Tráfego Pago Serviço R$ 500,00"
    )

    val faqItems = listOf(
        FaqItem(
            id = 1,
            question = "Sobre os planos de serviço?",
            answer = "1º Plano Light: ideal para sites institucionais; 2º Mais Popular: perfeito para site institucional ou landing capturar leads; 3º Plano Empresarial: desenvolvimento FullStack completo com banco de dados."
        ),
        FaqItem(
            id = 2,
            question = "Como contatar um plano personalizado?",
            answer = "Acesse a seção de contato ou envie mensagem via WhatsApp. Explique a complexidade de sua demanda e receba proposta sob medida."
        ),
        FaqItem(
            id = 3,
            question = "Como pagar? Métodos de pagamento?",
            answer = "Aceitamos Pix 24/7 com QR Code ou Chave, Cartão parcelado em até 12x via PicPay/Banco e criptomoedas como Bitcoin e Ethereum."
        )
    )

    fun setNavDestination(destination: NavDestination) {
        _currentNavDestination.value = destination
    }

    fun selectCategory(category: CategoryFilter) {
        _selectedCategory.value = category
    }

    fun selectPaymentType(paymentType: PaymentType) {
        _selectedPaymentType.value = paymentType
    }

    fun toggleFaq(id: Int) {
        _expandedFaqIds.value = if (_expandedFaqIds.value.contains(id)) {
            _expandedFaqIds.value - id
        } else {
            _expandedFaqIds.value + id
        }
    }

    fun showServiceDetail(service: ServiceCard?) {
        _selectedServiceDetail.value = service
    }

    fun updateQuoteName(name: String) {
        _quoteForm.value = _quoteForm.value.copy(name = name, errorMessage = null)
    }

    fun updateQuoteEmail(email: String) {
        _quoteForm.value = _quoteForm.value.copy(email = email, errorMessage = null)
    }

    fun updateQuoteMessage(message: String) {
        _quoteForm.value = _quoteForm.value.copy(message = message, errorMessage = null)
    }

    fun copyToClipboard(context: Context, text: String, label: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "$label copiada com sucesso!", Toast.LENGTH_SHORT).show()
    }

    fun submitQuote(context: Context, onOpenWhatsApp: Boolean = false) {
        val current = _quoteForm.value
        if (current.name.isBlank()) {
            _quoteForm.value = current.copy(errorMessage = "Por favor, digite seu nome.")
            return
        }
        if (current.email.isBlank() || !current.email.contains("@")) {
            _quoteForm.value = current.copy(errorMessage = "Por favor, informe um e-mail válido.")
            return
        }
        if (current.message.isBlank()) {
            _quoteForm.value = current.copy(errorMessage = "Por favor, descreva as necessidades do projeto.")
            return
        }

        viewModelScope.launch {
            _quoteForm.value = current.copy(isSubmitting = true)
            // Save locally to Room database
            val quote = QuoteEntity(
                name = current.name.trim(),
                email = current.email.trim(),
                message = current.message.trim()
            )
            quoteDao.insertQuote(quote)

            _quoteForm.value = QuoteFormState(
                successMessage = "Orçamento registrado com sucesso!"
            )
            Toast.makeText(context, "Orçamento enviado com sucesso!", Toast.LENGTH_LONG).show()

            if (onOpenWhatsApp) {
                openWhatsApp(
                    context,
                    "Olá Alexsandro! Meu nome é ${quote.name} (${quote.email}). Gostaria de um orçamento para o seguinte projeto:\n\n${quote.message}"
                )
            }
        }
    }

    fun openWhatsApp(context: Context, text: String) {
        try {
            val encoded = URLEncoder.encode(text, StandardCharsets.UTF_8.toString())
            val uri = Uri.parse("https://wa.me/5513982237027?text=$encoded")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Não foi possível abrir o WhatsApp: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    fun openUrl(context: Context, url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Erro ao abrir link: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendEmail(context: Context, subject: String, body: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:alexsarantes2023@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }
            context.startActivity(Intent.createChooser(intent, "Enviar e-mail para Alexsandro"))
        } catch (e: Exception) {
            Toast.makeText(context, "Nenhum aplicativo de e-mail encontrado.", Toast.LENGTH_SHORT).show()
        }
    }
}
