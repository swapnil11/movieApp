package com.example.finalplayground.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finalplayground.R
import com.example.finalplayground.databinding.LayoutMovieListItemBinding
import com.example.finalplayground.domain.model.Configuration
import com.example.finalplayground.domain.model.Movie
import com.example.finalplayground.ui.common.ParentRecyclerViewAdapter.GenericViewHolder
import com.example.finalplayground.ui.model.LatestMoviesContentHolder
import com.example.finalplayground.ui.model.ListOfMoviesContentHolder
import com.example.finalplayground.ui.model.MovieContentHolder
import com.example.finalplayground.ui.model.MovieType

class ParentRecyclerViewAdapter(
    var list: MutableList<MovieContentHolder>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<GenericViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: LayoutMovieListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_movie_list_item, parent, false)
        return GenericViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(list.getOrNull(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].movieType.value
    }

    fun setItems(list: List<MovieContentHolder>) {
        this.list.addAll(list.filter { it.isContentAvailable() })
        notifyDataSetChanged()
    }

    class GenericViewHolder(
        private val binding: LayoutMovieListItemBinding,
        private val clickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieContentHolder?) {
            item?.run {
                binding.categoryName.text =
                    itemView.context.resources.getStringArray(R.array.categories)[item.movieType.value]
                when (item.movieType) {
                    MovieType.LATEST_MOVIE_TYPE -> {
                        (item as LatestMoviesContentHolder).movie?.run {
                            binding.movieList.adapter = ChildRecyclerViewAdapter(
                                listOf(this),
                                item.configuration,
                                clickListener
                            )
                        }
                    }
                    else -> {
                        val movies = (item as ListOfMoviesContentHolder).response?.results
                        binding.movieList.adapter = ChildRecyclerViewAdapter(
                            movies,
                            item.configuration,
                            clickListener
                        )
                    }
                }
            }
        }
    }
}

interface ItemClickListener {
    fun onItemClick(item: Movie?, configuration: Configuration)
}
