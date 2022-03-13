import Command.Command
import Command.UserIdIdentifierCommand
import com.vk.api.sdk.objects.messages.Message


object CommandDeterminant {
    fun getCommand(commands: Collection<Command>, message: Message): Command {
        val messageText = message.text
        for (command in commands) {
            if (command.name == messageText) {
                return command
            }
        }
        return UserIdIdentifierCommand()
    }
}