
import com.vk.api.sdk.objects.messages.Message


class Messenger(private val message: Message) : Runnable {
    override fun run() {
        Commander.execute(message)
    }
}