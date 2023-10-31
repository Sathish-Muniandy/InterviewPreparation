package com.example.interviewpreparation.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.interviewpreparation.ApiInterface.ApiCall
import com.example.interviewpreparation.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiCall: ApiCall) {

    private val _products = MutableLiveData<List<Product>>()
    val products : LiveData<List<Product>>
    get() = _products

    suspend fun getProductApiResponse() {
        val result = apiCall.getProducts()
        if(result!=null && result.isSuccessful) {
            _products.postValue(result.body())
        }
    }

}