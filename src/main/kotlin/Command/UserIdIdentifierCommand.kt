package Command
import VkCore
import com.vk.api.sdk.objects.messages.Message


class UserIdIdentifierCommand : Command("My id") {
    override fun execute(message: Message) {
        val userId = message.fromId
        val userInfo = VkCore.getUserInfoById(userId)
        if (userInfo != null) {
            val responseText = "Your id is ${userId}. You are ${userInfo.firstName}"
            VkCore.sendMessage(responseText, userId)
            println("Send message to $userId")
        } else {
            println("Server can't get user info")
        }
    }
}