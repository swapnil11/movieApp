package com.example.finalplayground.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.finalplayground.R
import com.example.finalplayground.databinding.FragmentMovieListBinding
import com.example.finalplayground.domain.model.Configuration
import com.example.finalplayground.domain.model.Movie
import com.example.finalplayground.ui.common.ItemClickListener
import com.example.finalplayground.ui.common.ParentRecyclerViewAdapter
import com.example.finalplayground.ui.viewmodels.MovieListViewModel
import com.example.finalplayground.ui.viewmodels.MovieViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(), ItemClickListener {
    private val movieListViewModel: MovieListViewModel by hiltNavGraphViewModels(R.id.movieListFragment)
    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!this::binding.isInitialized) {
            FragmentMovieListBinding.inflate(inflater, container, false).apply {
                binding = this
                lifecycleOwner = this@MovieListFragment
                vm = this@MovieListFragment.movieListViewModel
                clickListener = this@MovieListFragment
            }.root
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDivider()
        observeMovies()
    }

    private fun setupDivider() {
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { itemDecorator.setDrawable(it) }
        binding.mainMovieList.addItemDecoration(itemDecorator)
    }

    private fun observeMovies() {
        movieListViewModel.remoteMovies.observe(viewLifecycleOwner) {
            when (it) {
                is MovieViewState.Loading -> binding.isLoading = true
                is MovieViewState.MovieState -> handleSuccessResponse(it)
                is MovieViewState.LoadingFinished -> {
                    binding.isLoading = false
                    binding.isEmpty = binding.mainMovieList.adapter?.itemCount == 0
                }
            }
        }
    }

    private fun handleSuccessResponse(data: MovieViewState.MovieState) {
        if (binding.mainMovieList.adapter == null) {
            binding.mainMovieList.adapter = ParentRecyclerViewAdapter(mutableListOf(), this)
        }
        (binding.mainMovieList.adapter as ParentRecyclerViewAdapter).setItems(
            listOf(
                data.latestMovies,
                data.nowPlayingMovies,
                data.popularMovies,
                data.topRatedMovies,
                data.upcomingMovies
            )
        )

        binding.isLoading = false
        binding.isEmpty = binding.mainMovieList.adapter?.itemCount == 0
    }

    override fun onItemClick(item: Movie?, configuration: Configuration) {
        findNavController().navigate(
            MovieListFragmentDirections.listFragmentAction(
                item,
                configuration
            )
        )
    }
}
