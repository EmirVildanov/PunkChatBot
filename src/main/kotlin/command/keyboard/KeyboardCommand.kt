package command.keyboard

import VkCore
import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.objects.users.User
import command.Command

abstract class KeyboardCommand : Command() {
    final override fun executeMessageWithUserInfo(message: Message, userId: Int, userInfo: User) {
        changeState(VkCore)
        log("Changed state to ${VkCore.chatState}")
        val responseText = getResponseTextMessage(message, userId, userInfo)
        VkCore.sendMessage(responseText, userId)
        logSendingMessageToUserWithId(responseText, userId)
    }

    protected open fun getResponseTextMessage(message: Message, userId: Int, userInfo: User): String {
        return EMPTY
    }

    abstract fun changeState(vkCore: VkCore)
}