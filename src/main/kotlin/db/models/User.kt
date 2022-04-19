package db.models

import enums.ChatState
import kotlinx.datetime.LocalDate

@kotlinx.serialization.Serializable
data class User(
    val id: Int,
    val createdAt: LocalDate,
    val chatState: ChatState = ChatState.MAIN_MENU,
    val shareLinks: MutableList<ShareLink> = mutableListOf(),
    val blockedUser: MutableList<Int> = mutableListOf(),
) {
}