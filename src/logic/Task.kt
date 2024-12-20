package logic

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Task(
    val title: String,
    val description: String?,
    val id: String,
    val date: LocalDate = LocalDate.now(),
    var isComplete: Boolean = false
) {
    override fun toString(): String {
        return """
           {"id": "$id", "title": "$title", "description": "$description", "date": "${
            date.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            )
        }", "isComplete": $isComplete}
        """.trimIndent()
    }

}