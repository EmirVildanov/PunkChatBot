package command.text

import VkCore

class UnknownCommand : TextCommand() {
    override fun handleMessageText(messageText: String) {}

    override fun getResponseTextMessage(): String {
        return UNKNOWN_COMMAND_RESPONSE
    }

    override fun changeState(vkCore: VkCore) {}

    companion object {
        const val UNKNOWN_COMMAND_RESPONSE = "Извини, я не понимаю такую команду"
        const val COMMAND_NAME = "UNKNOWN"
    }
}