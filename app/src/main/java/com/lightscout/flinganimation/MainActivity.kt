package com.lightscout.flinganimation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.VelocityTracker
import android.view.VelocityTracker.obtain
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import com.lightscout.flinganimation.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val photoList = listOf(
            PhotoItem("https://www.pcclean.io/wp-content/uploads/2020/4/BNrIVj.jpg"),
            PhotoItem("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.oswego.edu%2Fcts%2Fsites%2Fwww.oswego.edu.cts%2Ffiles%2Fstyles%2Fpanopoly_image_original%2Fpublic%2Foswego_sunset.jpg%3Fitok%3D3pu5u00T&f=1&nofb=1"),
            PhotoItem("https://images3.alphacoders.com/111/111207.jpg"),
            PhotoItem("https://jooinn.com/images/sunset-532.png"),
            PhotoItem("https://jooinn.com/images/gray-bridge-and-trees.jpg"),
            PhotoItem("https://www.pcclean.io/wp-content/gallery/scenery-hd-wallpapers/Scenery-12.jpg"),
            PhotoItem("https://www.keralatourism.org/images/downloadHRI/resorts/big/1.jpg"),
            PhotoItem("https://www.roomsforafrica.com/images/Weekend_Getaways_kagga_kamma.jpg"),
            PhotoItem("https://eskipaper.com/images/images-2.jpg"),
        )


        val photoAdapter = PhotoAdapter(photoList)
        binding.mainRecycleView.adapter = photoAdapter
        binding.mainRecycleView.layoutManager = LinearLayoutManager(this).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }


        binding.mainRecycleView.setOnTouchListener { view, motionEvent ->
            var velocityTracker = obtain()
            velocityTracker.addMovement(motionEvent)
            velocityTracker.computeCurrentVelocity(1000)

            binding.mainRecycleView.fling(
                velocityTracker.xVelocity.toInt(),
                velocityTracker.yVelocity.toInt()
            )

            Log.d("TAG_J", "velocityTracker xVelocity: ${velocityTracker.xVelocity}")

            velocityTracker.recycle()
            velocityTracker = null


            false
        }
    }
}