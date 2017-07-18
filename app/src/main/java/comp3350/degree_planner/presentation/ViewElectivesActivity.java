package comp3350.degree_planner.presentation;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.business.exceptions.CourseAlreadyPlannedForTermException;
import comp3350.degree_planner.business.exceptions.CourseDoesNotExistException;
import comp3350.degree_planner.business.exceptions.CourseNotOfferedInTermException;
import comp3350.degree_planner.business.exceptions.StudentDoesNotExistException;
import comp3350.degree_planner.business.exceptions.TermTypeDoesNotExistException;
import comp3350.degree_planner.objects.Course;

/**
 * Created by Penny He on 7/9/2017.
 */

public class ViewElectivesActivity extends AppCompatActivity {
    private AccessCourses accessCourses;
    private List<Course> freeElectives = null;
    private ElectivesAdapter electivesAdapter;
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
        try {
            freeElectives = accessCourses.getAllUserDefinedCourses();
        } catch (SQLException e) {
            Toast.makeText(this, R.string.error_sql_exception, Toast.LENGTH_SHORT).show();
        }

        if (freeElectives != null && freeElectives.size() > 0) {

            ListView freeElectivesList = (ListView) findViewById(R.id.free_electives_list);

            electivesAdapter = new ElectivesAdapter(this, freeElectives, new CourseItemClickListener() {
                @Override
                public void onRemoveButtonClick(int courseId) {
                    removeElective(courseId);
                }

                @Override
                public void onMoveButtonClick(int id) {}
            });

            freeElectivesList.setAdapter(electivesAdapter);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.garbagebin_toolbar:
                // Delete mode on, remind adapter to refresh and show delete buttons
                electivesAdapter.toggleDeleteMode();
                electivesAdapter.notifyDataSetChanged();
                break;
            case R.id.home_toolbar:
                Intent intent = new Intent(ViewElectivesActivity.this, MainActivity.class);
                ViewElectivesActivity.this.startActivity(intent);
        }
        return true;
    }

    public void removeElective(int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final int electiveCourseId = id;
        String name = "";

        try{
            name = accessCourses.getCourseById(id).getName();
        }catch(Exception e){
            e.printStackTrace();
        }

        // Add confirm button
        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
                try {
                    accessCourses.removeUserDefinedCourse(electiveCourseId);
                    electivesAdapter.refreshList(accessCourses.getAllUserDefinedCourses());
                } catch (SQLException e) {
                    displayErrorMessage(e);
                }
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {} // Dialog dismissed
        });

        builder.setMessage(R.string.confirmRemoveCourse)
                .setTitle(name)
                .setIcon(R.drawable.question);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void displayErrorMessage(Exception e){
        if (e instanceof SQLException) {
            Toast.makeText(this, R.string.error_sql_exception, Toast.LENGTH_SHORT).show();
        }
    }

    public void buttonAddNewElectiveOnClick(View v) {
        Intent intent = new Intent(ViewElectivesActivity.this, CreateElectiveActivity.class);
        ViewElectivesActivity.this.startActivity(intent);
        finish();
    }
}