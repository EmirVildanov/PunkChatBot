package db.models

@kotlinx.serialization.Serializable
data class ShareLink(
    val fromId: Int,
    val toId: Int,
    val privateCode: String
) {
}