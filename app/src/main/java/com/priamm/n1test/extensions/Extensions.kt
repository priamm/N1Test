package com.priamm.n1test.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun Disposable.add(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}
