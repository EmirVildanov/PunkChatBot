package command

import com.vk.api.sdk.objects.messages.Message

class FindInterlocutorCommand : Command() {
    override fun execute(message: Message) {
        val userId = message.fromId
        val userInfo = VkCore.getUserInfoById(userId)
        if (userInfo != null) {
            val responseText = "Executed find interlocutor command"
            VkCore.sendMessage(responseText, userId)
            println("Send message to $userId")
        } else {
            println("Server can't get user info")
        }

    }

    companion object {
        const val COMMAND_NAME = KeyboardCreator.FIND_INTERLOCUTOR
    }
}