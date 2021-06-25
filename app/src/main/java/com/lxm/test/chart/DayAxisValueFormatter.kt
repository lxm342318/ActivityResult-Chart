package com.lxm.test.chart

import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.formatter.ValueFormatter

/**
 * @author : lxm
 * @package_name ：com.lxm.test.chart
 * @date : 2021/6/25 16:39
 * @description ：
 */
class DayAxisValueFormatter(chart: BarLineChartBase<*>?, formatValues: Array<String>?)  : ValueFormatter() {

    private var formatValues: Array<String>? = null
    private var chart: BarLineChartBase<*>? = null

     init {
         this.formatValues =  formatValues
         this.chart = chart
     }


    override fun getFormattedValue(value: Float): String {

        return if(formatValues!=null){
            if (value >= formatValues!!.size) {
                ""
            } else {
                formatValues!![value.toInt()]
            }
        }else{
            super.getFormattedValue(value)
        }

    }

}