package com.example.interviewpreparation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.interviewpreparation.model.Product
import com.example.interviewpreparation.Repository.ProductRepository

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val productLiveData : LiveData<List<Product>>
    get() = repository.products

   /* init {
        viewModelScope.launch {
            callRepositoryApi()
        }
    }*/

    suspend fun callRepositoryApi() {
        repository.getProductApiResponse()
    }

    suspend fun callRepositoryApi1() {
        repository.getProductApiResponse()
    }


}