package ru.netology.nmedia.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import ru.netology.nmedia.R
import ru.netology.nmedia.util.AndroidUtils
import kotlin.math.min
import kotlin.random.Random

class StatsView @JvmOverloads constructor(
    context: Context,
    attributesSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(
    context,
    attributesSet,
    defStyleAttr,
    defStyleRes
) {

    private var textSize = AndroidUtils.dp(context, 20).toFloat()
    private var lineWith = AndroidUtils.dp(context, 5)
    private var colors = emptyList<Int>()

    init {
        context.withStyledAttributes(attributesSet, R.styleable.StatsView) {
            textSize = getDimension(R.styleable.StatsView_textSize, textSize)
            lineWith = getDimension(R.styleable.StatsView_lineWidth, lineWith.toFloat()).toInt()
            colors = listOf(
                getColor(R.styleable.StatsView_color1, generateRandomColor()),
                getColor(R.styleable.StatsView_color2, generateRandomColor()),
                getColor(R.styleable.StatsView_color3, generateRandomColor()),
                getColor(R.styleable.StatsView_color4, generateRandomColor()),
            )

        }
    }

    var data: List<Float> = listOf(
        500F,
        500F,
        500F,
        500F
    )
        set(value) {
            field = value
            invalidate()
        }
    private var radius = 0F
    private var center = PointF(0F, 0F)
    private var oval = RectF(0F, 0F, 0F, 0F)

    private val paint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        strokeWidth = lineWith.toFloat()
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }
    private val textPaint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        textSize = this@StatsView.textSize
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(w, h) / 2F - lineWith / 2
        center = PointF(w / 2F, h / 2F)
        oval = RectF(
            center.x - radius,
            center.y - radius,
            center.x + radius,
            center.y + radius
        )
    }

    override fun onDraw(canvas: Canvas?) {
        if (data.isEmpty()) {
            return
        }
        var startAngle = -90F
        data.forEachIndexed { index, datum ->
            val angle = 360F * datum / data.sum()
            paint.color = colors.getOrElse(index) { generateRandomColor() }
            canvas?.drawArc(oval, startAngle, angle, false, paint)
            startAngle += angle
        }

        paint.color = colors.getOrNull(0) ?: generateRandomColor()
        canvas?.drawArc(oval, -90F, 1F, false, paint)
        canvas?.drawText(
            "%.2f%%".format(100F),
            center.x,
            center.y + textPaint.textSize / 4,
            textPaint
        )
    }

    private fun generateRandomColor() = Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
}