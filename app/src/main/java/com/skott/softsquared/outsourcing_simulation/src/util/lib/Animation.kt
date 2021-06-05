package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ListView
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun animToRecyclerView(position: Int,recyclerView: RecyclerView,animType: String,duration: Long)
{
    /**
     *
     *
     * RecyclerView 에 애니메이션을 추가합니다.
     */
    val firstPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    val lastPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
    val animSet = AnimatorSet()
    animSet.addListener {
        it.doOnEnd {
            for (i in 0 until lastPosition) {
                val v = recyclerView.getChildAt(i)
                v.translationY = 0F
            }

        }
    }
    val animArray = ArrayList<Animator>()
//    //fadeout anim
//    val fadeItem = parent.getChildAt(position)
//    val fadeout = ObjectAnimator.ofFloat(fadeItem, "alpha", 1f, 0f)
//    fadeout.duration = duration
//    animArray.add(fadeout)

    //when the BottomView totally show.
    for (i in position + 1 until lastPosition) {
        if (i == position + 1) {
        }
        val moveAnimator = ObjectAnimator.ofFloat(
            recyclerView.getChildAt(i),
            animType,
            -recyclerView.getChildAt(i).layoutParams.height.toFloat()
        )
        // 딜레이
        moveAnimator.duration = duration

        // 점점 빠르다 느리게
        moveAnimator.interpolator = AccelerateDecelerateInterpolator()
        animArray.add(moveAnimator)
    }
    animSet.playTogether(animArray)
    animSet.start()

}

fun animToListView(position: Int, listView: ListView,animType:String, duration: Long) {
    /**
     * Listview 에 애니메이션을 추가합니다.
     */
    val animSet = AnimatorSet()
    animSet.addListener {
        it.doOnEnd {
            for (i in 0 until listView.childCount) {
                val v = listView.getChildAt(i)
                v.translationY = 0F
            }

        }
    }
    val animArray = ArrayList<Animator>()
//    //fadeout anim
//    val fadeItem = listView.getChildAt(position)
//    val fadeout = ObjectAnimator.ofFloat(fadeItem, "alpha", 1f, 0f)
//    fadeout.duration = duration
//    animArray.add(fadeout)

    //when the BottomView totally show.
    val firstVisiblePosition = listView.firstVisiblePosition
    for (i in position + 1 until listView.childCount) {
        if (i == position + 1) {
        }
        val moveAnimator = ObjectAnimator.ofFloat(
            listView.getChildAt(i),
            animType,
            -listView.getChildAt(i).layoutParams.height.toFloat()
        )
        // 딜레이
        moveAnimator.duration = duration

        // 점점 빠르다 느리게
        moveAnimator.interpolator = AccelerateDecelerateInterpolator()
        animArray.add(moveAnimator)
    }
    animSet.playTogether(animArray)
    animSet.start()

}