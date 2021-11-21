package configuration

import org.jetbrains.exposed.sql.Database
import java.sql.DriverManager

object DatabaseSettings {
    val embedded by lazy {
        Database.connect("jdbc:sqlite:data/sea-battle.db", "org.sqlite.JDBC")
    }

    val inmemory by lazy {
        Database.connect({
            DriverManager.getConnection("jdbc:h2:mem:sea-battle;MODE=PostgreSQL")
        })
    }
}