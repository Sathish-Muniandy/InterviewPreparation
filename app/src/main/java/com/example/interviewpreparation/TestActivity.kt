package com.example.interviewpreparation

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.interviewpreparation.ApiInterface.RvPosition
import com.example.interviewpreparation.databinding.ActivityTestBinding
import com.example.interviewpreparation.model.Test
import java.nio.channels.AsynchronousByteChannel
import java.util.*
import javax.xml.datatype.DatatypeConstants.MINUTES


class TestActivity : AppCompatActivity(),RvPosition {

    private lateinit var binding : ActivityTestBinding
    var length = 0

    var list = arrayListOf(2,8,5,0)
    var temp = 0
    var total = 0
    var TAG = "Repeat:"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityTestBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test)
    //     setContentView(R.layout.activity_test);

        binding.test = Test("kumar",30)

        val array = arrayOf(1,2,3)
        println("Size:${array[0]}")
        for(i in 1..array.size) {
            println("number:$i")
        }
        /*val task = MyTask()
        task.execute("Hello")

        val timer = Timer()
        val timer1 = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() { // Function runs every MINUTES minutes.
                // Run the code you want here
                Log.i(TAG,"one minute")
            }
        }, 0, 1000)

        timer1.schedule(object : TimerTask(){
            override fun run() {
                Log.i(TAG,"fve minute")
            }
        },0,5000)
*/

        /*list.add(5)
        list.add(3)
        list.add(8)
        list.add(1)*/

        /*for(i in 0..50) {
            total+= i
        }

        System.out.println("Normal array:")
        System.out.println("Total:$total")
        for (i in 0..list.size-1) {
            System.out.println(list[i])
        }

        for (i in 0..list.size-1) {
            for (j in i+1..list.size-1) {
                if(list[j]>list[i]) {
                    temp = list[i]
                    list[i] = list[j]
                    list[j] = temp
                }
            }
        }

        System.out.println("Sorting array:")
        for (i in 0..list.size-1) {
            System.out.println(list[i])
        }*/

        /*val rv = findViewById<RecyclerView>(R.id.add_rv)
        val plusBtn = findViewById<Button>(R.id.plus_btn)
        val minusBtn = findViewById<Button>(R.id.minus_btn)
        rv.layoutManager = LinearLayoutManager(this)
        var rvAdapter = RvAdapter(length, this)
        rv.adapter = rvAdapter

        plusBtn.setOnClickListener(View.OnClickListener {
            if (length == 4) {
                Toast.makeText(this@TestActivity, "Item Count Reached", Toast.LENGTH_SHORT).show()
            } else {
                length = length + 1
                rvAdapter.RvLength(length)
            }
        })


        minusBtn.setOnClickListener(View.OnClickListener {
            if(length==0) {
                Toast.makeText(this@TestActivity, "No Item Found", Toast.LENGTH_SHORT).show()
            }else {
                length = length-1
                rvAdapter.RvLength(length)
            }
        })*/

     //   val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
       // binding.addRv.apply {
//            binding.addRv.layoutManager = layoutManager
//            binding.addRv.adapter = rvAdapter
      //  }



    }

    override fun getPosition(position: Int) {
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
    }


    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }

    inner class MyTask : AsyncTask<String,Int,Int>() {

        override fun doInBackground(vararg params: String?) : Int {

            val timer = Timer()
            timer.schedule(object : TimerTask(){
                override fun run() {
                    Log.i("Async:",params[0]!!)
                }
            },0,5000)
            return 1
        }

    }

}