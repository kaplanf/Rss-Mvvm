package com.kaplan.rssmvvm.binding

import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }
}

@BindingAdapter("webviewFromuRL")
fun bindWebviewFromUrl(webview: WebView, link: String?) {
  if (!link.isNullOrEmpty()) {
    webview.settings.javaScriptEnabled = true
    webview.loadUrl(link)
    webview.webViewClient = object : WebViewClient() {
      override fun shouldOverrideUrlLoading(view: WebView,
                                            request: WebResourceRequest): Boolean {
        view.loadUrl(request.url.toString())
        return false
      }
    }
  }
}


