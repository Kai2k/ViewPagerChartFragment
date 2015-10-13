package com.example.android.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shinobicontrols.charts.CategoryAxis;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.LineSeries;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.NumberRange;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;
import com.shinobicontrols.charts.SupportChartFragment;
import com.shinobicontrols.charts.TickMark.ClippingMode;

public class MyFragment extends SupportChartFragment{

    public enum months {
        THREE(3),
        SIX(6),
        TWELVE(12);

        int value;

        months(int value){
            this.value = value;
        }
    }

    private static final int CROSSHAIR_INACTIVE_COLOR = Color.argb(255, 240, 240, 240);
    private static final int TRENDLINE_COLOR = Color.argb(255, 59, 172, 200);
    private ShinobiChart shinobiChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  super.onCreateView(inflater, container, savedInstanceState);
        buildShinobiChart();
        return view;
    }

    private void buildShinobiChart() {

        shinobiChart = getShinobiChart();

        // TODO: replace <license_key_here> with your trial license key
        shinobiChart.setLicenseKey("<license_key_here>");

        rebuildChart(months.TWELVE);
    }

    private NumberAxis createYAxis() {
        // Create the Y Axis, clip the top tick and label and give it a title
        NumberAxis yAxis = new NumberAxis(new NumberRange(0.0, 60.0));
        yAxis.setTickMarkClippingModeHigh(ClippingMode.NEITHER_PERSIST);
        yAxis.setTitle("Downloads (1000s)");
        return yAxis;
    }

    public void rebuildChart(months months){
        int seriesCount = shinobiChart.getSeries().size();
        for (int i = 0; i < seriesCount; i++){
            shinobiChart.removeSeries(shinobiChart.getSeries().get(0));
        }
        int xAxisCount = shinobiChart.getAllXAxes().size();
        for (int i = 0; i < xAxisCount; i++){
            shinobiChart.removeXAxis(shinobiChart.getAllXAxes().get(0));
        }
        int yAxisCount = shinobiChart.getAllYAxes().size();
        for (int i = 0; i < yAxisCount; i++){
            shinobiChart.removeYAxis(shinobiChart.getAllYAxes().get(0));
        }
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = createYAxis();
        LineSeries companyRevenue = createCompanyRevenueSeries(months);

        // Add them to the chart
        shinobiChart.addXAxis(xAxis);
        shinobiChart.addYAxis(yAxis);
        shinobiChart.addSeries(companyRevenue, xAxis, yAxis);

        // Style the chart and the crosshair
        shinobiChart.getStyle().setPlotAreaBackgroundColor(
                MyFragment.CROSSHAIR_INACTIVE_COLOR);
        shinobiChart.redrawChart();
    }

    private LineSeries createCompanyRevenueSeries(months months) {
        LineSeries companyRevenue = new LineSeries();

        // Give the series some data
        switch (months){
            case THREE:
                companyRevenue.setDataAdapter(createCompanyRevenueDataFor3Months());
                break;
            case SIX:
                companyRevenue.setDataAdapter(createCompanyRevenueDataFor6Months());
                break;
            case TWELVE:
                default:
                companyRevenue.setDataAdapter(createCompanyRevenueDataFor12Months());
                break;
        }

        // Style the line to make it stand out
        companyRevenue.getStyle().setLineColor(MyFragment.TRENDLINE_COLOR);
        companyRevenue.getStyle().setLineWidth(2.0f);
        shinobiChart.setTitle("MySpiffyApp - 2013 Figures - " + months.value + " months");

        return companyRevenue;
    }

    private DataAdapter<?, ?> createCompanyRevenueDataFor12Months() {
        DataAdapter<String, Double> data = new SimpleDataAdapter<String, Double>();

        data.add(new DataPoint<String, Double>("Jan", 15.6));
        data.add(new DataPoint<String, Double>("Feb", 16.2));
        data.add(new DataPoint<String, Double>("Mar", 14.8));
        data.add(new DataPoint<String, Double>("Apr", 25.5));
        data.add(new DataPoint<String, Double>("May", 20.1));
        data.add(new DataPoint<String, Double>("Jun", 22.8));
        data.add(new DataPoint<String, Double>("Jul", 20.4));
        data.add(new DataPoint<String, Double>("Aug", 35.0));
        data.add(new DataPoint<String, Double>("Sep", 32.7));
        data.add(new DataPoint<String, Double>("Oct", 33.3));
        data.add(new DataPoint<String, Double>("Nov", 34.9));
        data.add(new DataPoint<String, Double>("Dec", 33.6));

        return data;
    }

    private DataAdapter<?, ?> createCompanyRevenueDataFor6Months() {
        DataAdapter<String, Double> data = new SimpleDataAdapter<String, Double>();

        data.add(new DataPoint<String, Double>("Jan", 15.6));
        data.add(new DataPoint<String, Double>("Feb", 16.2));
        data.add(new DataPoint<String, Double>("Mar", 14.8));
        data.add(new DataPoint<String, Double>("Apr", 25.5));
        data.add(new DataPoint<String, Double>("May", 20.1));
        data.add(new DataPoint<String, Double>("Jun", 22.8));

        return data;
    }

    private DataAdapter<?, ?> createCompanyRevenueDataFor3Months() {
        DataAdapter<String, Double> data = new SimpleDataAdapter<String, Double>();

        data.add(new DataPoint<String, Double>("Jan", 15.6));
        data.add(new DataPoint<String, Double>("Feb", 16.2));
        data.add(new DataPoint<String, Double>("Mar", 14.8));

        return data;
    }
}
