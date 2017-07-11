package comp3350.degree_planner.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.CompletedCourses;
import comp3350.degree_planner.business.CreditHours;
import comp3350.degree_planner.business.EligibleCourses;
import comp3350.degree_planner.objects.Course;


/**
 * Created by Penny He on 7/7/2017.
 * Modified by Matthew Provencher on 9/7/2017
 */

public class DegreeProgressActivity extends AppCompatActivity {
    private PieChart creditHourChart;
    private ListView courseTakenList;
    private ListView eligibleReqCourseList;
    private List<PieEntry> creditHourData;
    private List<Course> coursesTaken;
    private List<Course> eligibleReqCourses;
    private CreditHours creditHours = new CreditHours();
    private int studentId = 1;
    private int degreeId = 1;
    private int takenCreditHours = creditHours.getCreditHoursTaken( studentId );
    private int requiredCreditHours = creditHours.getRequiredCreditHoursTaken( studentId, degreeId );
    private CompletedCourses completedCourses = new CompletedCourses( Services.getDataAccess() );
    private EligibleCourses eligibleCourses = new EligibleCourses( Services.getDataAccess() );


    private String[] labels = {"Hours Taken", "Hours Left"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degreeprogress);

        Toolbar toolbar = (Toolbar) findViewById(R.id.degree_progress_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText(R.string.progress);

        coursesTaken = completedCourses.getCoursesTaken( studentId );
        courseTakenList = (ListView) findViewById( R.id.course_taken_list );
        courseTakenList.setAdapter( new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, android.R.id.text1, coursesTaken ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setText(coursesTaken.get(position).getName());
                return view;
            }
        } );
        TextView courseTakenTitle = new TextView( getApplicationContext() );
        courseTakenTitle.setText("Courses Taken");
        courseTakenList.addHeaderView( courseTakenTitle );

        eligibleReqCourses = eligibleCourses.getEligibleRequiredCourse( studentId, degreeId );
        eligibleReqCourseList = (ListView) findViewById( R.id.eligible_req_course_list );
        eligibleReqCourseList.setAdapter( new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, android.R.id.text1, eligibleReqCourses ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setText(eligibleReqCourses.get(position).getName());
                return view;
            }
        } );
        TextView eligibleCourseTitle = new TextView( getApplicationContext() );
        eligibleCourseTitle.setText("Eligible Required Courses");
        eligibleReqCourseList.addHeaderView( eligibleCourseTitle );

        creditHourChart = (PieChart) findViewById(R.id.credit_hours);
        //creditHourChart.setUsePercentValues(true);
        creditHourChart.getDescription().setEnabled(false);

        // Holds pie chart data
        creditHourData = new ArrayList<PieEntry>();

        // Side note: public PieEntry(float value, String label)
        creditHourData.add(new PieEntry( takenCreditHours , labels[0]));
        creditHourData.add(new PieEntry(120-takenCreditHours, labels[1]));

        PieDataSet dataSet = new PieDataSet(creditHourData, "");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(this, R.color.colorOrangeLight));
        colors.add(ContextCompat.getColor(this, R.color.colorMediumBlue));

        dataSet.setColors(colors);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        dataSet.setValueTextSize(20f);

        PieData chartData = new PieData(dataSet);
        //chartData.setValueFormatter(new PercentFormatter());

        creditHourChart.setData(chartData);
        creditHourChart.setEntryLabelColor(ContextCompat.getColor(this, R.color.colorBlack));
        creditHourChart.setEntryLabelTextSize(20f);
    }

    public void buttonPickDegreeOnClick(View v){
        Intent intent = new Intent(DegreeProgressActivity.this, PickDegreeActivity.class);
        DegreeProgressActivity.this.startActivity(intent);
    }
}
