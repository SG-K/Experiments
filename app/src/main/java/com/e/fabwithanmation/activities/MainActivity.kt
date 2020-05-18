package com.e.fabwithanmation.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.e.fabwithanmation.R
import com.e.fabwithanmation.adapter.ImagesAdapter
import com.e.fabwithanmation.roomdb.ImageUris
import com.e.fabwithanmation.roomdb.ImageuriRepository
import com.e.fabwithanmation.viewmodel.ImageUrisViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    //region variables
    var imageUrisViewModel: ImageUrisViewModel? = null
     val imagesAdapter : ImagesAdapter =
         ImagesAdapter(this)
    //repository
    private var repository: ImageuriRepository ?= null

    //endregion


    //region oncreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //repository intitalzaton
        repository = ImageuriRepository(this)

        //viewmodel intitalzaton
        imageUrisViewModel = ViewModelProviders.of(this).get(ImageUrisViewModel::class.java)
        repository?.getImageuris()?.observe(this,Observer<List<ImageUris>> { this.renderImageUris(it) })

        //Apply animation to fab
        imageUrisViewModel?.fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        imageUrisViewModel?.fab_open = AnimationUtils.loadAnimation(this,R.anim.fab_open)
        imageUrisViewModel?.fab_clock = AnimationUtils.loadAnimation(this,R.anim.fab_clock)
        imageUrisViewModel?.fab_anticlock = AnimationUtils.loadAnimation(this,R.anim.fab_anticlock
        )

       recyclerViewSetup()

        listeners()
    }
    //endregion

    //region clicklisteners
    private fun listeners(){

        //fab gallery click
        fab_gallery?.setOnClickListener(View.OnClickListener {

            val pickIntent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                    .putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                type = "image/*"
            }
            startActivityForResult(pickIntent, imageUrisViewModel!!.RES_IMAGE)

            Toast.makeText(applicationContext, "Gallery", Toast.LENGTH_SHORT).show()
        })
         //fab camera click
        fab_camera?.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "Camera", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,
                CameraActivity::class.java)
            startActivityForResult(intent, imageUrisViewModel!!.CAMERA_IMAGE)

           // CameraActivity.startActivity(WeakReference(this as Activity))
        })

        //fab main click
        fab_main?.setOnClickListener(View.OnClickListener {
            if (imageUrisViewModel!!.isOpen) {
                tv_camera?.visibility = View.INVISIBLE
                tv_gallery?.visibility = View.INVISIBLE
                fab_gallery?.startAnimation(imageUrisViewModel?.fab_close)
                fab_camera?.startAnimation(imageUrisViewModel?.fab_close)
                fab_main?.startAnimation(imageUrisViewModel?.fab_anticlock)

                imageUrisViewModel!!.isOpen = false
            } else {
                tv_camera?.visibility = View.VISIBLE
                tv_gallery?.visibility = View.VISIBLE
                fab_gallery?.startAnimation(imageUrisViewModel?.fab_open)
                fab_camera?.startAnimation(imageUrisViewModel?.fab_open)
                fab_main?.startAnimation(imageUrisViewModel?.fab_clock)
                imageUrisViewModel!!.isOpen = true
            }
        })
    }
    //endregion

    //region Configure recyclerview
    private  fun recyclerViewSetup(){
        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            adapter = imagesAdapter
        }
    }
    //endregion

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == imageUrisViewModel?.CAMERA_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {

                val task = data?.getStringExtra("image_URI")
                task?.let {
                    repository?.setImageuri(ImageUris(0,it))
                }
            }
        }else  if (requestCode == imageUrisViewModel?.RES_IMAGE) {
                if (resultCode == Activity.RESULT_OK) {
                    //multiPhotos from gallery
                    if (data?.clipData != null) {
                        val imageUri : ClipData = data.clipData!!
                        for (i in 0..imageUri.itemCount-1) {
                            repository?.setImageuri(ImageUris(0,imageUri.getItemAt(i).uri.toString()))
                        }
                    }else  if (data?.data != null) {     //single Photo from gallery
                        val imageUri = data.data
                        repository?.setImageuri(ImageUris(0,imageUri.toString()))
                    }
                }
                }
            }

    //data set to Listadapter
    private fun renderImageUris(uris: List<ImageUris>?){
        imagesAdapter.submitList(uris)
        imagesAdapter.notifyDataSetChanged()
    }
    }




