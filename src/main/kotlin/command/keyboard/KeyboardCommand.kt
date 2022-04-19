package command.keyboard

import VkCore
import VkServer.getUser
import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.objects.users.User
import command.Command

abstract class KeyboardCommand : Command() {
    final override fun executeMessageWithUserInfo(message: Message, userId: Int, userInfo: User) {
        changeState(userId)
        val user = getUser(userId)
        val responseText = getResponseTextMessage(message, userId, userInfo)
        VkCore.sendMessage(responseText, userId, user.chatState)
        logSendingMessageToUserWithId(responseText, userId)
    }

    protected open fun getResponseTextMessage(message: Message, userId: Int, userInfo: User): String {
        return EMPTY
    }

    abstract fun changeState(userId: Int)
}