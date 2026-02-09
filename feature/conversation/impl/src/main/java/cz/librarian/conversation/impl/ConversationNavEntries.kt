package cz.librarian.conversation.impl

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import cz.librarian.conversation.api.ChannelList
import cz.librarian.conversation.api.ConversationList
import cz.librarian.conversation.impl.data.exampleListUiState
import cz.librarian.conversation.impl.data.exampleUiState
import cz.librarian.conversation.impl.ui.ConversationContent
import cz.librarian.conversation.impl.ui.ConversationsContent
import cz.librarian.profile.api.Profile


public fun EntryProviderScope<Any>.conversationsContent(backstack: SnapshotStateList<Any>) {
    entry<ChannelList> {
        ConversationsContent(
            uiState = exampleListUiState,
            navigateToChannel = { channel -> backstack.add(ConversationList(channel)) }
        )
    }
}

public fun EntryProviderScope<Any>.conversationContent(backstack: SnapshotStateList<Any>) {
    entry<ConversationList> { key ->
        ConversationContent(
            uiState = exampleUiState, // Use key.id in the real case
            navigateToProfile = { profileId -> backstack.add(Profile(profileId)) },
            onNavIconPressed = { backstack.removeLastOrNull() },
        )
    }
}