package com.example.finalplayground.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.finalplayground.data.network.Resource
import com.example.finalplayground.domain.usecases.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    fun fetchMovieDetail(id: Int) = liveData {
        emit(Resource.loading())
        emit(useCase.movieDetail(id))
    }
}
