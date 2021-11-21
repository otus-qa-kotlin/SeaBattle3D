import configuration.DatabaseSettings
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import repository.model.Ships
import kotlin.test.assertEquals

internal class GameObjectsTest {

    val database = DatabaseSettings.embedded

    @Test
    fun findAllShips() {
        transaction {
            val shipsQuery = Ships.selectAll()

            assertEquals(2, shipsQuery.andWhere { Ships.len eq 4 }.count())
        }
    }
}