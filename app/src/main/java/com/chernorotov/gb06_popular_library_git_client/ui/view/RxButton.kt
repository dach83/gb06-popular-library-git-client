package com.chernorotov.gb06_popular_library_git_client.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import io.reactivex.rxjava3.core.Observable

class RxButton : AppCompatButton {

    constructor(
        context: Context
    ) : super(context)

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)


    val clicks: Observable<View> = Observable.create { emitter ->
        setOnClickListener { view ->
            emitter.onNext(view)
        }
    }

}
