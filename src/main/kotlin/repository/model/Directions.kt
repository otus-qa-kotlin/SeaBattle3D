package repository.model

import org.jetbrains.exposed.dao.id.IntIdTable

object Directions : IntIdTable() {
    val direction = varchar("direction", 30)
}