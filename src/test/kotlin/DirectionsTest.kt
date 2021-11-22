import configuration.DatabaseSettings
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import repository.entity.Directions
import kotlin.test.assertContains
import kotlin.test.assertEquals

internal class DirectionsTest {
    val database = DatabaseSettings.embedded

    @Test
    fun allDirectionsFilled() {
        print("hello")
        transaction {
            addLogger(StdOutSqlLogger)
            val selectAll = Directions.selectAll()
            val actual = selectAll.count()

            Directions.selectAll().onEach {
                println(it[Directions.direction])
            }
            val directionNames = Direction.values().map { it.name }
            assertEquals(actual.toInt(), Direction.values().size)

            selectAll.toSet().onEach {
                assertContains(directionNames, it[Directions.direction])
            }


//            Ship
//                .all()
//                .orderBy()
//                .groupBy { it.direction }
//

//            selectAll.groupBy(
//                Directions.direction
//            ).distinctBy {
//                it[Directions.direction]
//            }
//
//
//            selectAll.andWhere { Directions.id greater 3 }
//                .map {
//                    it[Directions.direction]
//                }.forEach { println(it) }

        }
    }
}