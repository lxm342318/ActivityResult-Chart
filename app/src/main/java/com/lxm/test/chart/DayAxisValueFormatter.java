package com.lxm.test.chart;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

/**
 *
 */
public class DayAxisValueFormatter extends ValueFormatter {

    private final String[] formatValues;

    private final BarLineChartBase<?> chart;

    DayAxisValueFormatter(BarLineChartBase<?> chart, String[] formatValues) {
        this.chart = chart;
        this.formatValues = formatValues;
    }

    @Override
    public String getFormattedValue(float value) {
        if (value >= formatValues.length) {
            return "";
        } else {
            return formatValues[(int) (value)];
        }
    }

}
