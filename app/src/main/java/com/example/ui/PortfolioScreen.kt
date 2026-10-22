package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.model.NavDestination
import com.example.ui.components.BottomNavBar
import com.example.ui.components.CategoryPillsSection
import com.example.ui.components.FaqSection
import com.example.ui.components.FloatingWhatsAppFab
import com.example.ui.components.FooterSection
import com.example.ui.components.HeroBentoSection
import com.example.ui.components.PaymentModuleSection
import com.example.ui.components.PricingSection
import com.example.ui.components.QuoteFormSection
import com.example.ui.components.SpecialtiesGridSection
import com.example.ui.components.TopBarSection
import com.example.ui.components.TrafficSection
import com.example.ui.theme.CyberVoid
import com.example.viewmodel.PortfolioViewModel
import kotlinx.coroutines.launch

// Section indexes in the LazyColumn for smooth auto-scroll
private const val INDEX_CATEGORY_PILLS = 0
private const val INDEX_HERO_BENTO = 1
private const val INDEX_SPECIALTIES = 2
private const val INDEX_PRICING = 3
private const val INDEX_TRAFFIC = 4
private const val INDEX_PAYMENT = 5
private const val INDEX_FAQ = 6
private const val INDEX_QUOTE_FORM = 7
private const val INDEX_FOOTER = 8

@Composable
fun PortfolioScreen(
    viewModel: PortfolioViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }

    val currentNav by viewModel.currentNavDestination.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val selectedPayment by viewModel.selectedPaymentType.collectAsState()
    val expandedFaqIds by viewModel.expandedFaqIds.collectAsState()
    val quoteFormState by viewModel.quoteForm.collectAsState()
    val savedQuotes by viewModel.savedQuotes.collectAsState()

    // Listen for toast/snackbar events
    LaunchedEffect(Unit) {
        viewModel.toastEvent.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(CyberVoid)
            .testTag("portfolio_screen"),
        topBar = {
            TopBarSection()
        },
        bottomBar = {
            BottomNavBar(
                currentDestination = currentNav,
                onNavigate = { destination ->
                    viewModel.setNavDestination(destination)
                    coroutineScope.launch {
                        when (destination) {
                            NavDestination.HOME -> listState.animateScrollToItem(INDEX_CATEGORY_PILLS)
                            NavDestination.SERVICOS -> listState.animateScrollToItem(INDEX_SPECIALTIES)
                            NavDestination.PLANOS -> listState.animateScrollToItem(INDEX_PRICING)
                            NavDestination.PAGAMENTO -> listState.animateScrollToItem(INDEX_PAYMENT)
                            NavDestination.CONTATO -> listState.animateScrollToItem(INDEX_QUOTE_FORM)
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingWhatsAppFab(
                onClick = {
                    viewModel.openWhatsApp(
                        context,
                        "Olá Alexsandro, gostaria de um orçamento!"
                    )
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = CyberVoid
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 600.dp) // Responsive constraint for tablets & foldables
                    .padding(horizontal = 14.dp)
                    .testTag("portfolio_lazy_column")
            ) {
                // Item 0: Category Filter Pills
                item(key = "category_pills") {
                    Spacer(modifier = Modifier.height(10.dp))
                    CategoryPillsSection(
                        selectedCategory = selectedCategory,
                        onCategorySelected = { category ->
                            viewModel.selectCategory(category)
                            coroutineScope.launch {
                                listState.animateScrollToItem(INDEX_SPECIALTIES)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Item 1: Hero Bento Profile Card
                item(key = "hero_bento") {
                    HeroBentoSection(
                        onHirePlansClick = {
                            viewModel.setNavDestination(NavDestination.PLANOS)
                            coroutineScope.launch {
                                listState.animateScrollToItem(INDEX_PRICING)
                            }
                        },
                        onLearnMoreClick = {
                            viewModel.setNavDestination(NavDestination.SERVICOS)
                            coroutineScope.launch {
                                listState.animateScrollToItem(INDEX_SPECIALTIES)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Item 2: Tactical Capabilities Grid (Especialidades)
                item(key = "specialties_grid") {
                    SpecialtiesGridSection(
                        services = viewModel.services,
                        onSelectQuoteForService = { service ->
                            viewModel.updateQuoteMessage("Gostaria de um orçamento detalhado para o serviço de ${service.title}.")
                            viewModel.setNavDestination(NavDestination.CONTATO)
                            coroutineScope.launch {
                                listState.animateScrollToItem(INDEX_QUOTE_FORM)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Item 3: Pricing Plans
                item(key = "pricing_plans") {
                    PricingSection(
                        plans = viewModel.pricingPlans,
                        onSelectPlan = { plan ->
                            viewModel.openWhatsApp(context, plan.waText)
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Item 4: Traffic Management Plan
                item(key = "traffic_plan") {
                    TrafficSection(
                        trafficPlan = viewModel.trafficPlan,
                        onHireTraffic = { plan ->
                            viewModel.openWhatsApp(context, plan.waText)
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Item 5: Payment Modules (Pix, Bitcoin, Ethereum)
                item(key = "payment_module") {
                    PaymentModuleSection(
                        selectedPayment = selectedPayment,
                        onSelectPayment = { type ->
                            viewModel.selectPaymentType(type)
                        },
                        onCopyAddress = { address, label ->
                            viewModel.copyToClipboard(context, address, label)
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Item 6: FAQ Accordion
                item(key = "faq_section") {
                    FaqSection(
                        faqItems = viewModel.faqItems,
                        expandedIds = expandedFaqIds,
                        onToggleFaq = { id -> viewModel.toggleFaq(id) }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Item 7: Quote Request Form
                item(key = "quote_form") {
                    QuoteFormSection(
                        formState = quoteFormState,
                        savedQuotes = savedQuotes,
                        onNameChange = { viewModel.updateQuoteName(it) },
                        onEmailChange = { viewModel.updateQuoteEmail(it) },
                        onMessageChange = { viewModel.updateQuoteMessage(it) },
                        onSubmit = { viewModel.submitQuote(context, onOpenWhatsApp = false) },
                        onSubmitAndWhatsApp = { viewModel.submitQuote(context, onOpenWhatsApp = true) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Item 8: Footer
                item(key = "footer") {
                    FooterSection(
                        onOpenUrl = { url -> viewModel.openUrl(context, url) }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}
