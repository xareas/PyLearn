package com.jonareas.pylearn.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.jonareas.pylearn.PyLearnApplication
import com.jonareas.pylearn.R
import com.jonareas.pylearn.data.model.LessonHeader
import com.jonareas.pylearn.databinding.ItemLessonBinding
import com.jonareas.pylearn.utils.OnClickLesson

class LessonAdapter(
    val context: Context,
    val dataset: List<LessonHeader>,
    var listener: OnClickLesson
)
    : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>()
{

    inner class LessonViewHolder(private val itemLessonBinding : ItemLessonBinding) :
        RecyclerView.ViewHolder(itemLessonBinding.root)
      {

       fun bind(lesson : LessonHeader) {

            itemLessonBinding.itemTitle.text = lesson.description //this.itemView.context.getString(lesson.stringResourceId)
            var imgDraw = PyLearnApplication.instance.resources.getIdentifier(lesson.imageName, null,
                     PyLearnApplication.instance.packageName)
            itemLessonBinding.itemImage.setImageResource(imgDraw) //setImageDrawable

            //Controlador de evento click
            itemLessonBinding.root.setOnClickListener {
                listener.OnClickItem(lesson)
            }

            setBackgroundColors(this.itemView.context, imgDraw) //lesson.drawableResourceId
            animateView(itemLessonBinding.root)
        }

        private fun animateView(viewToAnimate: View) {
            if (viewToAnimate.animation == null) {
                val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.scale_xy)
                viewToAnimate.animation = animation
            }
        }

        private fun setBackgroundColors(context: Context, imageResource: Int) {
            val image = BitmapFactory.decodeResource(context.resources, imageResource)
            Palette.from(image).generate { palette ->
                palette?.let {
                    val backgroundColor = it.getDominantColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                    this.itemLessonBinding.creatureCard.setBackgroundColor(backgroundColor)
                    this.itemLessonBinding.itemTitle.setBackgroundColor(backgroundColor)
                    val textColor = if (isColorDark(backgroundColor)) Color.WHITE else Color.BLACK
                    this.itemLessonBinding.itemTitle.setTextColor(textColor)
                }
            }
        }

        private fun isColorDark(color: Int): Boolean {
            val darkness = 1 - (0.299 * Color.red(color) +
                    0.587 * Color.green(color) +
                    0.114 * Color.blue(color)) / 255
            return darkness >= 0.5
        }




      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        var like = false
        val itemBinding = ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        itemBinding.imageViewButton.setOnClickListener {
            like = likeAnimation(itemBinding.imageViewButton, R.raw.bandai_dokkan, like)
        }

        return LessonViewHolder(itemLessonBinding = itemBinding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(lesson = dataset[position])
    }

    override fun getItemCount() = dataset.size

    companion object {
        private fun likeAnimation(imageView: LottieAnimationView,
                                  animation: Int,
                                  like: Boolean) : Boolean {

            if (!like) {
                imageView.setAnimation(animation)
                imageView.playAnimation()
            } else {
                imageView.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(object : AnimatorListenerAdapter() {

                        override fun onAnimationEnd(animator: Animator) {

                            imageView.setImageResource(R.drawable.like)
                            imageView.alpha = 1f
                        }

                    })
            }

            return !like
        }
    }
}