import com.vk.api.sdk.exceptions.ClientException
import com.vk.api.sdk.objects.base.Sex
import data.UserForm
import enums.Faculty
import enums.Interest
import kotlinx.coroutines.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


object VkServer {
    private var vkCore: VkCore? = null
    private const val RECONNECT_TIME_MILLISECONDS = 10000
    private const val BOT_RESPONSE_WAITING_TIME_MILLISECONDS = 300L

    val client = KMongo.createClient().coroutine
    val database = client.getDatabase("chatbot")
    val collection = database.getCollection<UserForm>()

    // kotlinx.serialization.MissingFieldException

    private val usersInfo =
        listOf(
            UserForm(
                1,
                "Emir",
                "Vildanov",
                20,
                Faculty.MATH_MECH,
                Sex.MALE,
                mutableListOf(Interest.PHOTOGRAPHY),
            )
        )

    init {
        try {
            vkCore = VkCore
        } catch (e: ConfigCreationException) {
            e.printStackTrace()
        } catch (e: ClientException) {
            e.printStackTrace()
        }
    }

    fun start() = runBlocking {
        customLog("Running server...")
        while (true) {
            delay(BOT_RESPONSE_WAITING_TIME_MILLISECONDS)
            try {
                val message = vkCore?.getMessage()
                if (message != null) {
                    customLog("Received message: ${message.text}")
                    supervisorScope {
                        try {
//                            withContext(Dispatchers.Default) {
//                                CommandExecutor.execute(message)
//                            }
                            throw Exception()
                        } catch (e: Exception) {
//                            customLog("Unexpected exception: ${e.message}")
                            customLog("Unexpected exception")
                            VkCore.sendMessage("Unexpected error occurred on the server side", message.fromId)
                        }
                    }
                }
            } catch (e: ClientException) {
                customLog("Unexpected error while listening the can't appeared")
                customLog("Connecting again in " + RECONNECT_TIME_MILLISECONDS / 1000 + " seconds")
                delay(RECONNECT_TIME_MILLISECONDS.toLong())
            }
        }
    }

    fun customLog(message: String) {
        println(message)
    }

    fun getUserInfo(userId: Int): UserForm {
        return usersInfo[0]
    }

    fun loadUserInfo(userId: Int, userDbInfo: UserForm) {

    }
}