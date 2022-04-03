package command.keyboard

import VkCore
import VkServer
import com.vk.api.sdk.objects.base.Sex
import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.objects.users.User
import enums.ChatState
import enums.Faculty
import enums.Interest

class FillFormCommand(private val toState: ChatState) : KeyboardCommand() {
    override fun getResponseTextMessage(message: Message, userId: Int, userInfo: User): String {
        if (toState != ChatState.FILLING_FORM_MAIN_MENU) {
            val userDbInfo = VkServer.getUserInfo(userId)
            when (toState) {
                ChatState.FILLING_FORM_FACULTY -> userDbInfo.faculty = Faculty.CHEM_PHAC
                ChatState.FILLING_FORM_SEX -> userDbInfo.sex = Sex.FEMALE
                ChatState.FILLING_FORM_DORMITORY -> userDbInfo.dormitoryNumber = 21
                ChatState.FILLING_FORM_INTERESTS -> userDbInfo.interests?.add(Interest.BOARD_GAME)
            }
            VkServer.loadUserInfo(userId, userDbInfo)
        }

        return when (toState) {
            ChatState.FILLING_FORM_FACULTY, ChatState.FILLING_FORM_SEX, ChatState.FILLING_FORM_DORMITORY, ChatState.FILLING_FORM_INTERESTS -> "Updated info"
            else -> super.getResponseTextMessage(message, userId, userInfo)
        }
    }

    override fun changeState(vkCore: VkCore) {
        vkCore.chatState = toState
    }
}