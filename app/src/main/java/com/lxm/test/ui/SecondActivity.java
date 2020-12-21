package com.lxm.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.github.mikephil.charting.charts.PieChart;
import com.lxm.test.L;
import com.lxm.test.R;
import com.lxm.test.base.BaseActivity;
import com.lxm.test.chart.PieChartManage;
import com.lxm.test.utils.ActivityManager;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import butterknife.BindView;
import butterknife.OnClick;

/**
  * @Copyright (C), 2020
  * @Author: ym
  * @Date:
  * @Description:
  */
public class SecondActivity extends BaseActivity {

    @BindView(R.id.btn_second)
    TextView btnSecond;
    @BindView(R.id.pie_chart)
    PieChart pieChart;
    private PieChartManage pieChartManage;
    private List<Float> list= new ArrayList<>();
    private List<String> sList=new ArrayList<>();
    private static final int total=100;
    private Float sum=0f;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        btnSecond.setText(getString(R.string.btn_result));
        getRandomFloat();
        showPieChart(list);
    }

    private void getRandomFloat() {
        Random random=new Random();
        for (int i = 0; i <10; i++) {
            Float temp = random.nextFloat() * 10;
            list.add(i, temp);
            sList.add(i,
                    getRandomChar()+getRandomChar());
            sum=sum+ temp;
            if(sum>100){
               break;
            }
        }
    }
    /**
      * @Description: 生成汉字
      */
    private String getRandomChar() {
        String str = "";
        int heightPos;
        int lowPos;

        Random random = new Random();

        heightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(heightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            L.i("生成汉字错误");
        }

        return str;
    }

    private void showPieChart(final List<Float> list) {
        if (pieChartManage == null) {
            pieChartManage = new PieChartManage(pieChart);
        }
         pieChartManage.showPicChart
                ((ArrayList<Float>) list, (ArrayList<String>) sList, total);

    }

    @OnClick(R.id.btn_second)
    public void onClickView(View view) {
        switch (view.getId()){
            case R.id.btn_second:
                setResult(RESULT_OK, new Intent().putExtra("data",
                        ActivityManager.getInstance().currentActivity().getClass().getSimpleName()));
                finish();
                break;
        }
    }


}