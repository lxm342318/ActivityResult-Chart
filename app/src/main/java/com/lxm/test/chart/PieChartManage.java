package com.lxm.test.chart;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.lxm.test.MyApplication;
import com.lxm.test.R;
import java.util.ArrayList;

/**
 * 饼状图
 *
 *
 */
public class PieChartManage {

    private PieChart pieChart;

    public PieChartManage(PieChart pieChart) {
        this.pieChart = pieChart;
    }

    /**
     * 初始化
     */
    private void initPicChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        //图表偏移量
        pieChart.setExtraOffsets(0, 0, 0, 0);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        //是否显示绘图条目标签（是否显示图表百分比值对应的类型）
        pieChart.setDrawEntryLabels(false);
        //是否开启拉孔（实心/空心）
        pieChart.setDrawHoleEnabled(true);
        //取消右下角描述
        pieChart.getDescription().setEnabled(false);

        //pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null) {
                    return;
                }
                Log.i("VAL SELECTED",
                        "Value: " + e.getY() + ", index: " + h.getX()
                                + ", DataSet index: " + h.getDataSetIndex());
            }

            @Override
            public void onNothingSelected() {

            }
        });


        pieChart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(5f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        pieChart.setEntryLabelColor(Color.WHITE);
        //pieChart.setEntryLabelTypeface(tfRegular);
        pieChart.setEntryLabelTextSize(12f);

    }

    /**
     * 显示图表
     */
    public void showPicChart(ArrayList<Float> range, ArrayList<String> parties, int total) {
        initPicChart();
        setData(range, parties, total);
    }

    /**
     * 更新图表
     *
     * @param range   百分比集合
     * @param parties 类型集合
     * @param total   总数
     */
    public void setData(ArrayList<Float> range, ArrayList<String> parties, int total) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        Float[] ranges = new Float[range.size()];
        String[] partiess = new String[parties.size()];
        for (int i = 0; i < range.size(); i++) {
            ranges[i] = range.get(i);
            partiess[i] = parties.get(i);
        }
        for (int i = 0; i < ranges.length - 1; i++) {
            for (int j = 0; j < ranges.length - 1 - i; j++) {
                if (ranges[j] < ranges[j + 1]) {
                    Float temp1 = ranges[j];
                    String temp2 = partiess[j];
                    ranges[j] = ranges[j + 1];
                    ranges[j + 1] = temp1;
                    partiess[j] = partiess[j + 1];
                    partiess[j + 1] = temp2;
                }
            }
        }

        for (int k = 0; k < range.size(); k++) {
            entries.add(new PieEntry(ranges[k],
                    partiess[k % partiess.length]));

        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        //是否绘制图表条目（是否显示图表百分比值）
        dataSet.setDrawValues(false);
        //是否在图上显示数值
        dataSet.setDrawValues(false);

        //图表空白间隔
        dataSet.setSliceSpace(1.5f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#2694FF"));
        colors.add(Color.parseColor("#FDAA2E"));
        colors.add(Color.parseColor("#6D4F2E"));
        colors.add(Color.parseColor("#FFC0CB"));
        colors.add(Color.parseColor("#00FFFF"));
        colors.add(Color.parseColor("#FF8C00"));
        colors.add(Color.parseColor("#FF0000"));
        colors.add(Color.parseColor("#696969"));
        colors.add(Color.parseColor("#FFD700"));
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
        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);
        pieChart.setCenterTextSize(12f);
        pieChart.setCenterText(total + MyApplication.application.getString(R.string.str_total));

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }

    /**
     * 设置图表内文本
     *
     * @return
     */
    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

}
