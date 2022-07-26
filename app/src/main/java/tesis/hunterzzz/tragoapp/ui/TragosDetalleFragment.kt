package tesis.hunterzzz.tragoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import tesis.hunterzzz.tragoapp.AppDadabase
import tesis.hunterzzz.tragoapp.data.DataSourseImpl
import tesis.hunterzzz.tragoapp.data.model.Drink
import tesis.hunterzzz.tragoapp.data.model.DrinkEntity
import tesis.hunterzzz.tragoapp.databinding.FragmentTragosDetalleBinding
import tesis.hunterzzz.tragoapp.domain.RepoImpl
import tesis.hunterzzz.tragoapp.ui.viewmodel.MainViewModel
import tesis.hunterzzz.tragoapp.ui.viewmodel.VMFactory

class TragosDetalleFragment : Fragment() {

    private lateinit var drink: Drink

    private var _binding: FragmentTragosDetalleBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepoImpl(
                DataSourseImpl(
                    AppDadabase.getDatabase(
                        requireActivity().applicationContext
                    )
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable<Drink>("drink")!!
//            Log.d("Detalles frag","${drink.toString()}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTragosDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(drink.imagen)
            .centerCrop()
            .into(binding.ivTrago)

        binding.tvTragoTitle.text = drink.nombre
        binding.tvTragoDescripcion.text = drink.descripcion

        binding.btnGuardarTrago.setOnClickListener {
            viewModel.guardarTrago(
                DrinkEntity(
                    drink.cocktailId,
                    drink.imagen,
                    drink.nombre,
                    drink.descripcion,
                    drink.hasAlcohol
                )
            )
            Toast.makeText(
                requireContext(),
                "Se guardo el trago a favoritos ",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}