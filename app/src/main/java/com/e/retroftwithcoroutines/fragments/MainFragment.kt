package com.e.retroftwithcoroutines.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.e.retroftwithcoroutines.R
import com.e.retroftwithcoroutines.api.Status
import com.e.retroftwithcoroutines.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val viewModel: MainViewModel = MainViewModel()

  //onCreateView wecan assgn and declare view variables
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
//onActivityCreated() to do any final initialisations you want to do once everything has completed.
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //observing livedata from viewmodel
        viewModel.loadData().observe(this, Observer { networkResource ->
            when (networkResource.status) {
                Status.LOADING -> {
                    progress.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    progress.visibility = View.GONE

                    val person = networkResource.data
                    person?.let {
                        message.text =
                            person.firstName + " " + person.lastName + "\n" + person.email

                        Glide.with(activity!!)
                            .load(person.avatar)
                            .into(im_view)
                    }
                }
                Status.ERROR -> {
                    progress.visibility = View.GONE
                    message.text = "error loading data from network"
                }
            }
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
