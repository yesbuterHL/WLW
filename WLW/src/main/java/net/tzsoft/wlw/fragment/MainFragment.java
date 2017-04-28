package net.tzsoft.wlw.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import net.tzsoft.wlw.R;
import net.tzsoft.wlw.utils.L;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by hl on 2017/4/26.
 */

public class MainFragment extends BaseFragment implements View.OnClickListener {
    Button date1Btn;
    Button date2Btn;
    Button date3Btn;
    BarChart barChart;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initDateBtn();
        initBarChart();
        date3Btn.performClick();
    }
    private void initView(View view){
        barChart= (BarChart) view.findViewById(R.id.barchart);
        date1Btn= (Button) view.findViewById(R.id.date1_btn);
        date2Btn= (Button) view.findViewById(R.id.date2_btn);
        date3Btn= (Button) view.findViewById(R.id.date3_btn);
        date1Btn.setOnClickListener(this);
        date2Btn.setOnClickListener(this);
        date3Btn.setOnClickListener(this);
    }
    private void initBarChart(){
        XAxis xAxis=barChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelsToSkip(0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(18f);
        xAxis.setEnabled(true);
        xAxis.setXOffset(0f);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setAxisMinValue(0f);
        barChart.setScaleEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDescription(null);
        Legend legend = barChart.getLegend();
        legend.setFormSize(15f);
        legend.setTextSize(15f);
    }
    private void initDateBtn(){
        DateTime now=DateTime.now();
        date3Btn.setText(now.toString("M.dd"));
        date2Btn.setText(now.minusDays(1).toString("M.dd"));
        date1Btn.setText(now.minusDays(2).toString("M.dd"));
    }
    private void drawChart(int btnID){
        BarData barData=getBarData(btnID);
        barChart.setData(barData);
    }
    private BarData getBarData(int btnID){
        ArrayList<String> xValues = new ArrayList<String>();
        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
        xValues.add("出库");xValues.add("入库");xValues.add("库存");xValues.add("发射");
        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        for (int i = 0; i < xValues.size(); i++) {
            float value = (float) (Math.random()) + 3;
            yValues.add(new BarEntry(value, i));
        }
        BarDataSet barDataSet = new BarDataSet(yValues, "库存数量");
        barDataSet.setColor(Color.YELLOW);
        barDataSets.add(barDataSet);
        yValues=new ArrayList<BarEntry>();
        for (int i = 0; i < xValues.size(); i++) {
            float value = (float) (Math.random()) + 3;
            yValues.add(new BarEntry(value, i));
        }
        barDataSet = new BarDataSet(yValues, "操作数量");
        barDataSet.setColor(Color.rgb(91,155,213));
        barDataSets.add(barDataSet);
        BarData barData = new BarData(xValues, barDataSets);
        return barData;
    }
    @Override
    public void onClick(View view) {
        L.i("onclick");
        switch (view.getId()){
            case R.id.date1_btn:
                date1Btn.setEnabled(false);date2Btn.setEnabled(true);date3Btn.setEnabled(true);
                drawChart(R.id.date1_btn);
                break;
            case R.id.date2_btn:
                date2Btn.setEnabled(false);date1Btn.setEnabled(true);date3Btn.setEnabled(true);
                drawChart(R.id.date2_btn);
                break;
            case R.id.date3_btn:
                date3Btn.setEnabled(false);date1Btn.setEnabled(true);date2Btn.setEnabled(true);
                drawChart(R.id.date3_btn);
                break;
        }
    }
}
