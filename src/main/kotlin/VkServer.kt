import com.vk.api.sdk.exceptions.ClientException
import java.util.concurrent.Executors


object VkServer {
    private var vkCore: VkCore? = null

    private const val RECONNECT_TIME_MILLISECONDS = 10000
    private const val BOT_RESPONSE_WAITING_TIME_MILLISECONDS = 300L

    init {
        try {
            vkCore = VkCore
        } catch (e: ConfigCreationException) {
            e.printStackTrace()
        } catch (e: ClientException) {
            e.printStackTrace()
        }
    }

    fun start() {
        println("Running server...")
        while (true) {
            Thread.sleep(BOT_RESPONSE_WAITING_TIME_MILLISECONDS)
            try {
                val message = vkCore?.getMessage()
                if (message != null) {
                    println("Received message: ${message.text}")
                    val exec = Executors.newCachedThreadPool()
                    exec.execute(CommandExecutor(message))
                }
            } catch (e: ClientException) {
                println("Unexpected error while listening the can't appeared")
                println("Connecting again in " + RECONNECT_TIME_MILLISECONDS / 1000 + " seconds")
                Thread.sleep(RECONNECT_TIME_MILLISECONDS.toLong())
            }
        }
    }
}