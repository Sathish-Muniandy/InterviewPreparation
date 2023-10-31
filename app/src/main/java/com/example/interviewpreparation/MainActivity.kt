package com.example.interviewpreparation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.interviewpreparation.DaggerDI.DiApplicaton
import com.example.interviewpreparation.ViewModel.MainViewModelFactory
import com.example.interviewpreparation.ViewModel.ProductViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var productViewModel: ProductViewModel
    lateinit var rl : RelativeLayout

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val refreshedToken: String = FirebaseInstanceId.getInstance().getToken()
//        Log.i( "Refreshed token: $refreshedToken")
        rl = findViewById(R.id.rl)
        (application as DiApplicaton).applicationComponent.inject(this)

        productViewModel = ViewModelProvider(this, mainViewModelFactory)
                           .get(ProductViewModel::class.java)


        lifecycleScope.launch {
            productViewModel.callRepositoryApi()
        }

        productViewModel.productLiveData.observe(this,{
            rl.visibility = View.GONE
            Toast.makeText(this@MainActivity,it.get(0).title.toString(),Toast.LENGTH_LONG).show()
        });

    }
}