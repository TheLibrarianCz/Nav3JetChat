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
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import cz.librarian.nav3chat.theme.JetchatTheme
import cz.librarian.nav3chat.conversation.ConversationsContent
import cz.librarian.nav3chat.data.exampleListUiState
import cz.librarian.nav3chat.data.exampleUiState
import cz.librarian.nav3chat.data.meProfile
import cz.librarian.nav3chat.profile.ProfileContent
import cz.librarian.nav3chat.profile.ProfileScreen
import cz.librarian.nav3chat.theme.JetchatTheme

data object ChannelList
data class ConversationList(val id: String)
data class Profile(val id: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetchatTheme {
                val backstack = remember { mutableStateListOf<Any>(ChannelList) }

                NavDisplay(
                    backStack = backstack,
                    onBack = { backstack.removeLastOrNull() },
                    entryProvider = { key ->
                        when (key) {
                            is ChannelList -> {
                                NavEntry(key) {
                                    ConversationsContent(
                                        uiState = exampleListUiState,
                                        navigateToChannel = { backstack.add(ConversationList(it)) }
                                    )
                                }
                            }

                            is ConversationList -> {
                                NavEntry(key) {
                                    ConversationContent(
                                        uiState = exampleUiState,
                                        navigateToProfile = { backstack.add(meProfile) },
                                        onNavIconPressed = { backstack.removeLastOrNull() },
                                    )
                                }
                            }

                            is Profile -> {
                                NavEntry(key) {
                                    ProfileContent(
                                        userData = meProfile,
                                        onNavIconPressed = { backstack.removeLastOrNull() },
                                    )
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