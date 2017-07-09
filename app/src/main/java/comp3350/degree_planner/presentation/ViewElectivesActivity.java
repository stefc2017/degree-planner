package comp3350.degree_planner.presentation;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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

public class ViewElectivesActivity extends AppCompatActivity {
    private AccessCourses accessCourses;
    private List<Course> freeElectives = null;
    private ArrayAdapter<Course> electiveListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_electives);

        Toolbar toolbar = (Toolbar) findViewById(R.id.view_electives_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText("Free Electives");

        accessCourses = new AccessCourses(Services.getDataAccess());
        freeElectives = accessCourses.getAllUserDefinedCourses();

        if (freeElectives != null && freeElectives.size() > 0) {
            // Set up adaptor for elective list
            electiveListAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, android.R.id.text1, freeElectives) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setText(freeElectives.get(position).getName());
                    return view;
                }
            };
            ListView freeElectivesList = (ListView) findViewById(R.id.free_electives_list);
            freeElectivesList.setAdapter(electiveListAdapter);

            freeElectivesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the selected free elective and pass its id to add free elective page
                    Course c = (Course) parent.getItemAtPosition(position);
                    int courseId = c.getId();
                    Intent intent = new Intent(ViewElectivesActivity.this, AddFreeElectiveActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("courseId", courseId);
                    intent.putExtras(b);
                    ViewElectivesActivity.this.startActivity(intent);
                }
            });
        }
    }

    public void buttonAddNewElectiveOnClick(View v) {
        Intent intent = new Intent(ViewElectivesActivity.this, CreateElectiveActivity.class);
        ViewElectivesActivity.this.startActivity(intent);
    }
}