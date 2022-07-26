package tesis.hunterzzz.tragoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tesis.hunterzzz.tragoapp.data.model.DrinkEntity
import tesis.hunterzzz.tragoapp.domain.TragosDao

@Database(entities = arrayOf(DrinkEntity::class), version = 1)
abstract class AppDadabase : RoomDatabase() {

    //    appDatabase.tragoDao().insertTrago(trago)
    abstract fun tragoDao(): TragosDao

    companion object {

        private var instancia: AppDadabase? = null

        fun getDatabase(context: Context): AppDadabase {
            instancia = instancia ?: Room.databaseBuilder(
                context.applicationContext,
                AppDadabase::class.java,
                "Tabla_tragos"
            ).build()

            return instancia!!
        }

        fun detroyInstance(){
            instancia = null
        }
    }


}