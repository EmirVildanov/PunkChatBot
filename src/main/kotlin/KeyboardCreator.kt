import com.vk.api.sdk.objects.messages.*

object KeyboardCreator {
    const val EMPTY = ""
    const val FIND_INTERLOCUTOR = "Найти собеседника"
    const val FILL_FORM = "Изменить анкету"

    fun createStateKeyboard(chatState: ChatState): Keyboard {
        return when (chatState) {
            ChatState.MAIN_MENU -> createMainMenuKeyboard()
            else -> createMainMenuKeyboard()
        }
    }

    fun createMainMenuKeyboard(): Keyboard {
        val config = listOf(
            listOf(
                CustomKeyboardButton(FIND_INTERLOCUTOR, KeyboardButtonColor.DEFAULT),
                CustomKeyboardButton(FILL_FORM, KeyboardButtonColor.DEFAULT)
            )
        )
        return createKeyboard(config)
    }

    private fun createKeyboard(commandsList: List<List<CustomKeyboardButton>>): Keyboard {
        val keyboard = Keyboard()
        val allKeys: MutableList<List<KeyboardButton>> = ArrayList()
        for (line in commandsList) {
            val currentKeyBoardLine: MutableList<KeyboardButton> = ArrayList()
            for (button in line) {
                currentKeyBoardLine.add(
                    KeyboardButton()
                        .setAction(
                            KeyboardButtonAction()
                                .setLabel(button.text)
                                .setType(TemplateActionTypeNames.TEXT)
                        )
                        .setColor(button.color)
                )
            }
            allKeys.add(currentKeyBoardLine)
        }

        keyboard.oneTime = false
        keyboard.buttons = allKeys
        return keyboard
    }

    class CustomKeyboardButton(val text: String, val color: KeyboardButtonColor)
}