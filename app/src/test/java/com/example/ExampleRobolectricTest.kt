package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.PaymentType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Alex Arantes", appName)
  }

  @Test
  fun `payment types have valid addresses`() {
    PaymentType.entries.forEach { payment ->
      assertNotNull(payment.addressValue)
      assertNotNull(payment.displayName)
    }
  }
}
