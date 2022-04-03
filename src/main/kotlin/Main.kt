import com.vk.api.sdk.objects.base.Sex
import data.UserForm
import enums.Faculty
import enums.Interest
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

fun main() {
    val vkServer = VkServer
    vkServer.start()

//    val client = KMongo.createClient().coroutine
//    val database = client.getDatabase("chatbot")
//    val collection = database.getCollection<UserForm>()

//    runBlocking {
////        collection.insertOne(
////            UserForm(
////                1,
////                "Emir",
////                "Vildanov",
////                20,
////                Faculty.MATH_MECH,
////                Sex.MALE,
////                mutableListOf(Interest.BOARD_GAME),
////                Sex.UNKNOWN
////            )
////        )
//        val test = collection.findOne(UserForm::id eq 1)
//        println(test)
//    }
}