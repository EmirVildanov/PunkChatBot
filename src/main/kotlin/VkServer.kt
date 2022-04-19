import com.vk.api.sdk.exceptions.ClientException
import enums.ChatState
import db.interactor.ChatBotDb
import db.models.User
import db.models.UserForm
import kotlinx.coroutines.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt


object VkServer {
    private var vkCore: VkCore? = null
    private var chatBotDb = ChatBotDb
    private const val RECONNECT_TIME_MILLISECONDS = 10000
    private const val BOT_RESPONSE_WAITING_TIME_MILLISECONDS = 300L

    private val supervisorScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

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
                    val userId = message.fromId
                    customLog("Received message: ${message.text}")
                    supervisorScope.launch {
                        val user = getUser(userId)
                        try {
                            withContext(Dispatchers.Default) {
                                command.CommandExecutor.execute(message, user.chatState)
                            }
                            throw Exception()
                        } catch (e: Exception) {
//                            customLog("Unexpected exception: ${e.message}")
                            customLog("Unexpected exception")
                            VkCore.sendMessage("Unexpected error occurred on the server side", message.fromId, state = user.chatState)
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

    fun registerNewUser(userId: Int) = supervisorScope.launch {
        val userInfo = vkCore?.getUserInfoById(userId)
        if (userInfo != null) {
            val user = User(userId, Clock.System.todayAt(TimeZone.currentSystemDefault()))
            val userForm = UserForm(userId, userInfo.firstName, userInfo.lastName)
            chatBotDb.addNewUser(user, userForm)
        }
    }

    fun getUser(userId: Int): User {
        val user: User?
        runBlocking {
            user = supervisorScope.async {
                chatBotDb.getUser(userId)
            }.await()
        }
        if (user == null) {
            throw java.lang.RuntimeException("There is no user with id $userId in database")
        }
        return user
    }

    fun getUserForm(userId: Int): UserForm {
        val userForm: UserForm?
        runBlocking {
            userForm = supervisorScope.async{
                chatBotDb.getUserForm(userId)
            }.await()
        }
        if (userForm == null) {
            throw java.lang.RuntimeException("There is no user with id $userId in database")
        }
        return userForm
    }

    fun loadUserInfo(userId: Int, userDbInfo: UserForm?) {

    }

    fun changeUserState(userId: Int, chatState: ChatState) = supervisorScope.launch {
        chatBotDb.changeUserState(userId, chatState)
    }
}