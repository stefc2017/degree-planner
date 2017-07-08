package comp3350.degree_planner.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.objects.Course;

/**
 * Created by Penny He on 7/8/2017.
 */

public class AddCourseActivity extends AppCompatActivity {
    private AccessCourses accessCourses;
    private Course selectedRequiredCourse;
    private int courseId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_course_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText(R.string.addCourse);

        Bundle b = getIntent().getExtras();
        courseId = b.getInt("courseId");

        accessCourses = new AccessCourses(Services.getDataAccess());

        selectedRequiredCourse = accessCourses.getCourseById(courseId);

        if(selectedRequiredCourse != null){
            TextView courseName = (TextView)findViewById(R.id.courseName);
            courseName.setText(selectedRequiredCourse.getName());
        }
    }
}