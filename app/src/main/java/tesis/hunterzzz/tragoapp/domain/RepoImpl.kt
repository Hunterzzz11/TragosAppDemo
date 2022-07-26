package tesis.hunterzzz.tragoapp.domain

import tesis.hunterzzz.tragoapp.data.DataSourseImpl
import tesis.hunterzzz.tragoapp.data.model.Drink
import tesis.hunterzzz.tragoapp.data.model.DrinkEntity
import tesis.hunterzzz.tragoapp.vo.Resource
import javax.inject.Inject

class RepoImpl @Inject constructor(private val dataSourse: DataSourse):Repo {
    override suspend fun getTragosList(tragoName:String): Resource<List<Drink>> {
        return dataSourse.getTragoByName(tragoName)
    }

    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return dataSourse.getTragosFavoritos()
    }

    override suspend fun insertTrago(trago: DrinkEntity) {
        dataSourse.insertTragoRoom(trago)
    }

    override suspend fun deleteDrink(drink: Drink) {
        dataSourse.deleteDrink(drink)
    }
}