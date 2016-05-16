package com.example.adam.aplikacja;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;

public class WykresyLekarz extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wykresy_lekarz);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        drawChart();
    }

    private void drawChart()
    {

        ArrayList<String> data = new  ArrayList<String>();

        // Creating an  XYSeries for przycisniecia
        XYSeries przycisnieciaSeries = new XYSeries("Ilość Przyciśnięć");
        // Creating an  XYSeries for czas
        XYSeries czasSeries = new XYSeries("Czas");
        // Creating an  XYSeries for srednia
        XYSeries sredniaSeries = new XYSeries("Średnia");

        // Adding data
        DataDbAdapter.givearrayData(this, data, przycisnieciaSeries, czasSeries, sredniaSeries);
        //Creating a dataset to hold  series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYMultipleSeriesDataset dataset1 = new XYMultipleSeriesDataset();
        XYMultipleSeriesDataset dataset2 = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        dataset.addSeries(przycisnieciaSeries);
        dataset1.addSeries(czasSeries);
        dataset2.addSeries(sredniaSeries);
        // Creating XYSeriesRenderer to customizeSeries
        XYSeriesRenderer Renderer = new XYSeriesRenderer();
        Renderer.setColor(Color.DKGRAY); //color of the graph set to cyan
        Renderer.setFillPoints(true);
        Renderer.setLineWidth(2);
        Renderer.setDisplayChartValues(true);
        Renderer.setChartValuesTextSize(20);


        // Creating a XYMultipleSeriesRenderer to customize the whole chart 1
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Ściśnięcia piłeczki");
        multiRenderer.setXTitle("Data");
        multiRenderer.setYTitle("Ilość Przyciśnięć");
        for(int i=0;i<data.size();i++){
            multiRenderer.addXTextLabel(i, data.get(i));
        }

        /***
         * Customizing graph 1
         */

        multiRenderer.setChartTitleTextSize(28);

        multiRenderer.setAxisTitleTextSize(24);

        multiRenderer.setLabelsTextSize(24);

        multiRenderer.setZoomButtonsVisible(false);

        multiRenderer.setPanEnabled(true, false);

        multiRenderer.setClickEnabled(false);

        multiRenderer.setZoomEnabled(true, false);

        multiRenderer.setShowGridY(false);

        multiRenderer.setShowGridX(false);

        multiRenderer.setFitLegend(true);

        multiRenderer.setShowGrid(false);

        multiRenderer.setZoomEnabled(true);

        multiRenderer.setExternalZoomEnabled(false);

        multiRenderer.setAntialiasing(true);

        multiRenderer.setInScroll(false);

        multiRenderer.setLegendHeight(30);
        multiRenderer.setLegendTextSize(24);

        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);

        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);

        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);

        multiRenderer.setYLabels(10);

        multiRenderer.setXAxisMin(-0.5);
        multiRenderer.setYAxisMin(0);

       multiRenderer.setXAxisMax(data.size());

        multiRenderer.setBarSpacing(0.5);

        multiRenderer.setBackgroundColor(Color.TRANSPARENT);

        multiRenderer.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
        multiRenderer.setApplyBackgroundColor(true);

        multiRenderer.setMargins(new int[]{30, 30, 30, 30});


        multiRenderer.addSeriesRenderer(Renderer);
        multiRenderer.setLabelsColor(Color.BLACK);
        multiRenderer.setXLabelsColor(Color.BLACK);
        multiRenderer.setYLabelsColor(0,Color.BLACK);

// Creating a XYMultipleSeriesRenderer to customize the whole chart 2
        XYMultipleSeriesRenderer multiRenderer1 = new XYMultipleSeriesRenderer();
        multiRenderer1.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        multiRenderer1.setXLabels(0);
        multiRenderer1.setChartTitle("Czas gry");
        multiRenderer1.setXTitle("Data");
        multiRenderer1.setYTitle("Czas");
        for(int i=0;i<data.size();i++){
            multiRenderer1.addXTextLabel(i, data.get(i));
        }

        /***
         * Customizing graph 2
         */
        multiRenderer1.setChartTitleTextSize(28);

        multiRenderer1.setAxisTitleTextSize(24);

        multiRenderer1.setLabelsTextSize(24);

        multiRenderer1.setZoomButtonsVisible(false);

        multiRenderer1.setPanEnabled(true, false);

        multiRenderer1.setClickEnabled(false);

        multiRenderer1.setZoomEnabled(true, false);

        multiRenderer1.setShowGridY(false);

        multiRenderer1.setShowGridX(false);

        multiRenderer1.setFitLegend(true);

        multiRenderer1.setShowGrid(false);

        multiRenderer1.setZoomEnabled(true);

        multiRenderer1.setExternalZoomEnabled(false);

        multiRenderer1.setAntialiasing(true);

        multiRenderer1.setInScroll(false);

        multiRenderer1.setLegendHeight(30);
        multiRenderer1.setLegendTextSize(24);

        multiRenderer1.setXLabelsAlign(Paint.Align.CENTER);

        multiRenderer1.setYLabelsAlign(Paint.Align.LEFT);

        multiRenderer1.setTextTypeface("sans_serif", Typeface.NORMAL);

        multiRenderer1.setYLabels(10);

        multiRenderer1.setXAxisMin(-0.5);
        multiRenderer1.setYAxisMin(0);

        multiRenderer1.setXAxisMax(data.size());

        multiRenderer1.setBarSpacing(0.5);

        multiRenderer1.setBackgroundColor(Color.TRANSPARENT);
        multiRenderer1.setLabelsColor(Color.BLACK);
        multiRenderer1.setXLabelsColor(Color.BLACK);
        multiRenderer1.setYLabelsColor(0,Color.BLACK);



        multiRenderer1.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
        multiRenderer1.setApplyBackgroundColor(true);

        multiRenderer1.setMargins(new int[]{30, 30, 30, 30});

        multiRenderer1.addSeriesRenderer(Renderer);
        // Creating a XYMultipleSeriesRenderer to customize the whole chart 3
        XYMultipleSeriesRenderer multiRenderer2 = new XYMultipleSeriesRenderer();
        multiRenderer2.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        multiRenderer2.setXLabels(0);
        multiRenderer2.setChartTitle("Średnia ilość przyciśnięć na minutę");
        multiRenderer2.setXTitle("Data");
        multiRenderer2.setYTitle("Średnia");
        for(int i=0;i<data.size();i++){
            multiRenderer2.addXTextLabel(i, data.get(i));
        }

        /***
         * Customizing graph 3
         */
        multiRenderer2.setChartTitleTextSize(28);

        multiRenderer2.setAxisTitleTextSize(24);

        multiRenderer2.setLabelsTextSize(24);

        multiRenderer2.setZoomButtonsVisible(false);

        multiRenderer2.setPanEnabled(true, false);

        multiRenderer2.setClickEnabled(false);

        multiRenderer2.setZoomEnabled(true, false);

        multiRenderer2.setShowGridY(false);

        multiRenderer2.setShowGridX(false);

        multiRenderer2.setFitLegend(true);

        multiRenderer2.setShowGrid(false);

        multiRenderer2.setZoomEnabled(true);

        multiRenderer2.setExternalZoomEnabled(false);

        multiRenderer2.setAntialiasing(true);

        multiRenderer2.setInScroll(false);

        multiRenderer2.setLegendHeight(30);
        multiRenderer2.setLegendTextSize(24);

        multiRenderer2.setXLabelsAlign(Paint.Align.CENTER);

        multiRenderer2.setYLabelsAlign(Paint.Align.LEFT);

        multiRenderer2.setTextTypeface("sans_serif", Typeface.NORMAL);

        multiRenderer2.setYLabels(10);

        multiRenderer2.setXAxisMin(-0.5);
        multiRenderer2.setYAxisMin(0);

        multiRenderer2.setXAxisMax(data.size());

        multiRenderer2.setBarSpacing(0.5);

        multiRenderer2.setBackgroundColor(Color.TRANSPARENT);

        multiRenderer2.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
        multiRenderer2.setApplyBackgroundColor(true);
        multiRenderer2.setLabelsColor(Color.BLACK);
        multiRenderer2.setXLabelsColor(Color.BLACK);
        multiRenderer2.setYLabelsColor(0,Color.BLACK);

        multiRenderer2.setMargins(new int[]{30, 30, 30, 30});

        multiRenderer2.addSeriesRenderer(Renderer);

        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);
        LinearLayout chartContainer1 = (LinearLayout) findViewById(R.id.chart_container1);
        LinearLayout chartContainer2 = (LinearLayout) findViewById(R.id.chart_container2);
        chartContainer.removeAllViews();
        chartContainer1.removeAllViews();
        chartContainer2.removeAllViews();
        //drawing bar chart
        GraphicalView chart = ChartFactory.getBarChartView(WykresyLekarz.this, dataset,
                multiRenderer, BarChart.Type.DEFAULT);
        GraphicalView chart1 = ChartFactory.getBarChartView(WykresyLekarz.this, dataset1,
                multiRenderer1, BarChart.Type.DEFAULT);
        GraphicalView chart2 = ChartFactory.getBarChartView(WykresyLekarz.this, dataset2,
                multiRenderer2, BarChart.Type.DEFAULT);
        //adding the view to the linearlayout
        chartContainer.addView(chart);
        chartContainer1.addView(chart1);
        chartContainer2.addView(chart2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wykresy_lekarz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
