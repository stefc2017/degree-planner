package comp3350.degree_planner.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.objects.CoursePlan;

/**
 * Created by Penny He on 6/20/2017.
 */

public class CoursePlansActivity extends AppCompatActivity {
    private AccessCoursePlan accessCoursePlan;
    private List coursePlansAndHeaders;
    private CoursePlanAdapter coursePlanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseplans);

        Toolbar toolbar = (Toolbar) findViewById(R.id.course_plan_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText(R.string.coursePlan);

        ListView coursePlanList = (ListView)findViewById(R.id.coursePlans);
        accessCoursePlan = new AccessCoursePlan(Services.getDataAccess());

        try {
            coursePlansAndHeaders = accessCoursePlan.getCoursePlansAndHeaders(1);
        }
        catch (Exception e) {
            setContentView(R.layout.generic_error);
        }

        coursePlanAdapter = new CoursePlanAdapter(this, coursePlansAndHeaders, new CoursePlanClickListener() {
            @Override
            public void onRemoveButtonClick(int id) {
                confirmDelete(id);
            }
        });
        coursePlanList.setAdapter(coursePlanAdapter);
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
                coursePlanAdapter.toggleDeleteMode();
                coursePlanAdapter.notifyDataSetChanged();
                break;
        }
        return true;
    }

    public void confirmDelete(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final int coursePlanId = id;
        String name = "";

        try{
            CoursePlan cp = accessCoursePlan.getCoursePlan(id);
            name = cp.getCourse().getName();
        }catch(Exception e) {
            e.printStackTrace();
        }
        // Add the buttons
        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Proceed with deletion/removing course
                try {
                    accessCoursePlan.removeFromCoursePlan(coursePlanId);
                }catch(Exception e){
                    e.printStackTrace();
                }

                try {
                    coursePlanAdapter.refreshList(accessCoursePlan.getCoursePlansAndHeaders(1));
                }
                catch (Exception e) {
                    setContentView(R.layout.generic_error);
                }
            }//end onClick
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked cancel, dialog dismissed
            }//end onClick
        });

        builder.setMessage(R.string.confirmRemoveCourse)
                .setTitle(name)
                .setIcon(R.drawable.question);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void buttonCompSciCoursesOnClick(View v){
        Intent intent = new Intent(CoursePlansActivity.this, DegreesActivity.class);
        CoursePlansActivity.this.startActivity(intent);
    }

    public void buttonFreeElectiveOnClick(View v){
        Intent intent = new Intent(CoursePlansActivity.this, AddFreeElectiveActivity.class);
        CoursePlansActivity.this.startActivity(intent);
    }
}
