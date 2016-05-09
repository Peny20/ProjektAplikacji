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
        //Renderer.setDisplayChartValues(true);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
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
         * Customizing graphs
         */
        //setting text size of the title
        multiRenderer.setChartTitleTextSize(28);
        //setting text size of the axis title
        multiRenderer.setAxisTitleTextSize(24);
        //setting text size of the graph lable
        multiRenderer.setLabelsTextSize(24);
        //setting zoom buttons visiblity
        multiRenderer.setZoomButtonsVisible(false);
        //setting pan enablity which uses graph to move on both axis
        multiRenderer.setPanEnabled(true, false);
        //setting click false on graph
        multiRenderer.setClickEnabled(false);
        //setting zoom to false on both axis
        multiRenderer.setZoomEnabled(true, false);
        //setting lines to display on y axis
        multiRenderer.setShowGridY(false);
        //setting lines to display on x axis
        multiRenderer.setShowGridX(false);
        //setting legend to fit the screen size
        multiRenderer.setFitLegend(true);
        //setting displaying line on grid
        multiRenderer.setShowGrid(false);
        //setting zoom to false
        multiRenderer.setZoomEnabled(true);
        //setting external zoom functions to false
        multiRenderer.setExternalZoomEnabled(false);
        //setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer.setAntialiasing(true);
        //setting to in scroll to false
        multiRenderer.setInScroll(false);
        //setting to set legend height of the graph
        multiRenderer.setLegendHeight(30);
        multiRenderer.setLegendTextSize(24);
        //setting x axis label align
        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
        //setting y axis label to align
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
        //setting text style
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
        //setting no of values to display in y axis
        multiRenderer.setYLabels(10);
    /* setting y axis max value, Since i'm using static values inside the graph
     * so i'm setting y max value to 4000.
     */
        // if you use dynamic values then get the max y value and set here
       // multiRenderer.setYAxisMax(4000);
        //setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMin(-0.5);
        multiRenderer.setYAxisMin(0);
        //setting max values to be display in x axis
       multiRenderer.setXAxisMax(data.size());
        //setting bar size or space between two bars
        multiRenderer.setBarSpacing(0.5);
        //Setting background color of the graph to transparent
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
        //Setting margin color of the graph to transparent
        multiRenderer.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
        multiRenderer.setApplyBackgroundColor(true);

        //setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{30, 30, 30, 30});

        // Adding expenseRenderer to multipleRenderer
        multiRenderer.addSeriesRenderer(Renderer);
        multiRenderer.setLabelsColor(Color.BLACK);
        multiRenderer.setXLabelsColor(Color.BLACK);
        multiRenderer.setYLabelsColor(0,Color.BLACK);
        //this part is used to display graph on the xml

        //remove any views before u paint the chart

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
         * Customizing graphs
         */
        //setting text size of the title
        multiRenderer1.setChartTitleTextSize(28);
        //setting text size of the axis title
        multiRenderer1.setAxisTitleTextSize(24);
        //setting text size of the graph lable
        multiRenderer1.setLabelsTextSize(24);
        //setting zoom buttons visiblity
        multiRenderer1.setZoomButtonsVisible(false);
        //setting pan enablity which uses graph to move on both axis
        multiRenderer1.setPanEnabled(true, false);
        //setting click false on graph
        multiRenderer1.setClickEnabled(false);
        //setting zoom to false on both axis
        multiRenderer1.setZoomEnabled(true, false);
        //setting lines to display on y axis
        multiRenderer1.setShowGridY(false);
        //setting lines to display on x axis
        multiRenderer1.setShowGridX(false);
        //setting legend to fit the screen size
        multiRenderer1.setFitLegend(true);
        //setting displaying line on grid
        multiRenderer1.setShowGrid(false);
        //setting zoom to false
        multiRenderer1.setZoomEnabled(true);
        //setting external zoom functions to false
        multiRenderer1.setExternalZoomEnabled(false);
        //setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer1.setAntialiasing(true);
        //setting to in scroll to false
        multiRenderer1.setInScroll(false);
        //setting to set legend height of the graph
        multiRenderer1.setLegendHeight(30);
        multiRenderer1.setLegendTextSize(24);
        //setting x axis label align
        multiRenderer1.setXLabelsAlign(Paint.Align.CENTER);
        //setting y axis label to align
        multiRenderer1.setYLabelsAlign(Paint.Align.LEFT);
        //setting text style
        multiRenderer1.setTextTypeface("sans_serif", Typeface.NORMAL);
        //setting no of values to display in y axis
        multiRenderer1.setYLabels(10);
    /* setting y axis max value, Since i'm using static values inside the graph
     * so i'm setting y max value to 4000.
     */
        // if you use dynamic values then get the max y value and set here
        // multiRenderer.setYAxisMax(4000);
        //setting used to move the graph on xaxiz to .5 to the right
        multiRenderer1.setXAxisMin(-0.5);
        multiRenderer1.setYAxisMin(0);
        //setting max values to be display in x axis
        multiRenderer1.setXAxisMax(data.size());
        //setting bar size or space between two bars
        multiRenderer1.setBarSpacing(0.5);
        //Setting background color of the graph to transparent
        multiRenderer1.setBackgroundColor(Color.TRANSPARENT);
        multiRenderer1.setLabelsColor(Color.BLACK);
        multiRenderer1.setXLabelsColor(Color.BLACK);
        multiRenderer1.setYLabelsColor(0,Color.BLACK);


        //Setting margin color of the graph to transparent
        multiRenderer1.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
        multiRenderer1.setApplyBackgroundColor(true);

        //setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer1.setMargins(new int[]{30, 30, 30, 30});

        // Adding expenseRenderer to multipleRenderer
        multiRenderer1.addSeriesRenderer(Renderer);
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
         * Customizing graphs
         */
        //setting text size of the title
        multiRenderer2.setChartTitleTextSize(28);
        //setting text size of the axis title
        multiRenderer2.setAxisTitleTextSize(24);
        //setting text size of the graph lable
        multiRenderer2.setLabelsTextSize(24);
        //setting zoom buttons visiblity
        multiRenderer2.setZoomButtonsVisible(false);
        //setting pan enablity which uses graph to move on both axis
        multiRenderer2.setPanEnabled(true, false);
        //setting click false on graph
        multiRenderer2.setClickEnabled(false);
        //setting zoom to false on both axis
        multiRenderer2.setZoomEnabled(true, false);
        //setting lines to display on y axis
        multiRenderer2.setShowGridY(false);
        //setting lines to display on x axis
        multiRenderer2.setShowGridX(false);
        //setting legend to fit the screen size
        multiRenderer2.setFitLegend(true);
        //setting displaying line on grid
        multiRenderer2.setShowGrid(false);
        //setting zoom to false
        multiRenderer2.setZoomEnabled(true);
        //setting external zoom functions to false
        multiRenderer2.setExternalZoomEnabled(false);
        //setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer2.setAntialiasing(true);
        //setting to in scroll to false
        multiRenderer2.setInScroll(false);
        //setting to set legend height of the graph
        multiRenderer2.setLegendHeight(30);
        multiRenderer2.setLegendTextSize(24);
        //setting x axis label align
        multiRenderer2.setXLabelsAlign(Paint.Align.CENTER);
        //setting y axis label to align
        multiRenderer2.setYLabelsAlign(Paint.Align.LEFT);
        //setting text style
        multiRenderer2.setTextTypeface("sans_serif", Typeface.NORMAL);
        //setting no of values to display in y axis
        multiRenderer2.setYLabels(10);
    /* setting y axis max value, Since i'm using static values inside the graph
     * so i'm setting y max value to 4000.
     */
        // if you use dynamic values then get the max y value and set here
        // multiRenderer.setYAxisMax(4000);
        //setting used to move the graph on xaxiz to .5 to the right
        multiRenderer2.setXAxisMin(-0.5);
        multiRenderer2.setYAxisMin(0);
        //setting max values to be display in x axis
        multiRenderer2.setXAxisMax(data.size());
        //setting bar size or space between two bars
        multiRenderer2.setBarSpacing(0.5);
        //Setting background color of the graph to transparent
        multiRenderer2.setBackgroundColor(Color.TRANSPARENT);
        //Setting margin color of the graph to transparent
        multiRenderer2.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
        multiRenderer2.setApplyBackgroundColor(true);
        multiRenderer2.setLabelsColor(Color.BLACK);
        multiRenderer2.setXLabelsColor(Color.BLACK);
        multiRenderer2.setYLabelsColor(0,Color.BLACK);
        //setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer2.setMargins(new int[]{30, 30, 30, 30});

        // Adding expenseRenderer to multipleRenderer
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
