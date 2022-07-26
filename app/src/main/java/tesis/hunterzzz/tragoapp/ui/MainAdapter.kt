package tesis.hunterzzz.tragoapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tesis.hunterzzz.tragoapp.base.BaseViewHolder
import tesis.hunterzzz.tragoapp.data.model.Drink
import tesis.hunterzzz.tragoapp.databinding.TragosRowBinding

class MainAdapter(
    private val context: Context,
    private val tragosList: MutableList<Drink>,
    private val itemClickListener:onTragoClickListener,
//    private val itemClickListener2: FavoritosFragment
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface onTragoClickListener {
        fun onTragoClick(drink: Drink,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = TragosRowBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = MainViewHolder(itemBinding)

        return holder

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(tragosList[position],position)
        }
    }

    fun deleteDrink(position: Int) {
        tragosList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    private inner class MainViewHolder(
//        itemview: View,
        val binding: TragosRowBinding

    ) : BaseViewHolder<Drink>(binding.root) {
        override fun bind(item: Drink,position: Int) = with(binding) {
            Glide.with(context)
                .load(item.imagen)
                .centerCrop()
                .into(ivTrago)

            tvTitulo.text = item.nombre
            tvDescripcion.text = item.descripcion

            binding.root.setOnClickListener{itemClickListener.onTragoClick(item, position)}

        }

    }
}