
import com.vk.api.sdk.objects.messages.Message
import command.FillFormCommand
import command.FindInterlocutorCommand
import command.UserIdIdentifierCommand


class CommandExecutor(private val message: Message) : Runnable {
    override fun run() {
        val command = when (message.text) {
            FillFormCommand.COMMAND_NAME -> FillFormCommand()
            FindInterlocutorCommand.COMMAND_NAME -> FindInterlocutorCommand()
            UserIdIdentifierCommand.COMMAND_NAME -> UserIdIdentifierCommand()
            else -> UserIdIdentifierCommand()
        }
        command.execute(message)
    }
}