package com.example.huaxie.threerows

import android.support.v4.view.ViewCompat
import android.view.View
import android.view.ViewAnimationUtils

/**
 *
 * @param view the view to animate
 * @param x    the x point of view
 * @param y    the y point of view
 */
fun showCircularReveal(x: Int, y: Int, view: View) {
    if (viewNotValidForAnimation(view)) {
        return
    }
    val radius = Math.hypot(x.toDouble(), y.toDouble()).toFloat()
    view.visibility = View.VISIBLE
    ViewAnimationUtils.createCircularReveal(view, x, y, 0f, radius).start()
}

fun viewNotValidForAnimation(view: View?): Boolean {
    return view == null || !ViewCompat.isAttachedToWindow(view)
}


