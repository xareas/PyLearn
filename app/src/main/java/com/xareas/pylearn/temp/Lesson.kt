package com.xareas.pylearn.temp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Lesson(@StringRes val stringResourceId: Int,
             @DrawableRes val drawableResourceId: Int) {
}