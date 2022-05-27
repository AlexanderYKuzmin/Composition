package com.example.composition.presentation

import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("requiredAnswers")
fun bindingRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_scores),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindingRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindingScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindingScorePercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        count
    )
}

@BindingAdapter("image")
fun bindingImage(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(setPictureWinner(winner))
}

@BindingAdapter("enoughCountOfRightAnswers")
fun bindingAnswersProgress(textView: TextView, isEnough: Boolean) {
    val color = ContextCompat.getColor(textView.context, getColorResId(isEnough))
    textView.setTextColor(color)
}

@BindingAdapter("number")
fun bindingQuestionSum(textView: TextView, number: Int) {
    textView.text = number.toString()
}

/*@BindingAdapter("visibleNumber")
fun bindingVisibleNumber(textView: TextView, visibleNumber: Int) {
    textView.text = visibleNumber.toString()
}*/

@BindingAdapter("progressBar")
fun bindingProgressBar(progressBar: ProgressBar, isEnough: Boolean) {
    val color = ContextCompat.getColor(progressBar.context, getColorResId(isEnough))
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

private fun setPictureWinner(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.too_sad_26009
    }
}

private fun getColorResId(isEnough: Boolean): Int {
    return if (isEnough) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
}




