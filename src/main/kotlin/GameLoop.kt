import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import repository.entity.DirectionEntity
import repository.entity.Directions
import repository.entity.Ship
import repository.entity.Ships

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
                        it[x] = ship.x
                        it[y] = ship.y
                        it[z] = ship.z
                        it[len] = ship.len
                        it[direction] = directionToId[ship.direction.name] ?: error("Not found direction in DB")
                    }
                    else -> Unit// todo: add mines
                }
            }
        }
    }

    private fun transactionWithORM(){
            val directionNameToEntity = DirectionEntity.all().associateBy { it.direction }

            gameObjects.forEach { ship ->
                when(ship) {
                    is StaticShipObject -> Ship.new {
                        x = ship.x
                        y = ship.y
                        z = ship.z
                        len = ship.len
                        direction = directionNameToEntity[ship.direction.name] ?: error("Not found direction in DB")
                    }
                    else -> Unit// todo: add mines
                }
            }
    }
}
