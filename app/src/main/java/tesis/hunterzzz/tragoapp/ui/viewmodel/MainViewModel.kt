package tesis.hunterzzz.tragoapp.ui.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tesis.hunterzzz.tragoapp.data.model.Drink
import tesis.hunterzzz.tragoapp.data.model.DrinkEntity
import tesis.hunterzzz.tragoapp.domain.Repo
import tesis.hunterzzz.tragoapp.vo.Resource
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    private val tragosData =  MutableLiveData<String>()

    fun setTrago(tragoName:String){
        tragosData.value = tragoName
    }

    init {
        setTrago("margarita")
    }

    val fetchTragosList = tragosData.distinctUntilChanged().switchMap { nombreTrago ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getTragosList(nombreTrago))

            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun guardarTrago(trago:DrinkEntity){
        viewModelScope.launch {
            repo.insertTrago(trago)
        }
    }

    fun getTragosFavoritos() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(repo.getTragosFavoritos())

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun deleteTrago(drink: Drink) {
        viewModelScope.launch {
            repo.deleteDrink(drink)
        }
    }


}