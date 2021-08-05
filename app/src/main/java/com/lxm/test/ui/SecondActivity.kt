package com.lxm.test.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.github.mikephil.charting.charts.PieChart
import com.lxm.test.R
import com.lxm.test.base.BaseActivity
import java.util.ArrayList
import java.util.Random
import butterknife.BindView
import butterknife.OnClick
import com.lxm.test.NULL_CHARACTER
import com.lxm.test.chart.PieChartManage
import com.lxm.test.utils.ActivityManager
import com.lxm.test.utils.LogCompat.logI
import java.lang.Exception
import java.nio.charset.Charset
import kotlin.math.abs

/**
  * @Copyright (C), 2020
  * @Author: ym
  * @Date:
  * @Description:
  */
class SecondActivity : BaseActivity() {

    @BindView(R.id.btn_second)
    private var btnSecond:TextView ? = null
    @BindView(R.id.pie_chart)
    private var  pieChart : PieChart ? = null
    private var  pieChartManage : PieChartManage ? = null
    private var  list : MutableList<Float> = ArrayList()
    private var  sList : MutableList<String> = ArrayList()
    private val  total : Int = 100
    private var sum : Float = 0f


    override fun getLayoutResource(): Int {
        return R.layout.activity_second
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        btnSecond?.text = getString(R.string.btn_result)
        getRandomFloat();
        showPieChart(list);
    }

    private fun getRandomFloat() {
        val  random=Random()
        for (index in 0 until 10) {
            val temp = random.nextFloat() * 10
            list.add(index, temp)
            sList.add(index,
                    getRandomChar()+getRandomChar())
            sum += temp
            if(sum>100)
               break
        }
    }
    /**
      * @Description: 生成汉字
      */
    private fun getRandomChar() : String{

        var str = NULL_CHARACTER
        val random = Random()
        val heightPos = (176 + abs(random.nextInt(39)))
        val lowPos = (161 + abs(random.nextInt(93)))

        val b = ByteArray(2)
        b[0] = heightPos.toByte()
        b[1] = lowPos.toByte()

        try {
            str = String(b, Charset.forName("GBK"))
        } catch ( e : Exception) {
            e.printStackTrace()
            ("生成汉字错误").logI()
        }

        return str;
    }

    private fun showPieChart( list :MutableList<Float>) {
        if (pieChartManage == null)
            pieChartManage = PieChartManage(pieChart)
         pieChartManage?.showPicChart(list , sList , total)

    }

    @OnClick(R.id.btn_second)
    fun onClickView(view : View?) {
        when (view?.id){
             R.id.btn_second ->{
                 setResult(RESULT_OK,  Intent().putExtra("data",
                         ActivityManager.currentActivity?.javaClass?.simpleName))
                 finish()
             }
        }
    }


}