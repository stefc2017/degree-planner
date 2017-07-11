package comp3350.degree_planner.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
    private List<Course> courseList;
    private ArrayAdapter<Course> courseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degreeinfo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.degree_info_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText(R.string.degreeInfo);

        Bundle b = getIntent().getExtras();
        final int degreeId = b.getInt("degreeId");

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

            degreeRequiredCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the selected degree course and pass its id to add course page
                    Course c = (Course)parent.getItemAtPosition(position);
                    int courseId = c.getId();
                    Intent intent = new Intent(DegreeInfoActivity.this, AddCourseActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("courseId", courseId);
                    b.putInt("degreeId", degreeId);
                    intent.putExtras(b);
                    DegreeInfoActivity.this.startActivity(intent);
                    finish();
                }
            });
        }
    }//end onCreate
}
