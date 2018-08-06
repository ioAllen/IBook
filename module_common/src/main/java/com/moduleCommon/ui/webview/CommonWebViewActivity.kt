package com.moduleCommon.ui.webview

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.webkit.*
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.core.RouterHub
import com.common.jsoup.JsoupBookContentManager
import com.common.jsoup.OnBookContentAnalysisListener
import com.moduleCommon.R
import com.moduleCommon.ui.base.CommonBaseActivity
import kotlinx.android.synthetic.main.common_webview_activity.*
import javax.inject.Inject


/**
 * author：WangLei
 * date:2018/1/12.
 * QQ:619321796
 */
@Route(path = RouterHub.COMMON_WEB_VIEW_ACTIVITY)
class CommonWebViewActivity : CommonBaseActivity() {

    @Autowired
    @JvmField
    var title: String? = null

    @Autowired
    @JvmField
    var url: String? = null

    @Inject
    lateinit var jsoupBookContentManager: JsoupBookContentManager

    override fun attachLayoutRes(): Int {
        return R.layout.common_webview_activity
    }

    override fun injection() {
        activityComponent()?.inject(this)
    }

    override fun initData() {
        init()
        setTitleTxt(title)
        showViewBreak(true)
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        if (url != null) {
            showProgressDialog(R.string.brvah_loading, false)
            jsoupBookContentManager.loadBookContent(url!!)
            jsoupBookContentManager.setOnBookContentAnalysisListener(object : OnBookContentAnalysisListener {
                override fun onBookContentAnalysisSuccess(content: String) {
                    dismissProgressDialog()
                    mWebView.loadData(content, "text/html; charset=UTF-8", null)
                }
            })
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        //声明WebSettings子类
        val webSettings = mWebView.settings
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.javaScriptEnabled = true
        //设置自适应屏幕，两者合用
//        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
//        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小
        webSettings.displayZoomControls = true //隐藏原生的缩放控件
        //其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭webview中缓存
        webSettings.allowFileAccess = true //设置可以访问文件
        webSettings.defaultTextEncodingName = "UTF -8"
        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true //支持自动加载图片
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mWebView.goBack()
            if (mWebView.canGoBack()) {
                mWebView.goBack()
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        jsoupBookContentManager.onDestroy()
        //清空所有Cookie
        CookieSyncManager.createInstance(this)  //Create a singleton CookieSyncManager within a context
        val cookieManager = CookieManager.getInstance() // the singleton CookieManager instance
        cookieManager.removeAllCookie()// Removes all cookies.
        CookieSyncManager.getInstance().sync() // forces sync manager to sync now
        mWebView.webChromeClient = null
        mWebView.webViewClient = null
        mWebView.settings.javaScriptEnabled = false
        mWebView.clearCache(true)
    }
}