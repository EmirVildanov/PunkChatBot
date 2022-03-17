import com.vk.api.sdk.objects.messages.*
import enums.ChatState

/*
    Max size of Keyboard is 5x6
 */
object KeyboardCreator {
    const val FIND_INTERLOCUTOR = "Найти собеседника"
    const val FILL_FORM = "Изменить анкету"
    const val SHOW_FORM = "Показать анкету"

    const val SET_DORMITORY = "Общага"
    const val SET_SEX = "Пол"
    const val SET_INTERESTS = "Интересы"
    const val SET_FACULTY = "Факультет"
    const val CANCEL = "Отмена"

    fun createStateKeyboard(chatState: ChatState): Keyboard {
        return when (chatState) {
            ChatState.MAIN_MENU -> createMainMenuKeyboard()

            ChatState.FILLING_FORM_MAIN_MENU -> createFillingFormMainMenuKeyboard()
            ChatState.FILLING_FORM_DORMITORY -> createFillingFormBackKeyboard()
            ChatState.FILLING_FORM_INTERESTS -> createFillingFormBackKeyboard()
            ChatState.FILLING_FORM_FACULTY -> createFillingFormBackKeyboard()

            else -> createMainMenuKeyboard()
        }
    }

    private fun createFillingFormBackKeyboard(): Keyboard {
        val config = listOf(
            listOf(
                CustomKeyboardButton(CANCEL, KeyboardButtonColor.NEGATIVE),
            ),
        )
        return createKeyboard(config)
    }

    private fun createFillingFormMainMenuKeyboard(): Keyboard {
        val config = listOf(
            listOf(
                CustomKeyboardButton(SET_DORMITORY, KeyboardButtonColor.DEFAULT),
                CustomKeyboardButton(SET_SEX, KeyboardButtonColor.DEFAULT)
            ),
            listOf(
                CustomKeyboardButton(SET_INTERESTS, KeyboardButtonColor.DEFAULT),
                CustomKeyboardButton(SET_FACULTY, KeyboardButtonColor.DEFAULT)
            ),
            listOf(CustomKeyboardButton(CANCEL, KeyboardButtonColor.NEGATIVE))
        )
        return createKeyboard(config)
    }

    private fun createMainMenuKeyboard(): Keyboard {
        val config = listOf(
            listOf(
                CustomKeyboardButton(FIND_INTERLOCUTOR, KeyboardButtonColor.DEFAULT),
                CustomKeyboardButton(FILL_FORM, KeyboardButtonColor.DEFAULT),
                CustomKeyboardButton(SHOW_FORM, KeyboardButtonColor.DEFAULT),
            )
        )
        return createKeyboard(config)
    }

    private fun createKeyboard(config: List<List<CustomKeyboardButton>>): Keyboard {
        val keyboard = Keyboard()
        val allKeys: MutableList<List<KeyboardButton>> = ArrayList()
        for (line in config) {
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