package com.example.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R

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

private fun setPictureWinner(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.too_sad_26009
    }

}


