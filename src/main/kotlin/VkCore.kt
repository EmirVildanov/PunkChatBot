import com.vk.api.sdk.client.Lang
import com.vk.api.sdk.client.TransportClient
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.exceptions.ApiException
import com.vk.api.sdk.exceptions.ClientException
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.messages.Message
import com.vk.api.sdk.objects.users.Fields
import com.vk.api.sdk.objects.users.User
import enums.ChatState
import java.io.FileInputStream
import java.io.IOException
import java.util.*
import kotlin.random.Random


object VkCore {
    private var vk: VkApiClient
    private var ts = 0
    private var actor: GroupActor? = null
    private var maxMsgId = -1

    private var keyboardCreator: KeyboardCreator = KeyboardCreator
    var chatState: ChatState = ChatState.MAIN_MENU

    init {
        val transportClient: TransportClient = HttpTransportClient.getInstance()
        vk = VkApiClient(transportClient)

        val prop = Properties()
        val groupId: Int
        val accessToken: String
        try {
            prop.load(FileInputStream("vkconfig.properties"))
            groupId = Integer.valueOf(prop.getProperty("groupId"))
            accessToken = prop.getProperty("accessToken")
            actor = GroupActor(groupId, accessToken)
            ts = vk.messages().getLongPollServer(actor).execute().ts
        } catch (e: ConfigCreationException) {
            e.printStackTrace()
            println("Failed to load configuration file")
        }
    }

    fun getMessage(): Message? {
        val eventsQuery = vk.messages()
            .getLongPollHistory(actor)
            .ts(ts)
        if (maxMsgId > 0) {
            eventsQuery.maxMsgId(maxMsgId)
        }
        val messages = eventsQuery
            .execute()
            .messages
            .items
        if (messages.isNotEmpty()) {
            try {
                ts = vk.messages()
                    .getLongPollServer(actor)
                    .execute()
                    .ts
            } catch (e: ClientException) {
                e.printStackTrace()
            }
        }
        if (messages.isNotEmpty() && !messages[0].isOut) {
            /*
            messageId - max gotten ID. We need it to avoid 10 internal server error,
            that is a limitation in API VK. In case of old ts (more than one day),
            and MaxMsgId wasn't passed, method may return Exception 10 (Internal server error).
             */
            val messageId = messages[0].id
            if (messageId > maxMsgId) {
                maxMsgId = messageId
            }
            return messages[0]
        }
        return null
    }

    fun getUserInfoById(userId: Int): User? {
        val user: User
        try {
            user = vk
                .users()
                .get(actor)
                .userIds(userId.toString())
                .fields(Fields.FIRST_NAME_ABL)
                .lang(Lang.EN)
                .execute()[0]
            return user
        } catch (e: ClientException) {
            e.printStackTrace()
        } catch (e: ApiException) {
            e.printStackTrace()
        }
        return null
    }

    fun sendMessage(msg: String, peerId: Int, keyboardEnabled: Boolean = true) {
        try {
            val messagesSendQuery = vk
                .messages()
                .send(actor)
                .randomId(generateRandomId(peerId))
                .peerId(peerId)
                .message(msg)
            if (keyboardEnabled) {
                val keyboard = keyboardCreator.createStateKeyboard(chatState)
                messagesSendQuery.keyboard(keyboard)
            }
            messagesSendQuery.execute()
        } catch (e: ApiException) {
            e.printStackTrace()
        } catch (e: ClientException) {
            e.printStackTrace()
        }
    }

    private fun generateRandomId(userId: Int): Int {
        val value = Random.nextInt()
        return value
    }
}

class ConfigCreationException(message: String) : IOException(message)