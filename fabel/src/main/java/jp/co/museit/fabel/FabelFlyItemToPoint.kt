package jp.co.museit.fabel

import android.provider.Contacts
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import com.reyurnible.rxanimation.animation.AnimationEvent
import com.reyurnible.rxanimation.animation.RxAnimation
import com.reyurnible.rxanimation_kotlin.animation.events
import rx.Subscriber


/**
 * Created by shota.nakagawa on 2018/04/02.
 */
class FabelFlyItemToPoint() {

    open fun flyItemToPoint(startPos: Array<Float>, endPos: Array<Float>, item: View, frameLayout: FrameLayout) {

        // Viewを表示する場所を指定
        //var params = FrameLayout.LayoutParams(100,100)
        //params.leftMargin = 50;
        //params.topMargin = 100;

        // Layoutにitemを追加してアニメーション
        if (item.parent != null) {
            frameLayout.removeView(item)
            frameLayout.addView(item)
            item.x = startPos[0]
            item.y = startPos[1]

            val TAG = "tag"
            val animation: Animation = TranslateAnimation(item.x,item.y,endPos[0],endPos[1])
            animation.duration = 2000
            animation.fillAfter = true
            item.startAnimation(animation)
            Log.d(TAG, item.x.toString() + item.y.toString())
        } else {
            frameLayout.addView(item)
            item.x = startPos[0]
            item.y = startPos[1]

            val TAG = "tag"
            val animation: Animation = TranslateAnimation(item.x,item.y,endPos[0],endPos[1])
            animation.duration = 2000
            animation.fillAfter = true
            item.startAnimation(animation)
            Log.d(TAG, item.x.toString() + item.y.toString())
        }


    }

}