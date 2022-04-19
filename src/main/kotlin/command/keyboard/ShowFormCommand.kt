package command.keyboard

import VkServer
import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.objects.users.User

class ShowFormCommand : KeyboardCommand() {

    override fun getResponseTextMessage(message: Message, userId: Int, userInfo: User): String {
        val form = VkServer.getUserForm(userId)
        var responseText = ""
        responseText += HERE_IS_YOUR_FORM
        responseText += form.toString()
        return responseText
    }

    override fun changeState(userId: Int) {}

    companion object {
        const val HERE_IS_YOUR_FORM = "Вот твоя анкета: \n"
    }
}