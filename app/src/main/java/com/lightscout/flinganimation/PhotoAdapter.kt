package com.lightscout.flinganimation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.VelocityTracker
import android.view.VelocityTracker.obtain
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ListPopupWindow.WRAP_CONTENT
import androidx.cardview.widget.CardView
import androidx.dynamicanimation.animation.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lightscout.flinganimation.databinding.PhotoItemLayoutBinding


class PhotoAdapter(var photoList: List<PhotoItem>): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    inner class PhotoViewHolder(val binding: PhotoItemLayoutBinding, var isExpand: Boolean = false) : RecyclerView.ViewHolder(
        binding.root
    ) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder = PhotoViewHolder(
        PhotoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {


        holder.binding.apply {

            Glide.with(this.photoImageView)
                .load(photoList[position].photo)
                .into(this.photoImageView)


        }

        holder.itemView.setOnClickListener { view ->
//            var velocityTracker = obtain()
//
//            velocityTracker.addMovement(motionEvent)
//            velocityTracker.computeCurrentVelocity(1000)
//            Log.d("TAG_J", "xVelocity: ${velocityTracker.xVelocity}")
//            if(velocityTracker.xVelocity != 0.0F){
//                if(holder.isExpand){
//                    getSpringAnimation(view, DynamicAnimation.SCALE_X, 1.3F,1F).start()
//                    getSpringAnimation(view, DynamicAnimation.SCALE_Y, 1.3F,1F).start()
//                    holder.isExpand = false
//                }
//            }else {

                if(!holder.isExpand){
                    getSpringAnimation(view, DynamicAnimation.SCALE_X, .7F,1.3F).start()
                    getSpringAnimation(view, DynamicAnimation.SCALE_Y, .7F,1.3F).start()
                    holder.isExpand = true
                }else{
                    getSpringAnimation(view, DynamicAnimation.SCALE_X, 1.3F,1F).start()
                    getSpringAnimation(view, DynamicAnimation.SCALE_Y, 1.3F,1F).start()
                    holder.isExpand = false
                }

        }



    }

    override fun getItemCount(): Int = photoList.size

    private fun getSpringAnimation(
        view: View,
        springAnimationType: FloatPropertyCompat<View>,
        initialPosition: Float,
        finalPosition: Float
    ): SpringAnimation {
        return SpringAnimation(view, springAnimationType).setStartValue(initialPosition).also {
            val spring = SpringForce()
            spring.finalPosition = finalPosition
            spring.stiffness = SpringForce.STIFFNESS_MEDIUM // optional
            spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY // optional
            it.spring = spring
        }

    }
}