
import CommandDeterminant.getCommand
import com.vk.api.sdk.objects.messages.Message


object Commander {
    fun execute(message: Message) {
        getCommand(CommandManager.commands, message).execute(message)
    }
}