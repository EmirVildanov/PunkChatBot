import Command.Command
import Command.KeyboardCommand
import Command.UserIdIdentifierCommand

object CommandManager {
    val commands = HashSet<Command>()

    init {
        commands.add(UserIdIdentifierCommand())
        commands.add(KeyboardCommand())
    }
}