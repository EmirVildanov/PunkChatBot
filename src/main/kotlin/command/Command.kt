package command
import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.objects.users.User


abstract class Command {
    fun execute(message: Message) {
        val userId = message.fromId
        val userInfo = VkCore.getUserInfoById(userId)
        if (userInfo != null) {
            executeMessageWithUserInfo(message, userId, userInfo)
        } else {
            println(FAILED_TO_GET_USER_INFO)
        }
    }

    protected fun logSendingMessageToUserWithId(messageText: String, userId: Int) {
        println("Sending message [$messageText] to user with [$userId] id")
    }

    protected fun log(message: String) {
        println(message)
    }

    abstract fun executeMessageWithUserInfo(message: Message, userId: Int, userInfo: User)

    companion object {
        const val EMPTY = ""
        const val FAILED_TO_GET_USER_INFO = "Server can't get user info"
    }
}