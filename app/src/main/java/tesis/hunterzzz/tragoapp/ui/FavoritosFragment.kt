package tesis.hunterzzz.tragoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tesis.hunterzzz.tragoapp.AppDadabase
import tesis.hunterzzz.tragoapp.data.DataSourseImpl
import tesis.hunterzzz.tragoapp.data.model.Drink
import tesis.hunterzzz.tragoapp.databinding.FragmentFavoritosBinding
import tesis.hunterzzz.tragoapp.domain.RepoImpl
import tesis.hunterzzz.tragoapp.ui.viewmodel.MainViewModel
import tesis.hunterzzz.tragoapp.vo.Resource
@AndroidEntryPoint
class FavoritosFragment : Fragment(),MainAdapter.onTragoClickListener {

    private var _binding: FragmentFavoritosBinding? = null
    private val binding get() = _binding!!

//    @Inject
//    val viewModel by viewModels<MainViewModel> ()
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getTragosFavoritos().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val lista = it.data.map {
                        Drink(it.tragoId, it.imagen,it.nombre,it.descripcion, it.hasAlcohol)
                    }.toMutableList()
//                    val lista = it.data.asDrinkList()

                    adapter = MainAdapter(
                        requireContext(), lista, this
                    )
                    binding.rvTragosFavoritos.adapter = adapter
//                    Log.d("Lista favoritos", "${it.data}")
                }
                is Resource.Failure -> TODO()
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvTragosFavoritos.layoutManager = LinearLayoutManager(context)
        binding.rvTragosFavoritos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTragoClick(drink: Drink,position:Int) {
        viewModel.deleteTrago(drink)
        adapter.deleteDrink(position)

    }
}