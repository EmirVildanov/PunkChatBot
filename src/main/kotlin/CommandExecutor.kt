import com.vk.api.sdk.objects.messages.Message
import command.*
import command.keyboard.FillFormCommand
import command.keyboard.FindInterlocutorCommand
import command.keyboard.ShowFormCommand
import command.text.TalkingToInterlocutorCommand
import command.text.UnknownCommand
import command.text.UserIdIdentifierCommand
import enums.ChatState


object CommandExecutor {
    fun execute(message: Message) {
        val command = when (val messageText = message.text) {
            KeyboardCreator.FILL_FORM -> FillFormCommand(ChatState.FILLING_FORM_MAIN_MENU)
            KeyboardCreator.SHOW_FORM -> ShowFormCommand()
            FindInterlocutorCommand.COMMAND_NAME -> FindInterlocutorCommand()
            UserIdIdentifierCommand.COMMAND_NAME -> UserIdIdentifierCommand()
            else -> handleUnknownTextCommand(messageText)
        }
        command.execute(message)
    }

    private fun handleUnknownTextCommand(messageText: String): Command {
        return when (VkCore.chatState) {
            ChatState.TALKING -> TalkingToInterlocutorCommand()
            ChatState.FILLING_FORM_MAIN_MENU -> when (messageText) {
                KeyboardCreator.SET_DORMITORY -> FillFormCommand(ChatState.FILLING_FORM_DORMITORY)
                KeyboardCreator.SET_FACULTY -> FillFormCommand(ChatState.FILLING_FORM_FACULTY)
                KeyboardCreator.SET_INTERESTS -> FillFormCommand(ChatState.FILLING_FORM_INTERESTS)
                KeyboardCreator.SET_SEX -> FillFormCommand(ChatState.FILLING_FORM_SEX)
                else -> UnknownCommand()
            }
            ChatState.FILLING_FORM_DORMITORY, ChatState.FILLING_FORM_FACULTY, ChatState.FILLING_FORM_INTERESTS -> FillFormCommand(
                ChatState.FILLING_FORM_MAIN_MENU
            )
            else -> UnknownCommand()
        }
    }
}