package com.lxm.test.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.lxm.test.utils.DisplayManager

/**
 * @Copyright (C), 2020
 * @Author: ym
 * @Date:
 * @Description: 旋转圆弧进度条
 */
class RotateCircleProgressBar : View{

    /**
     * View宽高
     */
    private var mWidth = 0
    private var mHeight = 0

    /**
     * 画笔
     */
    private lateinit var mCircleBorderPaint: Paint
    private lateinit var mArcPaint: Paint

    /**
     * 外圆颜色
     */
    private val mCircleColor = Color.parseColor("#4DEFEFF0")

    /**
     * 弧线颜色
     */
    private val mArcColor = Color.parseColor("#FFFFFF")

    /**
     * 中心点X、Y坐标
     */
    private var mCenterX = 0
    private var mCenterY = 0

    /**
     * 圆半径
     */
    private var mRadius = 0f

    /**
     * 旋转角度
     */
    private var mAngle = 0f

    /**
     * 绘制范围
     */
    private lateinit var mRect: RectF

    constructor(context: Context) : super(context)

    constructor(
            context: Context,
            attrs: AttributeSet?
    ) : super(context, attrs) {
        init()
    }

    constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init(){
        //外圆画笔
        mCircleBorderPaint = Paint()
        mCircleBorderPaint.style = Paint.Style.STROKE
        mCircleBorderPaint.color = mCircleColor
        mCircleBorderPaint.strokeWidth = DisplayManager.dip2px(4.0).toFloat()
        mCircleBorderPaint.isAntiAlias = true
        //弧线画笔
        mArcPaint = Paint()
        mArcPaint.style = Paint.Style.STROKE
        mArcPaint.color = mArcColor
        mArcPaint.strokeWidth = DisplayManager.dip2px(3.5).toFloat()
        mArcPaint.isAntiAlias = true
        //设置笔触为圆形
        mArcPaint.strokeCap = Paint.Cap.ROUND

        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.interpolator = LinearInterpolator()
        animator.duration = 600
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.RESTART
        animator.addUpdateListener { animation: ValueAnimator ->
            //更新旋转角度
            mAngle = animation.animatedValue as Float
            postInvalidate()
        }
        animator.start()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        //取出padding值
        val paddingLeft = paddingLeft
        val paddingRight = paddingRight
        val paddingTop = paddingTop
        val paddingBottom = paddingBottom
        //绘制范围
        mRect = RectF()
        mRect.left = paddingLeft.toFloat()
        mRect.top = paddingTop.toFloat()
        mRect.right = mWidth.toFloat() - paddingRight
        mRect.bottom = mHeight.toFloat() - paddingBottom
        val diameter = (mWidth.coerceAtMost(mHeight) - paddingLeft - paddingRight).toFloat()
        mRadius = (diameter / 2 * 0.98).toFloat()
        //计算圆心的坐标
        mCenterX = mWidth / 2
        mCenterY = mHeight / 2
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureSpec(widthMeasureSpec), measureSpec(heightMeasureSpec))
    }

    private fun measureSpec(measureSpec: Int): Int {

        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        //默认大小
        val defaultSize = DisplayManager.dip2px(30.0)
        //指定宽高则直接返回
        return when(mode){
            MeasureSpec.EXACTLY -> {
                size
            }
            MeasureSpec.AT_MOST -> {
                //wrap_content的情况
                defaultSize.coerceAtMost(size)
            }
            else ->{
                defaultSize
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.scale(0.87f, 0.87f, mCenterX.toFloat(), mCenterY.toFloat())
        //让画布旋转指定角度
        canvas?.rotate(mAngle, mCenterX.toFloat(), mCenterY.toFloat())
        //画外圆
        canvas?.drawCircle(mCenterX.toFloat(), mCenterY.toFloat(), mRadius, mCircleBorderPaint)
        //画弧线
        canvas?.drawArc(mRect, -90f, 200f, false, mArcPaint)
    }

}