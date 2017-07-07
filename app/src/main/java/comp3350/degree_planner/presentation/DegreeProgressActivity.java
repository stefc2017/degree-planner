package comp3350.degree_planner.presentation;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;


/**
 * Created by Penny He on 7/7/2017.
 */

public class DegreeProgressActivity extends AppCompatActivity {
    private PieChart creditHourChart;
    private List<PieEntry> exampleData;
    private String[] labels = {"Example A", "Example B"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degreeprogress);

        Toolbar toolbar = (Toolbar) findViewById(R.id.degreeProgress_toolbar);
        setSupportActionBar(toolbar);

        creditHourChart = (PieChart) findViewById(R.id.credit_hours);
        creditHourChart.setUsePercentValues(true);
        creditHourChart.getDescription().setEnabled(false);

        // Holds pie chart data
        exampleData = new ArrayList<PieEntry>();

        // Side note: public PieEntry(float value, String label)
        exampleData.add(new PieEntry(35.0f, labels[0]));
        exampleData.add(new PieEntry(65.0f, labels[1]));

        PieDataSet dataSet = new PieDataSet(exampleData, "Title");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(this, R.color.colorOrangeLight));
        colors.add(ContextCompat.getColor(this, R.color.colorMediumBlue));

        dataSet.setColors(colors);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        dataSet.setValueTextSize(20f);

        PieData chartData = new PieData(dataSet);
        chartData.setValueFormatter(new PercentFormatter());

        creditHourChart.setData(chartData);
        creditHourChart.setEntryLabelColor(ContextCompat.getColor(this, R.color.colorBlack));
        creditHourChart.setEntryLabelTextSize(20f);
    }
}
