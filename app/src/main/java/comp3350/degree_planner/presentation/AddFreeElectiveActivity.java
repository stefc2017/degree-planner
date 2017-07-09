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

import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.objects.Course;

/**
 * Created by Penny He on 7/9/2017.
 */

public class AddFreeElectiveActivity extends AppCompatActivity {
    private AccessCourses accessCourses;
    private List<Course> freeElectives = null;
    private ArrayAdapter<Course> electiveListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electives);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_electives_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText("Add Free Elective To Course Plan");
    }

}
