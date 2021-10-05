package com.example.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs
import kotlin.math.min

class Joystick @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var bigR: Float = 0f
    private var bigC: PointF = PointF()
    private var smallR: Float = 0f
    private var smallC: PointF = PointF()
    var aileron:  Float = 0f
    var elevator: Float = 0f
    lateinit var onChange: (Float, Float) -> Unit

    private val paintBigBall = Paint().apply {
        color = Color.GRAY
    }

    private val paintSmallBall = Paint().apply {
        color = Color.BLACK
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        smallR = 0.07f * min(w, h)
        smallC = PointF(width.toFloat() / 2.0f, height.toFloat() / 2.0f)
        bigR = 0.2f * min(w, h)
        bigC = PointF(width.toFloat() / 2.0f, height.toFloat() / 2.0f)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(bigC.x, bigC.y, bigR, paintBigBall)
        canvas.drawCircle(smallC.x, smallC.y, smallR, paintSmallBall)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_MOVE && (bigR >= abs(event.x - bigC.x))
            && (bigR >= abs(event.y - bigC.y))) {
            smallC.x = event.x;
            smallC.y = event.y;
            aileron = (event.x - bigC.x) / (bigR)
            elevator = (event.y - bigC.y) / (bigR)
            try{
                onChange(aileron,elevator)
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
        if(event?.action == MotionEvent.ACTION_MOVE) {
            invalidate()
        }
        if(event?.action == MotionEvent.ACTION_UP){
            smallC.x = bigC.x;
            smallC.y = bigC.y;
            invalidate()
        }
        return true
    }

}