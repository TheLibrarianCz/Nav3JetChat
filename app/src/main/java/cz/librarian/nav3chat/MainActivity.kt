package cz.librarian.nav3chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.librarian.nav3chat.ui.theme.Nav3ChatTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import cz.librarian.nav3chat.theme.JetchatTheme

data object ConversationList
data class ConversationDetail(val id: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetchatTheme {
                val backstack = remember { mutableStateListOf<Any>(ConversationList) }

                NavDisplay(
                    backStack = backstack,
                    onBack = { backstack.removeLastOrNull() },
                    entryProvider = { key ->
                        when (key) {
                            is ConversationList -> {
                                NavEntry(key) {

                                }
                            }

                            is ConversationDetail -> {
                                NavEntry(key) {

                                }
                            }

                            else -> error("unknown key: $key")
                        }
                    }
                )
            }
        }
    }
}