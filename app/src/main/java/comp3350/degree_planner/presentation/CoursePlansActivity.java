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
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCoursePlan;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.courseplans_toolbar);
        setSupportActionBar(toolbar);

        ListView coursePlanList = (ListView)findViewById(R.id.coursePlans);
        accessCoursePlan = new AccessCoursePlan(Services.getDataAccess());

        try {
            coursePlansAndHeaders = accessCoursePlan.getCoursePlansAndHeaders(1);
        }
        catch (Exception e) {
            setContentView(R.layout.generic_error);
        }

        coursePlanAdapter = new CoursePlanAdapter(this, coursePlansAndHeaders);
        coursePlanList.setAdapter(coursePlanAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }//end onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.garbagebin_toolbar:
                LinearLayout deleteButtons;
                int numberOfCourses = coursePlanAdapter.getNumberOfCoursePlans();
                for(int i = 0; i < numberOfCourses; i++){
                    deleteButtons = (LinearLayout) findViewById(R.id.deleteButton_courses);
                    deleteButtons.setVisibility(View.VISIBLE);
                    deleteButtons.setId(-1);
                }
        }
        return true;
    }//end onCreateOptionsMenu

    public void confirmDelete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final int coursePlanId = view.getId();
        //Add the buttons
        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //proceed with deletion/removing course
                try {
                    accessCoursePlan.removeFromCoursePlan(coursePlanId);
                    refreshPage();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }//end onClick
        });//end confirmButton

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //User clicked cancel
            }//end onClick
        });//end confirmButton

        builder.setMessage(R.string.confirmRemoveCourse)
                .setTitle(R.string.removeCourse)
                .setIcon(R.drawable.question);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void refreshPage(){
        Intent intent = new Intent(CoursePlansActivity.this, CoursePlansActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        startActivity(intent);
        overridePendingTransition(0,0);
        finish();
    }
}
