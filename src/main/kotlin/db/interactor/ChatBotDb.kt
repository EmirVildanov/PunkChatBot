package db.interactor

import enums.ChatState
import db.models.User
import db.models.UserForm
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.setValue

object ChatBotDb {
    private const val databaseName = "chatbot"

    private val client = KMongo.createClient().coroutine
    private val database = client.getDatabase(databaseName)
    private val userFormCollection = database.getCollection<UserForm>()
    private val userCollection = database.getCollection<User>()

    fun updateUserForm(userId: Int) {

    }

    suspend fun getUser(userId: Int): User? {
        return userCollection.findOne(User::id eq userId)
    }

    suspend fun getUserForm(userId: Int): UserForm? {
        return userFormCollection.findOne(User::id eq userId)
    }

    suspend fun addNewUser(user: User, userForm: UserForm) {
        userCollection.insertOne(user)
        userFormCollection.insertOne(userForm)
    }

    suspend fun changeUserState(userId: Int, chatState: ChatState) {
        userFormCollection.updateOne(User::id eq userId, setValue(User::chatState, chatState))
    }
}