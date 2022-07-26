package tesis.hunterzzz.tragoapp.domain

import tesis.hunterzzz.tragoapp.data.model.Drink
import tesis.hunterzzz.tragoapp.data.model.DrinkEntity
import tesis.hunterzzz.tragoapp.vo.Resource

interface Repo {
    suspend fun getTragosList(tragoName:String):Resource<List<Drink>>

    suspend fun getTragosFavoritos():Resource<List<DrinkEntity>>

    suspend fun insertTrago(trago:DrinkEntity)

    suspend fun deleteDrink(drink: Drink)
}