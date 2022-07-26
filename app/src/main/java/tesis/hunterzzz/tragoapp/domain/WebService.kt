package tesis.hunterzzz.tragoapp.domain

import retrofit2.http.GET
import retrofit2.http.Query
import tesis.hunterzzz.tragoapp.data.model.DrinkList

interface WebService {

    @GET("search.php")
    suspend fun getTragoByName(@Query("s") tragoName:String):DrinkList
}