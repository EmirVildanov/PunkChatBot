package command.keyboard

import VkServer
import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.objects.users.User
import enums.ChatState

class FindInterlocutorCommand : KeyboardCommand() {
    override fun getResponseTextMessage(message: Message, userId: Int, userInfo: User): String {
        return STARTED_LOOKING_FOR_YOUR_INTERLOCUTOR
    }

    override fun changeState(userId: Int) {
        VkServer.changeUserState(userId, ChatState.FINDING_INTERLOCUTOR)
    }

    companion object {
        const val STARTED_LOOKING_FOR_YOUR_INTERLOCUTOR = "Я начал подбор собеседника для тебя!"
        const val COMMAND_NAME = KeyboardCreator.FIND_INTERLOCUTOR
    }
}