package cz.librarian.conversation.api

interface ConversationRepository {
    suspend fun login(
        username: String,
        password: String
    ): Result<Unit>
}