package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.content.Context
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

fun getRoundedCornerBitmap(
    context: Context,
    uri: String,
    topLeftDp: Int=0,
    topRightDp: Int=0,
    bottomLeftDp: Int=0,
    bottomRightDp: Int=0,
    output: ImageView
) {
    val roundTopLeftDp = convertDpToPixel(context, topLeftDp)
    val roundTopRightDp = convertDpToPixel(context, topRightDp)
    val roundBottomLeftDp = convertDpToPixel(context, bottomLeftDp)
    val roundBottomRightDp = convertDpToPixel(context, bottomRightDp)
    Glide.with(context).load(uri).apply(
        RequestOptions().centerCrop().transform(
            CenterCrop(),
            GranularRoundedCorners(
                roundTopRightDp,
                roundTopLeftDp,
                roundBottomRightDp,
                roundBottomLeftDp
            )
        )
    )
        .into(output)
}

fun getRoundedAllCornerBitmap(context: Context, uri:String, dp: Int, output: ImageView) {
    return getRoundedCornerBitmap(context, uri, dp, dp, dp, dp, output)
}

fun getRoundedBottomCornerBitmap(context: Context, uri:String, dp: Int, output: ImageView) {
    return getRoundedCornerBitmap(context, uri,bottomLeftDp= dp,bottomRightDp= dp,output= output)
}

fun getRoundedTopCornerBitmap(context: Context, uri:String, dp: Int, output: ImageView) {
    return getRoundedCornerBitmap(context, uri,topLeftDp= dp,topRightDp= dp,output= output)
}

fun getImageToLayout(
    context: Context,
    layout: ConstraintLayout,
    uri: String,
    size: Int = 500,
    widthOrHeight: String = "w"
) {
    Glide.with(context).load(uri)
        .into(object : SimpleTarget<Drawable?>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable?>?
            ) {

                layout.setBackground(resource)
                layout.background.colorFilter = LightingColorFilter(0x76666666, 9)
            }
        })
}

fun convertDpToPixel(context: Context, dp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        context.resources.displayMetrics
    )
}

fun convertPixelsToDp(px: Float, context: Context): Float {
    return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}