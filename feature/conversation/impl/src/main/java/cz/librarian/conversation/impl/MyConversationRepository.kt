package cz.librarian.conversation.impl

import cz.librarian.conversation.api.ConversationRepository

class MyConversationRepository: ConversationRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result<Unit> {
        return Result.success(Unit)
    }
}