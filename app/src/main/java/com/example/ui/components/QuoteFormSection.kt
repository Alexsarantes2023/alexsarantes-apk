package com.example.ui.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuoteEntity
import com.example.ui.theme.CyberBorderGlass
import com.example.ui.theme.CyberBorderSubtle
import com.example.ui.theme.CyberSurfaceContainer
import com.example.ui.theme.CyberSurfaceContainerHigh
import com.example.ui.theme.CyberSurfaceElevated
import com.example.ui.theme.CyberSurfaceGlass
import com.example.ui.theme.CyberTeal
import com.example.ui.theme.CyberTealLight
import com.example.ui.theme.DefenseGreen
import com.example.ui.theme.ElectricIndigo
import com.example.ui.theme.ElectricIndigoLight
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WhatsAppGreen
import com.example.viewmodel.QuoteFormState

@Composable
fun QuoteFormSection(
    formState: QuoteFormState,
    savedQuotes: List<QuoteEntity>,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onMessageChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onSubmitAndWhatsApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CyberSurfaceGlass)
            .border(1.dp, CyberBorderGlass, RoundedCornerShape(16.dp))
            .padding(18.dp)
            .testTag("quote_request_form")
    ) {
        Column {
            Text(
                text = "Solicitar orçamento",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Envie suas necessidades técnicas e receba estimativa rápida.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Name Field
            Text(
                text = "Nome completo",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = formState.name,
                onValueChange = onNameChange,
                placeholder = {
                    Text("Digite seu nome", color = TextMuted, fontSize = 13.sp)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_quote_name"),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                colors = customTextFieldColors()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email Field
            Text(
                text = "E-mail corporativo",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = formState.email,
                onValueChange = onEmailChange,
                placeholder = {
                    Text("Digite seu email", color = TextMuted, fontSize = 13.sp)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_quote_email"),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                colors = customTextFieldColors()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Message Field
            Text(
                text = "Mensagem ou Descrição do Projeto",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = formState.message,
                onValueChange = onMessageChange,
                placeholder = {
                    Text("Digite sua mensagem com os requisitos e prazos", color = TextMuted, fontSize = 13.sp)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_quote_message"),
                minLines = 3,
                maxLines = 5,
                shape = RoundedCornerShape(8.dp),
                colors = customTextFieldColors()
            )

            // Error or success messages
            formState.errorMessage?.let { error ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp)
                )
            }

            formState.successMessage?.let { success ->
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = DefenseGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = success,
                        color = DefenseGreen,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Button(
                onClick = onSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .testTag("submit_quote_button"),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ElectricIndigo,
                    contentColor = Color.White
                ),
                enabled = !formState.isSubmitting
            ) {
                if (formState.isSubmitting) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Enviar Orçamento",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onSubmitAndWhatsApp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .testTag("submit_and_whatsapp_button"),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = CyberSurfaceContainerHigh,
                    contentColor = WhatsAppGreen
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(WhatsAppGreen.copy(alpha = 0.4f))
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Chat,
                        contentDescription = null,
                        tint = WhatsAppGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Enviar e Abrir no WhatsApp",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    )
                }
            }

            // Saved inquiries counter / offline indicator
            if (savedQuotes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${savedQuotes.size} orçamento(s) registrado(s) no dispositivo",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            fontSize = 10.5.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun customTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = CyberSurfaceElevated,
    unfocusedContainerColor = CyberSurfaceElevated,
    focusedTextColor = TextPrimary,
    unfocusedTextColor = TextPrimary,
    focusedBorderColor = ElectricIndigoLight,
    unfocusedBorderColor = CyberBorderSubtle,
    cursorColor = CyberTeal
)
