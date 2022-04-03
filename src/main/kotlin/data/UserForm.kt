package data

import com.vk.api.sdk.objects.base.Sex
import enums.Faculty
import enums.Interest

// Must mark all non-must-have values as Nullable
@kotlinx.serialization.Serializable
data class UserForm(
    var id: Int,
    var firstName: String,
    var secondName: String?,
    var dormitoryNumber: Int?,
    var faculty: Faculty?,
    var sex: Sex?,
    var interests: MutableList<Interest>,
) {
    override fun toString(): String {
        var body = ""
        body += if (secondName != null) {
            "Имя: $firstName $secondName\n"
        } else {
            "Имя: $firstName"
        }
        dormitoryNumber?.let {
            body += "Общага: $dormitoryNumber\n"
        }
        sex?.let {
            body += "Пол: ${it.name}\n"
        }
        faculty?.let {
            body += "Факультет : ${it.name}\n"
        }
        if (interests.size != 0) {
            body += "Интересы: $interests"
        }
        return body
    }
}