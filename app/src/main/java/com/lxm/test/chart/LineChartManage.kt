package com.lxm.test.chart

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.lxm.test.MyAlcImpl
import com.lxm.test.MyApplication
import com.lxm.test.R
import java.util.*

/**
 * @author : lxm
 * @package_name ：com.lxm.test.chart
 * @date : 2021/6/25 18:11
 * @description ：
 */
class LineChartManage(lineChart: LineChart?) : OnChartValueSelectedListener {

    private var lineChart: LineChart? = null

    init {
        this.lineChart = lineChart
    }

    /**
     * 初始化
     */
    private fun initLineChart() {
        lineChart?.setOnChartValueSelectedListener(this)

        // no description text
        lineChart?.description?.isEnabled = false

        // enable touch gestures
        lineChart?.setTouchEnabled(true)
        //是否可以缩放 仅x轴
        lineChart?.isScaleXEnabled = true
        lineChart?.dragDecelerationFrictionCoef = 0.9f

        // enable scaling and dragging
        lineChart?.isDragEnabled = true
        lineChart?.setScaleEnabled(false)
        lineChart?.setDrawGridBackground(false)
        lineChart?.isHighlightPerDragEnabled = true

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart?.setPinchZoom(false)

        // set an alternative background color
        lineChart?.setBackgroundColor(Color.WHITE)
        lineChart?.animateX(1500)

        // get the legend (only possible after setting data)
        val legend = lineChart?.legend

        // modify the legend ...
        legend?.form = Legend.LegendForm.LINE
        legend?.textSize = 11f
        //折线提示文字颜色
        legend?.textColor = Color.RED
        legend?.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend?.orientation = Legend.LegendOrientation.HORIZONTAL
        legend?.setDrawInside(false)
        legend?.yOffset = 5f
        val xAxis = lineChart?.xAxis
        xAxis?.textSize = 11f
        xAxis?.textColor = Color.parseColor("#5A5A5A")
        xAxis?.gridLineWidth = 10f
        xAxis?.setDrawGridLines(false)
        xAxis?.setDrawAxisLine(false)
        val formatValues = arrayOf("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00",
                "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00")
        val xAxisFormatter: ValueFormatter = DayAxisValueFormatter(lineChart, formatValues)
        //设置自定义格式，在绘制之前动态调整x的值。
        xAxis?.valueFormatter = xAxisFormatter
        //设置X轴的文字在底部
        xAxis?.position = XAxis.XAxisPosition.BOTTOM
        val leftAxis = lineChart?.axisLeft
        leftAxis?.textColor = Color.parseColor("#5A5A5A")
        leftAxis?.setDrawAxisLine(false)
        leftAxis?.setDrawGridLines(true)
        leftAxis?.isGranularityEnabled = true
        //设置y轴最小值为0
        leftAxis?.axisMinimum = 0f
        //leftAxis.setSpaceTop(20f);
        val rightAxis = lineChart?.axisRight
        rightAxis?.textColor = Color.WHITE
        rightAxis?.setDrawAxisLine(false)
        rightAxis?.setDrawGridLines(false)
        rightAxis?.setDrawZeroLine(false)
        rightAxis?.isGranularityEnabled = false
    }

    /**
     * 设置图表数据
     */
    fun setData(valueList: MutableList<Int>, context: Context) {
        val values1 = ArrayList<Entry>()
        val count = valueList.size
        for (i in 0 until count) {
            values1.add(Entry(i.toFloat(), valueList[i].toFloat()))
        }
        val set1: LineDataSet

//        if (lineChart.getData() != null &&
//                lineChart.getData().getDataSetCount() > 0) {
//            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
//            set1.setValues(values1);
//            lineChart.setData();
//            lineChart.getData().notifyDataChanged();
//            lineChart.notifyDataSetChanged();
//        } else {
        // create a dataset and give it a type
        set1 = LineDataSet(values1, context.getString(R.string.str_device_working_num))
        set1.axisDependency = YAxis.AxisDependency.LEFT
        //折现颜色
        set1.color = Color.RED
        //折点颜色
        set1.setCircleColor(Color.RED)
        set1.lineWidth = 2f
        set1.circleRadius = 3f
        set1.fillAlpha = 65
        set1.fillColor = ContextCompat.getColor(MyAlcImpl.getApplication(), R.color.colorPrimary)
        set1.highLightColor = Color.rgb(244, 117, 117)
        //这点是否绘制圆孔
        set1.setDrawCircleHole(true)
        //set1.setFillFormatter(new MyFillFormatter(0f));
        //set1.setDrawHorizontalHighlightIndicator(false);
        //set1.setVisible(false);
//            set1.setCircleHoleColor(Color.WHITE);

        // create a data object with the data sets(可绘制多条 折线数据)
        val data = LineData(set1)
        //折线图值文字颜色
        data.setValueTextColor(Color.parseColor("#5A5A5A"))
        data.setValueTextSize(9f)

        // set data
        lineChart?.data = data

        // undo all highlights
        lineChart?.highlightValues(null)

        // 当前统计图表中最多在x轴坐标线上显示的总量
        lineChart?.setVisibleXRangeMaximum(12f)
        lineChart?.moveViewToX((valueList.size - 1).toFloat())
        lineChart?.invalidate()
//        }
    }

    /**
     * 显示图表
     */
    fun showLineChart(valueList: MutableList<Int>, context: Context) {
        initLineChart()
        setData(valueList, context)
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }

    override fun onNothingSelected() {
    }



}