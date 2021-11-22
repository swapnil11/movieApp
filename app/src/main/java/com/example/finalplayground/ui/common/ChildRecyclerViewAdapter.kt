package com.example.finalplayground.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finalplayground.R
import com.example.finalplayground.databinding.LayoutChildMovieItemBinding
import com.example.finalplayground.domain.model.Configuration
import com.example.finalplayground.domain.model.Movie

class ChildRecyclerViewAdapter(
    val item: List<Movie>?,
    private val configuration: Configuration,
    private val clickListener: ItemClickListener
) :
    RecyclerView.Adapter<ChildRecyclerViewAdapter.ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: LayoutChildMovieItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_child_movie_item, parent, false)
        return ChildViewHolder(binding, configuration, clickListener)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.bind(item?.getOrNull(position))
    }

    override fun getItemCount(): Int {
        return item.orEmpty().size
    }

    class ChildViewHolder(
        private val binding: LayoutChildMovieItemBinding,
        private val configuration: Configuration,
        private val clickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie?) {
            val url = configuration.images?.run {
                secureBaseURL + logoSizes?.get(1) + item?.poster_path
            }
            binding.downloadURL = url
            binding.childImageView.contentDescription = item?.title
            itemView.setOnClickListener {
                clickListener.onItemClick(item, configuration)
            }
        }
    }
}
