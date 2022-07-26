package tesis.hunterzzz.tragoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tesis.hunterzzz.tragoapp.AppDadabase
import tesis.hunterzzz.tragoapp.R
import tesis.hunterzzz.tragoapp.data.DataSourseImpl
import tesis.hunterzzz.tragoapp.data.model.Drink
import tesis.hunterzzz.tragoapp.databinding.FragmentMainBinding
import tesis.hunterzzz.tragoapp.domain.RepoImpl
import tesis.hunterzzz.tragoapp.ui.viewmodel.MainViewModel
@AndroidEntryPoint
class MainFragment : Fragment(), MainAdapter.onTragoClickListener {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()
        setupSearchView()
        setupObservers()
        binding.btnIrFavoritos.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_favoritosFragment)
        }

    }

    private fun setupObservers() {
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is tesis.hunterzzz.tragoapp.vo.Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is tesis.hunterzzz.tragoapp.vo.Resource.Success -> {
                    binding.progressbar.visibility = View.GONE
                    binding.rvTragos.adapter = MainAdapter(requireContext(), result.data.toMutableList(), this)
                }
                is tesis.hunterzzz.tragoapp.vo.Resource.Failure -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(), "Ocurrio un erro al traer los datos ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.setTrago(p0!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    private fun setupRecyclerView() {
        binding.rvTragos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTragos.addItemDecoration(
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

    override fun onTragoClick(drink: Drink, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_mainFragment_to_tragosDetalleFragment, bundle)
    }


}