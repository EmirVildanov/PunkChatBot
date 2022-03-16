package command
import com.vk.api.sdk.objects.messages.Message


abstract class Command() {
    abstract fun execute(message: Message)
}