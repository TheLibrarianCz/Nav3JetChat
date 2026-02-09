package cz.librarian.nav3chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import cz.librarian.conversation.api.ChannelList
import cz.librarian.conversation.impl.conversationContent
import cz.librarian.conversation.impl.conversationsContent
import cz.librarian.profile.impl.profileContent
import cz.librarian.ui.theme.JetchatTheme


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

