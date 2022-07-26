package tesis.hunterzzz.tragoapp.domain

import androidx.room.*
import tesis.hunterzzz.tragoapp.data.model.Drink
import tesis.hunterzzz.tragoapp.data.model.DrinkEntity

@Dao
interface TragosDao {

    @Query("SELECT * FROM tragosEntity")
    suspend fun getAllFavoriteDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(trago:DrinkEntity)

    @Delete
    suspend fun deleteDrink(drink: DrinkEntity)

}