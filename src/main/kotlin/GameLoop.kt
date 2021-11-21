import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import repository.model.Directions
import repository.model.Ships

class GameLoop {
    val gameObjects = mutableListOf<GameObject>()
    val battleField by BattleFieldDelegate(gameObjects)

    fun start() {
        battleField.render()

        transaction {
            val directionToId = Directions.selectAll().associate { it[Directions.direction] to it[Directions.id] }

            gameObjects.forEach { ship ->
                when (ship) {
                    is StaticShipObject -> Ships.insert {
                        it[xPos] = ship.x
                        it[yPos] = ship.y
                        it[zPos] = ship.z
                        it[len] = ship.len
                        it[direction] = directionToId[ship.direction.name] ?: error("Not found direction in DB")
                    }
                    else -> Unit// todo: add mines
                }
            }
        }
    }
}
