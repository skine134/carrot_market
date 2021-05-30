package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import java.io.ByteArrayOutputStream
import java.util.*

fun uploadImageToFireBase(imageView: ImageView):String
{
    val uuid = UUID.randomUUID().toString()
    val profileImageRef= ApplicationClass.storageReference.child("image/$uuid")
    val bitmap = getBitmapFromView(imageView)
    val baos = ByteArrayOutputStream()
    var imageUrl = ""
    bitmap!!.compress(Bitmap.CompressFormat.JPEG,100,baos)
    val data = baos.toByteArray()
    var uploadTask = profileImageRef.putBytes(data)
    uploadTask.addOnProgressListener {
        val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
        Log.d("firebase upload progress", "Upload is $progress% done")
    }.addOnCanceledListener {
        Log.e("firebase storage error","failure upload image")
    }.addOnSuccessListener {

    }
    val urlTask = uploadTask.continueWithTask {
        if (!it.isSuccessful) {
            it.exception?.let {
                throw it
            }
        }
        profileImageRef.downloadUrl
    }.addOnCompleteListener {
        if (it.isSuccessful) {
            imageUrl = it.result!!.path!!
        } else {
            Log.e("firebase error","not found download url")
        }
    }
    return imageUrl
}
fun getBitmapFromView(view: View): Bitmap?{
    var bitmap =
        Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    var canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}

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