package com.example.finalplayground.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.example.finalplayground.R
import com.example.finalplayground.data.network.Resource
import com.example.finalplayground.databinding.FragmentMovieDetailBinding
import com.example.finalplayground.ui.common.showErrorBar
import com.example.finalplayground.ui.viewmodels.MovieDetailViewModel

class MovieDetailFragment : Fragment() {
    private val viewModel by hiltNavGraphViewModels<MovieDetailViewModel>(R.id.detailsFragment)
    private val args by navArgs<MovieDetailFragmentArgs>()
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMovieDetailBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = this@MovieDetailFragment
            vm = this@MovieDetailFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeMovieDetail()
    }

    private fun observeMovieDetail() {
        args.item?.id?.let { movieId ->
            viewModel.fetchMovieDetail(movieId).observe(viewLifecycleOwner) {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        binding.isLoading = true
                    }
                    Resource.Status.SUCCESS -> {
                        it.data?.run {
                            val url = args.configuration.images?.run {
                                secureBaseURL + posterSizes?.get(2) + args.item?.poster_path
                            }
                            binding.downloadUrl = url
                            binding.item = this
                            binding.movieGenre.text = genres.firstOrNull()?.name ?: ""
                            binding.runningTime.text = getString(R.string.running_time, runtime.toString())
                        }

                        binding.isLoading = false
                        binding.shouldShowUIComponents = true
                    }
                    Resource.Status.ERROR -> {
                        binding.isLoading = false
                        binding.shouldShowUIComponents = false
                        binding.errorText.isVisible = true
                        showErrorBar(it.message)
                    }
                }
            }
        }
    }
}
