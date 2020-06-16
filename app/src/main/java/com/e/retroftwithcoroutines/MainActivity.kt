package com.e.retroftwithcoroutines

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.e.retroftwithcoroutines.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var viewModel : MainViewModel = MainViewModel()

    //region Oncreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        viewModel.getTodoFromServer()

        //loading observer
        viewModel.loadingMessage.observe(this, Observer {
            if (it) {
                progress?.visibility = View.VISIBLE
            } else {
                progress?.visibility = View.GONE
            }
        })

        //success Response observer
        viewModel.successPost.observe(this, Observer {
            Toast.makeText(this,it.title,Toast.LENGTH_LONG).show()
            textView_sample?.text = String.format("title = %s", it.title)
        })

        //Error response Observer
        viewModel?.errorMessage?.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            Log.d("http","Error msg "+it )
        })

    }
    //endregion
}


