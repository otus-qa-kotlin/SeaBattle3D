import configuration.DatabaseSettings
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import repository.entity.Directions
import repository.entity.Ships
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class MainKtTest {

    val database = DatabaseSettings.embedded
    @Test
    fun loadFromDatabase() {
        transaction {
            Ships.join(Directions, JoinType.CROSS, Ships.direction, Directions.id)
                .selectAll()
                .onEach { println(it) }
            val expectedDirections = Direction.values().map { it.name }
            val allElements = Directions.selectAll()
            assertEquals(expectedDirections.size, allElements.count().toInt())
            val actual = allElements.toSet()
            assertTrue {
                actual.all {
                    return@all it[Directions.direction] in expectedDirections
                }
            }
        }
    }
}