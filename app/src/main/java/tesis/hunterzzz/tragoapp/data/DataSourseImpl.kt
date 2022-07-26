package tesis.hunterzzz.tragoapp.data

import tesis.hunterzzz.tragoapp.AppDadabase
import tesis.hunterzzz.tragoapp.data.model.Drink
import tesis.hunterzzz.tragoapp.data.model.DrinkEntity
import tesis.hunterzzz.tragoapp.data.model.asFavoriteEntity
import tesis.hunterzzz.tragoapp.domain.DataSourse
import tesis.hunterzzz.tragoapp.domain.TragosDao
import tesis.hunterzzz.tragoapp.vo.Resource
import tesis.hunterzzz.tragoapp.vo.RetrofitClient
import javax.inject.Inject

class DataSourseImpl @Inject constructor(private val tragosDao: TragosDao):DataSourse {

    override suspend fun getTragoByName(tragoName:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getTragoByName(tragoName).drinkList)
    }

    override suspend fun insertTragoRoom(trago:DrinkEntity){
        tragosDao.insertFavorite(trago)
    }

    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return Resource.Success(tragosDao.getAllFavoriteDrinks())
    }

    override suspend fun deleteDrink(drink: Drink) {
        tragosDao.deleteDrink(drink.asFavoriteEntity())

    }


//    companion object{
//        val generateTragosLsit = Resource.Success(listOf(
//            Drink(
//                "https://bakeitwithlove.com/wp-content/uploads/2021/09/Vodka-Margarita-sq.jpg",
//                "margarita",
//                "con azucar wodka y nueces"
//            ),
//            Drink(
//                "https://www.cocinayvino.com/wp-content/uploads/2016/10/FernetConCola.jpg",
//                "fernet",
//                "fernet con cocacola"
//            ),
//            Drink(
//                "https://pbs.twimg.com/media/CERSHJwXIAATqjl.jpg",
//                "toro",
//                "toro con pritty"
//            ),
//            Drink(
//                "https://www.losvinos.com.ar/wp-content/uploads/2019/12/tragos-con-gancia-1200x675.jpg",
//                "Gancia",
//                "Gancia con sprite"
//            ),
//        ))
//    }

}