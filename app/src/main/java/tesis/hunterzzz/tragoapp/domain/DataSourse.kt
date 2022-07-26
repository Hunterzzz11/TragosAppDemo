package tesis.hunterzzz.tragoapp.domain

import tesis.hunterzzz.tragoapp.data.model.Drink
import tesis.hunterzzz.tragoapp.data.model.DrinkEntity
import tesis.hunterzzz.tragoapp.vo.Resource
import tesis.hunterzzz.tragoapp.vo.RetrofitClient

interface DataSourse {
    suspend fun getTragoByName(tragoName:String): Resource<List<Drink>>

    suspend fun insertTragoRoom(trago: DrinkEntity)

    suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>>

    suspend fun deleteDrink(drink: Drink)
}