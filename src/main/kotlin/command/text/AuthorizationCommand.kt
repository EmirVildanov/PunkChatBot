package command.text

import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.objects.users.User
import command.Command

class AuthorizationCommand : Command() {
    override fun executeMessageWithUserInfo(message: Message, userId: Int, userInfo: User) {
        TODO("Not yet implemented")
    }
}