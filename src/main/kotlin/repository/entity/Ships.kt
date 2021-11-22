package repository.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Ships : IntIdTable() {
    val type = varchar("type", 15)
    val x = integer("x")
    val y = integer("y")
    val z = integer("z")
    val len = integer("len")
    val direction = reference("direction_id", Directions)
}

class Ship(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Ship>(Ships)

    var x by Ships.x
    var y by Ships.y
    var z by Ships.z
    var len by Ships.len
    var type by Ships.type
    var direction by DirectionEntity referencedOn Ships.direction
}