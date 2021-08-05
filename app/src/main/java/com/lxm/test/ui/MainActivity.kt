package com.lxm.test.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.Observer
import com.github.mikephil.charting.charts.LineChart
import com.lxm.test.base.BaseActivity
import com.lxm.test.chart.LineChartManage
import com.lxm.test.contract.ActivityContract
import com.lxm.test.R
import java.util.concurrent.CopyOnWriteArrayList
import butterknife.BindView
import butterknife.OnClick
import com.lxm.test.utils.ConnectionLiveData
import com.lxm.test.utils.ToastCompat

class MainActivity : BaseActivity() {

    @BindView(R.id.line_chart)
    private var  lineChart : LineChart ? = null
    @BindView(R.id.btn_main)
    private var  btnMain :TextView ? = null
    private var  launcher : ActivityResultLauncher<String> ? = null
    private var  lineChartManage: LineChartManage ? = null
    private var  list : MutableList<Int> ? = null
    val connection = ConnectionLiveData(context = this@MainActivity)


    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        btnMain?.text = getString(R.string.btn_jump)
        list = CopyOnWriteArrayList()
        showLineChart()
        launcher = registerForActivityResult(ActivityContract()) {
           ToastCompat.show(it)
        }
        connection.observe(this, {
            //todo 网络状态变化
        })
    }

    /**
     * 显示图表
     *
     * @param
     */
    private fun showLineChart() {
        for (index in 0 until 10){
            list?.add(index, (Math.random()*9+1).toInt())
        }
        if (lineChartManage == null)
            lineChartManage = LineChartManage(lineChart)
        lineChart?.visibility = View.VISIBLE
        list?.let { lineChartManage?.showLineChart(it, this) }
    }

    @OnClick(R.id.btn_main)
    fun onClickView(view : View ?) {
        when (view?.id) {
             R.id.btn_main ->{
                 launcher?.launch("MainActivity")
             }
        }
    }
}