package data

import com.vk.api.sdk.objects.base.Sex
import enums.Faculty
import enums.Interest

data class UserForm(
    var firstName: String,
    var secondName: String,
    var dormitoryNumber: Int,
    var faculty: Faculty,
    var sex: Sex,
    var interests: MutableList<Interest>
) {
    override fun toString(): String {
        var body = "Имя: $firstName $secondName\n"
        body += "Общага: $dormitoryNumber\n"
        body += "Пол: ${sex.name}\n"
        body += "Факультет : ${faculty.name}\n"
        body += "Интересы: $interests"
        return body
    }
}