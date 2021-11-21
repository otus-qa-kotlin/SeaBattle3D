package repository.model

import org.jetbrains.exposed.dao.id.IntIdTable

object Ships: IntIdTable() {
    val xPos = integer("x")
    val yPos = integer("y")
    val zPos = integer("z")
    val len = integer("len")
    val direction = reference("direction_id", Directions)
}