package awesome.shizzle.bomtest

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToString
import com.jeppeman.mockposable.mockk.answersComposable
import com.jeppeman.mockposable.mockk.everyComposable
import com.jeppeman.mockposable.mockk.verifyComposable
import io.mockk.mockkStatic
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test() = mockkStatic("awesome.shizzle.bomtest.MainActivityKt") {
        everyComposable {
            Greeting(name = any())
        } answersComposable {
            Text("ItsNotMe")
        }
        composeTestRule.setContent {
            Greeting(name = "Itsame")
        }
        verifyComposable {
            Greeting(name = "Itsame")
        }
        println(
            composeTestRule.onRoot()
                .printToString()
        )
        composeTestRule.onNodeWithText("ItsNotMe")
            .assertIsDisplayed()
    }
}
