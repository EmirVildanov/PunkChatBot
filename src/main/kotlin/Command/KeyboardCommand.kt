package Command

import VkCore
import com.vk.api.sdk.objects.messages.*


class KeyboardCommand : Command("keyboard") {
    override fun execute(message: Message) {
        val keyboard = createKeyboard()
        VkCore.sendMessage("Sending you a keyboard", message.fromId, keyboard)
    }

    private fun createKeyboard(): Keyboard {
        val keyboard = Keyboard()
        val allKeys: MutableList<List<KeyboardButton>> = ArrayList()
        val line1: MutableList<KeyboardButton> = ArrayList()
        val line2: MutableList<KeyboardButton> = ArrayList()
        line1.add(
            KeyboardButton()
                .setAction(KeyboardButtonAction()
                    .setLabel("Изменить анкету")
                    .setType(TemplateActionTypeNames.TEXT)
                )
                .setColor(KeyboardButtonColor.DEFAULT)
        )
        line1.add(
            KeyboardButton()
                .setAction(
                    KeyboardButtonAction()
                        .setLabel("Пожаловаться на собеседника")
                        .setType(TemplateActionTypeNames.TEXT)
                )
                .setColor(KeyboardButtonColor.NEGATIVE)
        )
        line2.add(
            KeyboardButton()
                .setAction(
                    KeyboardButtonAction()
                        .setLabel("Найти собеседника")
                        .setType(TemplateActionTypeNames.TEXT)
                )
                .setColor(KeyboardButtonColor.POSITIVE)
        )
        line2.add(
            KeyboardButton()
                .setAction(
                    KeyboardButtonAction()
                        .setLabel("Показать оставшееся время")
                        .setType(TemplateActionTypeNames.TEXT)
                )
                .setColor(KeyboardButtonColor.PRIMARY)
        )
        allKeys.add(line1)
        allKeys.add(line2)

        keyboard.oneTime = false
        keyboard.buttons = allKeys
        return keyboard
    }
}