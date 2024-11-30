import androidx.room.Database
import androidx.room.RoomDatabase
import com.galindo.erick.poketinder.data.database.dao.PokemonDao
import com.galindo.erick.poketinder.data.database.entities.MyPokemonEntity


@Database(entities = [MyPokemonEntity::class], exportSchema = false, version = 1)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
}


