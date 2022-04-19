package command

import KeyboardCreator
import VkCore
import com.vk.api.sdk.objects.messages.Message
import command.keyboard.*
import command.text.AuthorizationCommand
import command.text.TalkingToInterlocutorCommand
import command.text.UnknownCommand
import enums.ChatState


object CommandExecutor {
    fun execute(message: Message, chatState: ChatState) {
        val command = when (val messageText = message.text) {
            KeyboardCreator.FILL_FORM -> handleKeyboardTextCommand(FillFormCommand(ChatState.FILLING_FORM_MAIN_MENU), chatState)
            KeyboardCreator.SHOW_FORM -> handleKeyboardTextCommand(ShowFormCommand(), chatState)
            KeyboardCreator.FIND_INTERLOCUTOR -> handleKeyboardTextCommand(FindInterlocutorCommand(), chatState)

            KeyboardCreator.SET_FACULTY -> handleKeyboardTextCommand(FillFormCommand(ChatState.FILLING_FORM_FACULTY), chatState)
            KeyboardCreator.SET_DORMITORY -> handleKeyboardTextCommand(FillFormCommand(ChatState.FILLING_FORM_DORMITORY), chatState)
            KeyboardCreator.SET_SEX -> handleKeyboardTextCommand(FillFormCommand(ChatState.FILLING_FORM_SEX), chatState)
            KeyboardCreator.SET_INTERESTS -> handleKeyboardTextCommand(FillFormCommand(ChatState.FILLING_FORM_INTERESTS), chatState)

            else -> handleVaryingTextCommand(messageText, chatState)
        }
        command.execute(message)
    }

    private fun handleKeyboardTextCommand(command: KeyboardCommand, chatState: ChatState) : Command {
        return when (chatState) {
            ChatState.AUTHORIZATION -> UnexpectedKeyboardCommand()
            ChatState.MAIN_MENU -> UnexpectedKeyboardCommand()
            else -> UnexpectedKeyboardCommand()
        }
    }

    private fun handleVaryingTextCommand(messageText: String, chatState: ChatState): Command {
        return when (chatState) {
            ChatState.AUTHORIZATION -> AuthorizationCommand()
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