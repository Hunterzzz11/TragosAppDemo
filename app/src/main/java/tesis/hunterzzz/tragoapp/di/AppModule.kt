package tesis.hunterzzz.tragoapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tesis.hunterzzz.tragoapp.AppDadabase
import javax.inject.Singleton

/**
 * Created by Jose Luis Quispe on 25/07/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDadabase::class.java,
        "Tabla_tragos"
    ).build()

    @Singleton
    @Provides
    fun provideTragosDao(db:AppDadabase) = db.tragoDao()
}