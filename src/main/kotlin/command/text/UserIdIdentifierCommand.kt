package command.text
import VkCore
import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.objects.users.User
import command.Command


class UserIdIdentifierCommand : Command() {
    override fun executeMessageWithUserInfo(message: Message, userId: Int, userInfo: User) {
        val responseText = "Your id is ${userId}. You are ${userInfo.firstName}"
        VkCore.sendMessage(responseText, userId)
        logSendingMessageToUserWithId(responseText, userId)
    }

    companion object {
        const val COMMAND_NAME = "id"
    }
}