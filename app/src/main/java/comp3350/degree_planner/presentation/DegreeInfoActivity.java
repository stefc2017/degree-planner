package comp3350.degree_planner.presentation;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.business.AccessDegrees;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.Degree;

/**
 * Created by Penny He on 6/4/2017.
 */

public class DegreeInfoActivity extends AppCompatActivity {
    private AccessCourses accessCourses;
    private AccessDegrees accessDegrees;
    private ArrayList<Course> courseList;
    private ArrayAdapter<Course> courseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degreeinfo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.degreeinfo_toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        int degreeId = b.getInt("degreeId");

        accessCourses = new AccessCourses(Services.getDataAccess());
        accessDegrees = new AccessDegrees(Services.getDataAccess());
        courseList = new ArrayList<Course>();


        courseList = accessCourses.getDegreeCourses(degreeId);
        Degree currDegree = accessDegrees.getDegreeById(degreeId);

        if(currDegree != null){
            TextView degName = (TextView)findViewById(R.id.degreeName);
            TextView degCreditHours = (TextView)findViewById(R.id.degreeRequiredCreditHours);
            TextView degGPA = (TextView)findViewById(R.id.degreeRequiredGPA);
            degName.setText(currDegree.getName());
            degCreditHours.setText(currDegree.getCreditHours() + " CR");
            degGPA.setText("GPA " + currDegree.getGpaRequired() + "");
        }


        if(courseList != null){
            courseListAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, android.R.id.text1, courseList){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setText(courseList.get(position).getName());
                    return view;
                }
            };
            ListView degreeRequiredCourses = (ListView)findViewById(R.id.degreeRequiredCourses);
            degreeRequiredCourses.setAdapter(courseListAdapter);
        }
    }//end onCreate
}
