package com.lxm.test.chart;

import android.content.Context;
import android.graphics.Color;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.lxm.test.HT;
import com.lxm.test.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 折线图
 *
 *
 */
public class LineChartManage implements OnChartValueSelectedListener {

    private LineChart lineChart;

    public LineChartManage(LineChart lineChart) {
        this.lineChart = lineChart;
    }

    /**
     * 初始化
     */
    private void initLineChart() {
        lineChart.setOnChartValueSelectedListener(this);

        // no description text
        lineChart.getDescription().setEnabled(false);

        // enable touch gestures
        lineChart.setTouchEnabled(true);
        //是否可以缩放 仅x轴
        lineChart.setScaleXEnabled(true);

        lineChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);

        // set an alternative background color
        lineChart.setBackgroundColor(Color.WHITE);

        lineChart.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = lineChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        //折线提示文字颜色
        l.setTextColor(Color.RED);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setYOffset(5f);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.parseColor("#5A5A5A"));
        xAxis.setGridLineWidth(10f);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        String[] formatValues = new String[]{"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00"
                , "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"
                , "21:00", "22:00", "23:00", "24:00"};
        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(lineChart, formatValues);
        //设置自定义格式，在绘制之前动态调整x的值。
        xAxis.setValueFormatter(xAxisFormatter);
        //设置X轴的文字在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextColor(Color.parseColor("#5A5A5A"));
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        //设置y轴最小值为0
        leftAxis.setAxisMinimum(0);
        //leftAxis.setSpaceTop(20f);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setTextColor(Color.WHITE);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);

    }

    /**
     * 设置图表数据
     */
    public void setData(@NotNull List<Integer> valueList, Context context) {
        ArrayList<Entry> values1 = new ArrayList<>();

        int count = valueList.size();
        for (int i = 0; i < count; i++) {
            values1.add(new Entry(i, valueList.get(i)));
        }

        LineDataSet set1=null;

//        if (lineChart.getData() != null &&
//                lineChart.getData().getDataSetCount() > 0) {
//            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
//            set1.setValues(values1);
//            lineChart.setData();
//            lineChart.getData().notifyDataChanged();
//            lineChart.notifyDataSetChanged();
//        } else {
        // create a dataset and give it a type

            set1 = new LineDataSet(values1, context.getString(R.string.str_device_working_num));


        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        //折现颜色
        set1.setColor(Color.RED);
        //折点颜色
        set1.setCircleColor(Color.RED);
        set1.setLineWidth(2f);
        set1.setCircleRadius(3f);
        set1.setFillAlpha(65);
        set1.setFillColor(ContextCompat.getColor(HT.get().getApplication(), R.color.colorPrimary));
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        //这点是否绘制圆孔
        set1.setDrawCircleHole(true);
        //set1.setFillFormatter(new MyFillFormatter(0f));
        //set1.setDrawHorizontalHighlightIndicator(false);
        //set1.setVisible(false);
//            set1.setCircleHoleColor(Color.WHITE);

        // create a data object with the data sets(可绘制多条 折线数据)
        LineData data = new LineData(set1);
        //折线图值文字颜色
        data.setValueTextColor(Color.parseColor("#5A5A5A"));
        data.setValueTextSize(9f);

        // set data
        lineChart.setData(data);

        // undo all highlights
        lineChart.highlightValues(null);

        // 当前统计图表中最多在x轴坐标线上显示的总量
        lineChart.setVisibleXRangeMaximum(12);
        lineChart.moveViewToX(valueList.size() - 1);

        lineChart.invalidate();
//        }
    }

    /**
     * 显示图表
     */
    public void showLineChart(List<Integer> valueList,Context context) {
        initLineChart();
        setData(valueList,context);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

}
