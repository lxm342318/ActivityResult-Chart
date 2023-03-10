package com.lxm.test.chart

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.lxm.test.MyAlcImpl
import com.lxm.test.MyApplication
import com.lxm.test.R
import kotlin.collections.ArrayList

/**
 * @author : lxm
 * @package_name ：com.lxm.test.chart
 * @date : 2021/6/25 17:26
 * @description ：
 */
class PieChartManage(pieChart: PieChart?) {

    private var pieChart: PieChart? = null

    init {
       this.pieChart = pieChart
    }

    /**
     * 初始化
     */
    private fun initPicChart() {
        pieChart?.setUsePercentValues(true)
        pieChart?.description?.isEnabled = false
        //图表偏移量
        pieChart?.setExtraOffsets(0f, 0f, 0f, 0f)
        pieChart?.dragDecelerationFrictionCoef = 0.95f

        //是否显示绘图条目标签（是否显示图表百分比值对应的类型）
        pieChart?.setDrawEntryLabels(false)
        //是否开启拉孔（实心/空心）
        pieChart?.isDrawHoleEnabled = true
        //取消右下角描述
        pieChart?.description?.isEnabled = false

        //pieChart.setCenterText(generateCenterSpannableText());
        pieChart?.isDrawHoleEnabled = true
        pieChart?.setHoleColor(Color.WHITE)
        pieChart?.setTransparentCircleColor(Color.WHITE)
        pieChart?.setTransparentCircleAlpha(110)
        pieChart?.holeRadius = 58f
        pieChart?.transparentCircleRadius = 61f
        pieChart?.setDrawCenterText(true)
        pieChart?.rotationAngle = 0f
        // enable rotation of the chart by touch
        pieChart?.isRotationEnabled = true
        pieChart?.isHighlightPerTapEnabled = true

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        pieChart?.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight) {
                Log.i("VAL SELECTED",
                        "Value: " + e.y + ", index: " + h.x
                                + ", DataSet index: " + h.dataSetIndex)
            }

            override fun onNothingSelected() {}
        })
        pieChart?.animateY(1400, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);
        val legend = pieChart?.legend
        legend?.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend?.orientation = Legend.LegendOrientation.VERTICAL
        legend?.setDrawInside(false)
        legend?.xEntrySpace = 5f
        legend?.yEntrySpace = 0f
        legend?.yOffset = 0f

        // entry label styling
        pieChart?.setEntryLabelColor(Color.WHITE)
        //pieChart.setEntryLabelTypeface(tfRegular);
        pieChart?.setEntryLabelTextSize(12f)
    }

    /**
     * 显示图表
     */
    fun showPicChart(range: MutableList<Float>, parties: MutableList<String>, total: Int) {
        initPicChart()
        setData(range, parties, total)
    }

    /**
     * 更新图表
     *
     * @param range   百分比集合
     * @param parties 类型集合
     * @param total   总数
     */
    fun setData(range: MutableList<Float>, parties: MutableList<String>, total: Int) {
        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        val ranges  = ArrayList<Float>(range.size)

        val partiess  = ArrayList<String>(parties.size)
        for (i in range.indices) {
            ranges[i] = range[i]
            partiess[i] = parties[i]
        }
        for (i in 0 until ranges.size - 1) {
            for (j in 0 until ranges.size - 1 - i) {
                if (ranges[j] < ranges[j + 1]) {
                    val temp1 = ranges[j]
                    val temp2 = partiess[j]
                    ranges[j] = ranges[j + 1]
                    ranges[j + 1] = temp1
                    partiess[j] = partiess[j + 1]
                    partiess[j + 1] = temp2
                }
            }
        }
        for (k in range.indices) {
            entries.add(PieEntry(ranges[k],
                    partiess[k % partiess.size]))
        }
        val dataSet = PieDataSet(entries, "")
        dataSet.setDrawIcons(false)

        //是否绘制图表条目（是否显示图表百分比值）
        dataSet.setDrawValues(false)
        //是否在图上显示数值
        dataSet.setDrawValues(false)

        //图表空白间隔
        dataSet.sliceSpace = 1.5f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#2694FF"))
        colors.add(Color.parseColor("#FDAA2E"))
        colors.add(Color.parseColor("#6D4F2E"))
        colors.add(Color.parseColor("#FFC0CB"))
        colors.add(Color.parseColor("#00FFFF"))
        colors.add(Color.parseColor("#FF8C00"))
        colors.add(Color.parseColor("#FF0000"))
        colors.add(Color.parseColor("#696969"))
        colors.add(Color.parseColor("#FFD700"))
        //        colors.add(Color.parseColor("#274e13"));
//        for (int c : ColorTemplate.COLORFUL_COLORS) {
//            colors.add(c);
//        }
//        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
//            colors.add(c);
//        }
//
//        for (int c : ColorTemplate.JOYFUL_COLORS) {
//            colors.add(c);
//        }
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.WHITE)
        pieChart?.data = data
        pieChart?.setCenterTextSize(12f)
        pieChart?.centerText = String.format(MyAlcImpl.getApplication().getString(R.string.str_total) , total )

        // undo all highlights
        pieChart?.highlightValues(null)
        pieChart?.invalidate()
    }

    /**
     * 设置图表内文本
     *
     * @return
     */
    private fun generateCenterSpannableText(): SpannableString? {
        val s = SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.8f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }


}