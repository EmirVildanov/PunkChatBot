package command.text

import VkCore

class TalkingToInterlocutorCommand : TextCommand() {
    override fun handleMessageText(messageText: String) {
        TODO("Send message to interlocutor")
    }

    override fun getResponseTextMessage(): String {
        TODO("Return interlocutor response")
    }

    override fun changeState(vkCore: VkCore) {}

    companion object {
        const val COMMAND_NAME = "TALKING_TO_INTERLOCUTOR"
    }
}