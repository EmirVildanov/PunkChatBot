package command.text

import VkCore
import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.objects.users.User
import command.Command

/*
User may send random reasonable TextCommand in following situations:
- He is filling form and sending information about himself
- He is chatting with his interlocutor
Otherwise TextCommand is excepted as UnknownTextCommand
 */
abstract class TextCommand : Command() {
    override fun executeMessageWithUserInfo(message: Message, userId: Int, userInfo: User) {
        changeState(VkCore)
        handleMessageText(message.text)
        val responseText = getResponseTextMessage()
        VkCore.sendMessage(responseText, userId)
        logSendingMessageToUserWithId(responseText, userId)
    }

    abstract fun handleMessageText(messageText: String)

    abstract fun getResponseTextMessage() : String

    abstract fun changeState(vkCore: VkCore)
}