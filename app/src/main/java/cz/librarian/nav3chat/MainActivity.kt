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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.entryProvider
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
data class ConversationList(val channel: String)
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
                    entryProvider = entryProvider {
                        conversationsContent(backstack)
                        conversationContent(backstack)
                        profileContent(backstack)
                    }
                )
            }
        }
    }
}

private fun EntryProviderScope<Any>.conversationsContent(backstack: SnapshotStateList<Any>) {
    entry<ChannelList> {
        ConversationsContent(
            uiState = exampleListUiState,
            navigateToChannel = { channel -> backstack.add(ConversationList(channel)) }
        )
    }
}

private fun EntryProviderScope<Any>.conversationContent(backstack: SnapshotStateList<Any>) {
    entry<ConversationList> { key ->
        ConversationContent(
            uiState = exampleUiState, // Use key.id in the real case
            navigateToProfile = { backstack.add(key) },
            onNavIconPressed = { backstack.removeLastOrNull() },
        )
    }
}

private fun EntryProviderScope<Any>.profileContent(backstack: SnapshotStateList<Any>) {
    entry<Profile>  { key ->
        ProfileContent(
            userData = meProfile,
            onNavIconPressed = { backstack.removeLastOrNull() },
        )
    }
}