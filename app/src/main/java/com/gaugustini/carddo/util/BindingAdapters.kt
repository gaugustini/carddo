package com.gaugustini.carddo.util

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.isVisible = isGone
}

@BindingAdapter("contact")
fun TextInputEditText.setContact(str: String?) {
    if (str != null) {
        setText(str)
    }
}

//@BindingAdapter("background")
//fun TextInputEditText.setBackground(colorHex: String) {
//    setBackgroundColor(Color.parseColor(colorHex))
//}

//@BindingAdapter("cardBackgroundColor")
//fun MaterialCardView.setCardBackgroundColor(colorHex: String) {
//    setBackgroundColor(Color.parseColor(colorHex))
//}
