package com.e.fabwithanmation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

     //region variable declaration

     var fab_open: Animation? = null
     var fab_close: Animation? = null
     var fab_clock: Animation? = null
     var fab_anticlock: Animation? = null
     var isOpen = false
    //endregion

    //region oncreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        fab_clock = AnimationUtils.loadAnimation(this, R.anim.fab_clock)
        fab_anticlock = AnimationUtils.loadAnimation(this, R.anim.fab_anticlock)


        listeners()
    }
    //endregion

    //region clicklisteners
    private fun listeners(){

        //fab gallery click
        fab_gallery?.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "Gallery", Toast.LENGTH_SHORT).show()
        })
         //fab camera click
        fab_camera?.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "Camera", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CameraActivity::class.java)
            startActivityForResult(intent, 1)

           // CameraActivity.startActivity(WeakReference(this as Activity))
        })

        //fab main click
        fab_main?.setOnClickListener(View.OnClickListener {
            if (isOpen) {
                tv_camera?.visibility = View.INVISIBLE
                tv_gallery?.visibility = View.INVISIBLE
                fab_gallery?.startAnimation(fab_close)
                fab_camera?.startAnimation(fab_close)
                fab_main?.startAnimation(fab_anticlock)

                isOpen = false
            } else {
                tv_camera?.visibility = View.VISIBLE
                tv_gallery?.visibility = View.VISIBLE
                fab_gallery?.startAnimation(fab_open)
                fab_camera?.startAnimation(fab_open)
                fab_main?.startAnimation(fab_clock)
                isOpen = true
            }
        })
    }
    //endregion

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 1
        if (requestCode == 1) {
            // 2
            if (resultCode == Activity.RESULT_OK) {
                // 3
                val task = data?.getStringExtra("image_URI")

                task?.let {

                    val myUri:Uri = Uri.parse(it)
                    image_view.setImageURI(myUri)
                    Log.d("image capture ",task)
                    Toast.makeText(this, task, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    companion object {
        fun startActivity(activityWeakReference: WeakReference<Activity>) {
            val intent = Intent(activityWeakReference.get(), MainActivity::class.java)
            activityWeakReference.get()?.startActivity(intent)
        }
    }
}



