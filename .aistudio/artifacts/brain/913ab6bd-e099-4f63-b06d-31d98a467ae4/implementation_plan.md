# Alexsandro Arantes - App Mobile APK

A high-performance, dark tech portfolio and cybersecurity services mobile application for **Alexsandro A. Arantes** (FullStack Developer & Cyber Security Specialist), faithfully implementing the design system from `alexarantes.com.br`, the provided UI mockup, and the stitch specifications.

---

### User Review & Critical Decisions

> [!IMPORTANT]
> The app is structured as an edge-to-edge native Android experience featuring **Dark Tech Glassmorphism** (`#0B0F19`, `#111827`, `#6366F1`, `#06B6D4`, `#10B981`) with interactive navigation, smooth category filtering, direct WhatsApp & Email dispatch, copy-to-clipboard payment rails (PIX, Bitcoin, Ethereum), interactive FAQ accordion, and a functional quote request form with local persistence.

- **Navigation Strategy**: Smooth-scrolling unified dashboard with active section anchoring and Bottom Bar tabs (Home, Serviços, Planos, Pagamento, Contato), allowing one-tap jumps and deep linking.
- **Direct Dispatch & Offline Storage**: The Quote Form validates inputs, records inquiries locally using Room/preferences for offline durability, and provides instant one-tap dispatch to Alexsandro's WhatsApp (`13 98223-7027`) and corporate email (`alexsarantes2023@gmail.com`).
- **Interactive Security & Payment Tools**: Includes interactive category filter pills, dynamic payment switcher with QR visualizer and one-tap address copying with haptic feedback, and collapsible FAQ drawers.

---

### 1. Overview & Core Concept

- **What It Does**: Showcases Alexsandro Arantes' professional expertise across Web FullStack, Desktop & Mobile development, Blue Team cyber defense, Red Team pentesting, and Paid Traffic management. Enables prospective clients to inspect packages, calculate or submit project quotes, copy verified payment keys, and contact directly through official channels.
- **Target Audience / Persona**: Enterprise clients, startups, security teams, and business owners looking for tailored full-stack engineering, infrastructure audits, ethical hacking, and traffic acquisition.
- **Key Value**: Delivers an elite, authoritative mobile client presence with zero friction for service contracting, transparent pricing, and instant communication.

---

### 2. User Experience & Visual Design

- **Key User Flows**:
  1. **Hero & Expertise Discovery**: User opens app, views online verified availability badge, scrolls through interactive category chips (Web Fullstack, Desktop & Mobile, Blue Team, Red Team, Tráfego Pago), and reads dynamic profile bento card.
  2. **Services & Capabilities Inspection**: 2x2 modular tactical grid highlighting key domains with visual badge icons and service deep-dive dialogs.
  3. **Tiered Pricing & Plan Selection**: Browsing Plano Light (R$ 1.200), Plano Premium (R$ 2.200 - Mais Popular), Plano Empresarial (R$ 4.400), and Tráfego Light (R$ 500), each with feature checklists and direct WhatsApp contracting pre-filled messages.
  4. **Multi-Rail Payment Station**: Toggle between PIX, Bitcoin (BTC), and Ethereum (ETH). User sees QR code mockup, wallet address/Pix key, and taps copy button with feedback toast.
  5. **FAQ & Quote Request**: Interactive expandable accordion for common questions; form to input name, corporate email, and project message with immediate submission and WhatsApp/Email routing.
  6. **Omnipresent Communication**: Floating WhatsApp Action Button (FAB) with ambient green glow for instant chat start.

- **Visual Identity & Theme**:
  - *Color Architecture*:
    - Background Substrate: `#0B0F19` (Void Obsidian)
    - Container Surfaces: `#111827` (Elevated dark container)
    - Glass Surfaces: `rgba(17, 24, 39, 0.72)` with 1px border stroke `rgba(99, 102, 241, 0.20)`
    - Primary: `#6366F1` (Electric Indigo) / `#c0c1ff`
    - Secondary: `#06B6D4` (Cyber Teal) / `#4cd7f6`
    - Tertiary / Success: `#10B981` (Defense Green) / `#4edea3`
    - Red Team Accent: `#EF4444`
    - Blue Team Accent: `#3B82F6`
    - Yellow Accent: `#F59E0B`
    - WhatsApp Green: `#22C55E`
  - *Typography*:
    - Headlines: **Sora** (Bold / Semi-Bold, -0.02em tracking)
    - Body & Labels: **Hanken Grotesk** / Clean System Sans with high contrast
    - Monospace details for tech tags and crypto wallet hashes
  - *Components & Ergonomics*:
    - 8dp / 16dp rounded corners, minimum 48dp touch targets, Material ripple effects.
    - Custom adaptive launcher icon matching the cyber defense & fullstack developer identity.

---

### 3. Key Product Decisions & Trade-Offs

- **Decision 1: Edge-to-Edge Single Activity Architecture with Jetpack Compose**
  - *Chosen Approach*: Unified Compose view hierarchy with `enableEdgeToEdge()`, custom glassmorphic top header, smooth-scrolling `LazyListState` linked with BottomNavigationBar items and category filters.
  - *Why*: Delivers an ultra-fluid 60fps native experience without screen reload flicker, matching the sleek mobile web prototype while unlocking Android native haptics, clipboard managers, and system intents.
- **Decision 2: Direct Client Dispatch via Android System Intents**
  - *Chosen Approach*: WhatsApp intent (`https://wa.me/5513982237027` with URL-encoded messages) with fallback to browser, plus `mailto:alexsarantes2023@gmail.com` for corporate communications.
  - *Why*: Allows immediate, real-world business generation directly from the user's Android phone without requiring an unauthenticated backend server.
- **Decision 3: Local Persistence for Inquiries**
  - *Chosen Approach*: Room Database for storing sent budget requests and inquiries locally, providing an inquiry history viewer and offline drafting capability.

---

### 4. Technical Architecture & Data Strategy

```
┌────────────────────────────────────────────────────────────────────────┐
│                          MainActivity (Edge-to-Edge)                   │
├────────────────────────────────────────────────────────────────────────┤
│  TopAppBar: Avatar, Online Badge, Security Indicator                   │
├────────────────────────────────────────────────────────────────────────┤
│  Category Pills: [Web Fullstack] [Desktop & Mobile] [Blue] [Red]       │
├────────────────────────────────────────────────────────────────────────┤
│  LazyColumn (Smooth-Scrolling Portfolio Content):                      │
│    ├── Hero Bento Card (Title, Bio, Tech Stack Badges, CTAs)          │
│    ├── Especialidades Grid (Desktop & Mobile, Blue Team, Red, Traffic)│
│    ├── Pricing Cards (Plano Light, Plano Premium, Empresarial)         │
│    ├── Gestor de Tráfego Pago Module                                   │
│    ├── Métodos de Pagamento (PIX, Bitcoin, Ethereum + QR + Copy)      │
│    ├── FAQ Accordion (Expandable Q&A Cards)                            │
│    ├── Solicitar Orçamento (Form with Validation & Storage)           │
│    └── Footer (Developer Info, Social Links, Copyright)               │
├────────────────────────────────────────────────────────────────────────┤
│  BottomNavigationBar (Home, Serviços, Planos, Pagamento, Contato)     │
│  FloatingActionButton: WhatsApp Glow CTA                               │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
                                    ▼
       ┌──────────────────────────────────────────────────────────┐
       │                      PortfolioViewModel                  │
       │  - activeCategoryFilter: StateFlow<Category>             │
       │  - activePaymentRail: StateFlow<PaymentRail> (PIX/BTC/ETH)│
       │  - expandedFaqItems: StateFlow<Set<Int>>                 │
       │  - quoteFormState: StateFlow<QuoteFormState>             │
       │  - quoteInquiries: StateFlow<List<QuoteEntity>>          │
       └────────────────────────────┬─────────────────────────────┘
                                    │
                                    ▼
       ┌──────────────────────────────────────────────────────────┐
       │             AppDatabase (Room Local Storage)             │
       │  - quote_inquiries table (name, email, message, date)    │
       └──────────────────────────────────────────────────────────┘
```

- **Data Models**:
  - `ServiceCardModel(id, title, description, icon, tagColor, details)`
  - `PlanModel(id, tier, title, subtitle, price, isPopular, features, waMessage)`
  - `PaymentRail(id, name, networkBadge, address, qrType)`
  - `FaqItem(id, question, answer)`
  - `QuoteEntity(id, name, email, message, timestamp)`

- **Interactive State Transitions**:
  - Category pill click updates selected filter and highlights matching services.
  - Payment tab click switches QR visualizer and target copy hash with 1-tap clipboard trigger.
  - FAQ accordion header click toggles expand/collapse animation with smooth chevron rotation.
  - Form validation: checks non-empty name, valid email format, non-empty description, saves to local Room DB, and opens WhatsApp/Email client with full details.
