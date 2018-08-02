package com.common.utils.glide

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import io.reactivex.ObservableOnSubscribe


/**
 * author：WangLei
 * date:2018/8/2.
 * QQ:619321796
 */
object BlurUtil {

    fun loadBitmap(context: Context, imagePath: String, imageView: ImageView): io.reactivex.Observable<Bitmap> {
        return io.reactivex.Observable.create(ObservableOnSubscribe<Bitmap> { emitter ->
            GlideImgHelper.loadImageViewContentBitmap(context, imagePath, object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    emitter.onNext(resource)
                }
            })
        });
    }
}