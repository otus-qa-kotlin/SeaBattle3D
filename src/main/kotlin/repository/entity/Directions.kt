package repository.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Directions : IntIdTable() {
    val direction = varchar("direction", 30)
}

class DirectionEntity(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<DirectionEntity>(Directions)

    var direction by Directions.direction
}
