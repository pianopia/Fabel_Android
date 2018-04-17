package jp.co.museit.fabel

import android.annotation.TargetApi
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import jp.co.museit.fabel.FabelFlyItemToPoint

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class MainActivity : AppCompatActivity() {

    // ボタンの配置と重なり順序の設定
    var button: Button? = null
    var button2: Button? = null
    var drawable: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawable = getDrawable(R.drawable.fabel_icon)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setButtonClickListener()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        // タブの切り替えによってButtonの位置を変更
        when (item.itemId) {
            R.id.navigation_home -> {
                button!!.x = 120F
                button!!.y = 120F

                button2!!.x = 750F
                button2!!.y = 960F

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                button!!.x = 120F
                button!!.y = 400F

                button2!!.x = 750F
                button2!!.y = 900F
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                button!!.x = 120F
                button!!.y = 900F

                button2!!.x = 750F
                button2!!.y = 200F
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun setButtonClickListener() {
        // Fabel Managerの作成
        val flyItemToPoint = FabelFlyItemToPoint()

        button = findViewById(jp.co.museit.fabel.R.id.button2) as Button
        button2 = findViewById(R.id.button3) as Button

        button?.translationZ = 1F
        button2?.translationZ = 1F

        // Buttonのクリック
        button?.setOnClickListener {

            var x = button!!.getX()
            var y = button!!.getY()
            var startPos: Array<Float> = arrayOf(x, y)
            var endPos: Array<Float> = getNavigationItemPosition()

            flyItemToPoint.flyItemToPoint(startPos, endPos, createFlyItem(), findViewById<FrameLayout>(R.id.frame_layout))

            //var navigation = (R.id.navigation) as NavigationView
        }

        // Button2のクリック
        button2?.setOnClickListener {

            var x = button2!!.getX()
            var y = button2!!.getY()
            var startPos: Array<Float> = arrayOf(x, y)
            var endPos: Array<Float> = getNavigationItemPosition()

            flyItemToPoint.flyItemToPoint(startPos, endPos, createFlyItem(), findViewById<FrameLayout>(R.id.frame_layout))

        }
    }

    fun getNavigationItemPosition(): Array<Float> {
        val items = ArrayList<MenuItem>()
        var navigationView = findViewById(R.id.navigation) as BottomNavigationView
        return arrayOf(navigationView.x, navigationView.y)
    }

    fun createFlyItem(): ImageView {
        // Itemのサイズと重なり順序の設定
        val layout = findViewById<FrameLayout>(R.id.frame_layout)
        val flyItem = ImageView(this)
        flyItem.setImageDrawable(drawable)
        flyItem.layoutParams = FrameLayout.LayoutParams(50,50)
        flyItem.translationZ = 2F
        return flyItem
    }
}
