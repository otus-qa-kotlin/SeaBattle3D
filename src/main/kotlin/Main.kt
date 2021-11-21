import configuration.DatabaseSettings
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import repository.model.Directions
import repository.model.Ships

fun main(args: Array<String>) {
    DatabaseSettings.embedded

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.drop(Ships, Directions)
        SchemaUtils.create(Ships, Directions)
        val directionToId = Direction.values().map { directionValue ->
            directionValue to Directions.insertAndGetId {
                it[direction] = directionValue.name
            }
        }



        Directions.selectAll().forEach { println(it) }
    }
    GameLoop().start()
}