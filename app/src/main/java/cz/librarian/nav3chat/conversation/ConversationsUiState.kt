package cz.librarian.nav3chat.conversation

import androidx.compose.runtime.Immutable

class ConversationsUiState(val channels: List<Channel>)

@Immutable
data class Channel(
    val name: String,
    val members: Int,
    val lastMessage: String,
)