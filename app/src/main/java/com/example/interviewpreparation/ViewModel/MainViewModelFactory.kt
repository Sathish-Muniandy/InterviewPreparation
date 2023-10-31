package com.example.interviewpreparation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.interviewpreparation.Repository.ProductRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository : ProductRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(repository) as T
    }

}

