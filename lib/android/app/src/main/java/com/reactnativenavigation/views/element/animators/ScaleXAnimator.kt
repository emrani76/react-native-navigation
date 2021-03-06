package com.reactnativenavigation.views.element.animators

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.addListener
import androidx.core.animation.doOnStart
import com.facebook.react.views.text.ReactTextView
import com.reactnativenavigation.parse.SharedElementTransitionOptions
import com.reactnativenavigation.utils.withDuration
import com.reactnativenavigation.utils.withInterpolator
import com.reactnativenavigation.utils.withStartDelay

class ScaleXAnimator(from: View, to: View) : PropertyAnimatorCreator<ViewGroup>(from, to) {
    override fun shouldAnimateProperty(fromChild: ViewGroup, toChild: ViewGroup): Boolean {
        return fromChild.childCount == 0 && toChild.childCount == 0
    }

    override fun excludedViews(): List<Class<*>> = listOf<Class<*>>(ReactTextView::class.java)

    override fun create(options: SharedElementTransitionOptions): Animator {
        to.scaleX = from.width.toFloat() / to.width
        return ObjectAnimator
                .ofFloat(to, View.SCALE_X, from.width.toFloat() / to.width, 1f)
                .withDuration(options.getDuration())
                .withStartDelay(options.getStartDelay())
                .withInterpolator(options.interpolation.interpolator)
    }
}