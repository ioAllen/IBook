package com.common.utils.glide

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.amsu.marathon.util.glide.GlideCircleTransform
import com.amsu.marathon.util.glide.GlideRoundTransform
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.common.R

/**
 * Glide图片加载
 *
 *
 * Glide特点
 * 使用简单
 * 可配置度高，自适应程度高
 * 支持常见图片格式 Jpg png gif webp
 * 支持多种数据源  网络、本地、资源、Assets 等
 * 高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
 * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
 * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
 * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity
 * --------------------------------------------------------------------------------------------
 * 注：1.由于Glide内部已使用setTag(object)方法，再次使用会导致崩溃，故可使用setTag(key,object)方法
 * 2.with(context) context对象最好传this或者Activity/Fragment对象，以便于glide能自动管理图片加载的生命周期
 */
object GlideImgHelper {

    fun loadImage(context: Context, path: String, imageView: ImageView) {
        loadImageView(context, path, imageView, R.drawable.default_image)
    }

    fun loadImage(context: Context, path: String, simpleTarget: SimpleTarget<Bitmap>) {
        loadImageView(context, path, R.drawable.default_image, R.drawable.default_image, simpleTarget)
    }

    fun loadAvatarImage(context: Context, path: String, imageView: ImageView) {
        loadCircleImageView(context, path, imageView, R.drawable.contact_list_pic_big, R.drawable.contact_list_pic_big)
    }

    fun loadImageView(context: Context, path: String, imageView: ImageView, defaultImg: Int) {
        loadImageView(context, path, imageView, defaultImg, defaultImg)
    }

    /**
     * 设置加载中以及加载失败图片
     *
     * @param context
     * @param path
     * @param imageView
     * @param loadingImage
     * @param errorImageView
     */
    private fun loadImageView(context: Context, path: String, imageView: ImageView, loadingImage: Int, errorImageView: Int) {
        val options = GlideOptions()
        options.placeholder(loadingImage)
        options.error(errorImageView)
        Glide.with(context).load(path).apply(options).into(imageView)
    }

    private fun loadImageView(context: Context, path: String, loadingImage: Int, errorImageView: Int, simpleTarget: SimpleTarget<Bitmap>) {
        val options = GlideOptions()
        options.placeholder(loadingImage)
        options.error(errorImageView)
        Glide.with(context).asBitmap().load(path).apply(options).into(simpleTarget)
    }

    /**
     * 加载带圆角的图片
     *
     * @param context  最好是Activity或Fragment
     * @param url
     * @param erroImg
     * @param emptyImg
     * @param iv
     * @param radius
     */
    fun loadImageViewRadius(context: Context, url: String, iv: ImageView, erroImg: Int, emptyImg: Int, radius: Int) {
        val options = GlideOptions()
        options.placeholder(emptyImg)
        options.error(erroImg)
        options.transform(GlideRoundTransform(context, radius))
        Glide.with(context).load(url).apply(options).into(iv)
    }

    /**
     * 加载带圆角的图片
     *
     * @param context  最好是Activity或Fragment
     * @param url
     * @param erroImg
     * @param emptyImg
     * @param iv
     * @param radius
     */
    fun loadImageViewRadiusCrop(context: Context, url: String, iv: ImageView, erroImg: Int, emptyImg: Int, radius: Int) {
        val options = GlideOptions()
        options.placeholder(emptyImg)
        options.error(erroImg)
        options.transform(GlideRoundTransform(context, radius))
        options.centerCrop()
        Glide.with(context).load(url).apply(options).into(iv)
    }

    /**
     * 加载带圆角的图片
     *
     * @param context 最好是Activity或Fragment
     * @param url
     * @param iv
     * @param round   是否加载带圆角的图片
     */
    fun loadImageViewRadius(context: Context, url: String, iv: ImageView, round: Boolean) {
        val options = GlideOptions()
        options.placeholder(R.drawable.img_loading_default_color)
        options.error(R.drawable.img_loading_default_color)
        if (round) {
            options.transform(GlideRoundTransform(context, 6))
            Glide.with(context).load(url).apply(options).into(iv)
        } else {
            Glide.with(context).load(url).apply(options).into(iv)
        }
    }

    /**
     * 加载圆形图片
     *
     * @param context 最好是Activity或Fragment
     * @param url
     * @param iv
     */
    fun loadCircleImageView(context: Context, url: String, iv: ImageView) {
        val options = GlideOptions()
        options.transform(GlideCircleTransform(context))
        Glide.with(context).load(url).apply(options).into(iv)
    }

    /**
     * 加载圆形图片带加载中失败图片
     *
     * @param context  最好是Activity或Fragment
     * @param url
     * @param erroImg
     * @param emptyImg
     * @param iv
     */
    fun loadCircleImageView(context: Context, url: String, iv: ImageView, erroImg: Int, emptyImg: Int) {
        val options = GlideOptions()
        options.placeholder(emptyImg)
        options.error(erroImg)
        options.transform(GlideCircleTransform(context))
        Glide.with(context).load(url).apply(options).into(iv)
    }

    fun loadCircleImageViewNoCache(context: Context, url: String, iv: ImageView, erroImg: Int, emptyImg: Int) {
        val options = GlideOptions()
        options.placeholder(emptyImg)
        options.error(erroImg)
        options.diskCacheStrategy(DiskCacheStrategy.NONE)
        options.transform(GlideCircleTransform(context))
        Glide.with(context).load(url).apply(options).into(iv)
    }


    /**
     * 加载drawable资源
     *
     * @param mContext
     * @param resourceId 资源id
     * @param mImageView
     */
    fun loadDrawableImageView(mContext: Context, resourceId: Int, mImageView: ImageView) {
        Glide.with(mContext).load(resourceId).into(mImageView)
    }

    /**
     * 加载指定大小
     *
     * @param mContext
     * @param path
     * @param width
     * @param height
     * @param mImageView
     */
    fun loadImageViewSize(mContext: Context, path: String, width: Int, height: Int, mImageView: ImageView) {
        val options = GlideOptions()
        options.override(width, height)
        Glide.with(mContext).load(path).apply(options).into(mImageView)
    }

    /**
     * 设置加载中以及加载失败图片并且指定大小
     *
     * @param mContext
     * @param path
     * @param width
     * @param height
     * @param mImageView
     * @param lodingImage
     * @param errorImageView
     */
    fun loadImageViewSize(mContext: Context, path: String, width: Int, height: Int, mImageView: ImageView, lodingImage: Int, errorImageView: Int) {
        val options = GlideOptions()
        options.placeholder(lodingImage)
        options.error(errorImageView)
        options.override(width, height)
        Glide.with(mContext).load(path).apply(options).into(mImageView)
    }

    /**
     * 设置跳过内存缓存
     *
     * @param mContext
     * @param path
     * @param mImageView
     */
    fun loadImageViewCache(mContext: Context, path: String, mImageView: ImageView) {
        val options = GlideOptions()
        options.skipMemoryCache(true)
        Glide.with(mContext).load(path).apply(options).into(mImageView)
    }

    /**
     * 设置下载优先级
     *
     * @param mContext
     * @param path
     * @param mImageView
     */
    fun loadImageViewPriority(mContext: Context, path: String, mImageView: ImageView) {
        val options = GlideOptions()
        options.priority(Priority.NORMAL)
        Glide.with(mContext).load(path).apply(options).into(mImageView)
    }

    /**
     * 设置缓存策略
     *
     * @param mContext
     * @param path
     * @param mImageView * 策略解说：
     *
     *
     * all:缓存源资源和转换后的资源
     *
     *
     * none:不作任何磁盘缓存
     *
     *
     * source:缓存源资源
     *
     *
     * result：缓存转换后的资源
     */
    fun loadImageViewDiskCache(mContext: Context, path: String, mImageView: ImageView) {
        val options = GlideOptions()
        options.diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(mContext).load(path).apply(options).into(mImageView)
    }


    //api也提供了几个常用的动画：比如crossFade()

    /**
     * 设置加载动画
     *
     * @param mContext
     * @param path
     * @param anim
     * @param mImageView
     */
    fun loadImageViewAnim(mContext: Context, path: String, mImageView: ImageView) {
        Glide.with(mContext).load(path).transition(DrawableTransitionOptions().crossFade()).into(mImageView)
    }

    /**
     * 设置缩略图支持(会先加载缩略图)
     *
     * @param mContext
     * @param path
     * @param mImageView
     */
    fun loadImageViewThumbnail(mContext: Context, path: String, mImageView: ImageView) {
        Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView)
    }

    /**
     * 设置动态转换
     *
     * @param mContext
     * @param path
     * @param mImageView
     */
    fun loadImageViewCrop(mContext: Context, path: String, mImageView: ImageView) {
        val options = GlideOptions()
        options.centerCrop()
        Glide.with(mContext).load(path).apply(options).into(mImageView)
    }


    /**
     * 清理磁盘缓存(需要在子线程中执行)
     *
     * @param mContext
     */
    fun glideClearDiskCache(mContext: Context) {
        Glide.get(mContext).clearDiskCache()
    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    fun clearDiskCache(context: Context) {
        Thread(Runnable {
            Glide.get(context).clearDiskCache()//清理磁盘缓存 需要在子线程中执行
        }).start()
    }

    /**
     * 清理内存缓存(可以在UI主线程中进行)
     *
     * @param context
     */
    fun clearMemory(context: Context) {
        Glide.get(context).clearMemory()//清理内存缓存  可以在UI主线程中进行
    }

    /**
     * 恢复请求，一般在停止滚动的时候
     *
     * @param context
     */
    fun resumeRequests(context: Context) {
        Glide.with(context).resumeRequests()
    }

    /**
     * 暂停请求 正在滚动的时候
     *
     * @param context
     */
    fun pauseRequests(context: Context) {
        Glide.with(context).pauseRequests()
    }
}
