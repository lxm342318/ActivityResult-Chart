package com.lxm.test.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import com.github.mikephil.charting.charts.LineChart;
import com.lxm.test.T;
import com.lxm.test.base.BaseActivity;
import com.lxm.test.chart.LineChartManage;
import com.lxm.test.contract.ActivityContract;
import com.lxm.test.R;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.line_chart)
    LineChart lineChart;
    @BindView(R.id.btn_main)
    TextView btnMain;
    private ActivityResultLauncher<String> launcher;
    private LineChartManage lineChartManage;
    private List<Integer> list;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        btnMain.setText(getString(R.string.btn_jump));
        list = new CopyOnWriteArrayList<>();
        showLineChart();
        launcher = registerForActivityResult
                (new ActivityContract(), new ActivityResultCallback<String>() {
                    @Override
                    public void onActivityResult(String text) {
                        T.s(text);
                    }
                });
    }

    /**
     * 显示图表
     *
     * @param
     */
    private void showLineChart() {

        for (int i = 0; i <= 10; i++) {
            list.add(i, (int) ((Math.random() * 9 + 1)));
        }
        if (lineChartManage == null) {
            lineChartManage = new LineChartManage(lineChart);
        }
        lineChart.setVisibility(View.VISIBLE);
        lineChartManage.showLineChart(list, MainActivity.this);
    }

    @OnClick(R.id.btn_main)
    public void onClickView(@NotNull View view) {
        switch (view.getId()) {
            case R.id.btn_main:
                launcher.launch("MainActivity");
                break;
        }
    }
}