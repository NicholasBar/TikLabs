package assignm.nicholasbar.tikstreamlabstok.util.view

import android.animation.TimeAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import java.util.*

class CircleRippleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val speedDpPerSec = 100
    private var mBaseSpeed: Float = 0.toFloat()
    private val ripples = HashSet<Ripple>()
    private val numbers = HashSet<FloatingNum>()
    var mTimeAnimator: TimeAnimator? = null
    var mCurrentPlayTime: Long = 0
    private val paint = Paint()
    var centerX: Float = 0.0f
    var centerY: Float = 0.0f

    init{
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        mBaseSpeed = speedDpPerSec * resources.displayMetrics.density
    }

    /**
     * Class representing the state of a ripple
     */
    class Ripple {
        var radius: Float = 0.toFloat()
        var alpha: Int = 255
    }

    /**
     * Class representing the state of a floating number
     */
    class FloatingNum constructor(newValue: Int,startX: Float,startY: Float){
        var value: Int  = newValue
        var posX = startX
        var posY = startY
        var alpha: Int = 255
        var speed: Float = 3f
        val red: Int = (0..255).random()
        val green: Int = (0..255).random()
        val blue: Int = (0..255).random()
    }

    fun addRipple(){
        ripples.add(Ripple())
    }

    fun addNumber(newValue: Int){
        val newNumber = FloatingNum(newValue,centerX,centerY)
        newNumber.posY -= 70
        //Give random X offset
        newNumber.posX += (-80..60).random() - 40
        numbers.add(newNumber)
    }

    override fun onDraw(canvas: Canvas) {
        for (ripple in ripples) {
            val color = Color.argb(ripple.alpha,255,0,102)
            paint.color = color
            canvas.drawCircle(centerX, centerY, ripple.radius, paint)
        }
        for (number in numbers) {
            val color = Color.argb(number.alpha,number.red,number.blue,number.green)
            paint.color = color
            //Put a max size on text growth
            if(number.value < 100){
                paint.textSize = (number.value * 1.5f) + 60f
            }else{
                paint.textSize = (100 * 1.5f) + 60f
            }
            canvas.drawText( "+" + number.value.toString(), number.posX, number.posY, paint)
        }
    }

    fun setCenter(x: Float, y: Float) {
        centerX = x
        centerY = y
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mTimeAnimator = TimeAnimator()
        mTimeAnimator!!.setTimeListener(TimeAnimator.TimeListener { animation, totalTime, deltaTime ->
            if (!isLaidOut) {
                // Ignore all calls before the view has been measured and laid out.
                return@TimeListener
            }
            updateState(deltaTime.toFloat())
            invalidate()
        })
        mTimeAnimator!!.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mTimeAnimator!!.cancel()
        mTimeAnimator!!.setTimeListener(null)
        mTimeAnimator!!.removeAllListeners()
        mTimeAnimator = null
    }

    /**
     * Pause the animation if it's running
     */
    fun pause() {
        if (mTimeAnimator != null && mTimeAnimator!!.isRunning) {
            // Store the current play time for later.
            mCurrentPlayTime = mTimeAnimator!!.currentPlayTime
            mTimeAnimator?.pause()
        }
    }

    /**
     * Resume the animation if not already running
     */
    fun resume() {
        if (mTimeAnimator != null && mTimeAnimator!!.isPaused) {
            mTimeAnimator?.start()
            // TimeAnimator uses timestamps internally to determine the delta given
            // in the TimeListener. When resumed, the next delta received will the whole
            // pause duration, which might cause a huge jank in the animation.
            // By setting the current play time, it will pick of where it left off.
            mTimeAnimator!!.currentPlayTime = mCurrentPlayTime
        }
    }

    /**
     * Progress the animation by moving the ripples based on the elapsed time
     * @param deltaMs time delta since the last frame, in millis
     */
    private fun updateState(deltaMs: Float) {
        val deltaSeconds = deltaMs / 1000f

        for (ripple in ripples) {
            // Move the ripple based on the elapsed time and it's speed
            ripple.radius += 15f
            when(ripple.alpha){
                in 11..255 -> ripple.alpha -= 5
                in 1..10 -> ripple.alpha = 0
                else -> {
                    // TODO: Remove from set
                }
            }
        }
        for(number in numbers){
            // TODO: Use AccelerateInterpolator
            number.posY -=  number.speed
            number.speed = number.speed * 1.1f
            when(number.alpha){
                in 11..255 -> number.alpha -= 6
                in 1..3 -> number.alpha = 0
                else -> {
                    // TODO: Remove from set
                }
            }
        }
    }

}