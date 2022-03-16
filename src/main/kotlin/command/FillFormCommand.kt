package command

import KeyboardCreator
import com.vk.api.sdk.objects.messages.Message

class FillFormCommand : Command() {
    override fun execute(message: Message) {
        val userId = message.fromId
        val userInfo = VkCore.getUserInfoById(userId)
        if (userInfo != null) {
            val responseText = "Executed fill form command"
            VkCore.sendMessage(responseText, userId)
            println("Send message to $userId")
        } else {
            println("Server can't get user info")
        }
    }

    companion object {
        const val COMMAND_NAME = KeyboardCreator.FILL_FORM
    }
}