package cz.librarian.profile.impl

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import cz.librarian.profile.api.Profile
import cz.librarian.profile.impl.data.meProfile
import cz.librarian.profile.impl.ui.ProfileContent

public fun EntryProviderScope<Any>.profileContent(backstack: SnapshotStateList<Any>) {
    entry<Profile>  { key ->
        ProfileContent(
            userData = meProfile,
            onNavIconPressed = { backstack.removeLastOrNull() },
        )
    }
}