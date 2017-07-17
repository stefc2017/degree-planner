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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.business.AccessTermTypes;
import comp3350.degree_planner.business.exceptions.CourseAlreadyPlannedForTermException;
import comp3350.degree_planner.business.exceptions.CourseDoesNotExistException;
import comp3350.degree_planner.business.exceptions.CourseNotOfferedInTermException;
import comp3350.degree_planner.business.exceptions.StudentDoesNotExistException;
import comp3350.degree_planner.business.exceptions.TermTypeDoesNotExistException;
import comp3350.degree_planner.objects.CoursePlan;

/**
 * Created by Penny He on 6/20/2017.
 */

public class CoursePlansActivity extends AppCompatActivity {
    private AccessCoursePlan accessCoursePlan;
    private AccessTermTypes accessTermTypes;
    private AlertDialog dialog = null;
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
        accessTermTypes = new AccessTermTypes(Services.getDataAccess());

        try {
            coursePlansAndHeaders = accessCoursePlan.getCoursePlansAndHeaders(1);
        }
        catch (Exception e) {
            setContentView(R.layout.generic_error);
        }

        coursePlanAdapter = new CoursePlanAdapter(this, coursePlansAndHeaders, new CourseItemClickListener() {
            @Override
            public void onRemoveButtonClick(int id) {
                confirmDelete(id);
            }

            @Override
            public void onMoveButtonClick(int id) {
                moveCoursePlan(id);
            }
        });
        coursePlanList.setAdapter(coursePlanAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.courseplans_menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home_toolbar:
                Intent intent = new Intent(CoursePlansActivity.this, MainActivity.class);
                CoursePlansActivity.this.startActivity(intent);
                break;
            case R.id.move_toolbar:
                coursePlanAdapter.toggleEditMode();
                coursePlanAdapter.notifyDataSetChanged();
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

    public void moveCoursePlan(int id){
        final int coursePlanId = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.dialog_move_course, null);
        final EditText editYear = (EditText) v.findViewById(R.id.year);
        Button moveBtn = (Button) v.findViewById(R.id.confirm_move_button);
        final AutoCompleteTextView autocompleteview = (AutoCompleteTextView)v.findViewById(R.id.term);

        // Set up term list for auto filling
        String[] termsArray = getResources().getStringArray(R.array.season_list);
        List<String> termsList = Arrays.asList(termsArray);

        ArrayAdapter<String> termAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, termsList);

        autocompleteview.setAdapter(termAdapter);

        moveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = -1;
                boolean dataIsValid = true;
                String termSelected = "";
                int termTypeId = -1;

                // Validate year entered by user
                if(validate(editYear.getText().toString())){
                    year = Integer.parseInt(editYear.getText().toString());
                    // Validate term entered by user
                    if(validate(autocompleteview.getText().toString())){
                        termSelected = autocompleteview.getText().toString();
                        if(termSelected.equalsIgnoreCase("Winter")){
                            termTypeId = accessTermTypes.getTermTypeIdByName("Winter");
                        }else if(termSelected.equalsIgnoreCase("Summer")){
                            termTypeId = accessTermTypes.getTermTypeIdByName("Summer");
                        }else if(termSelected.equalsIgnoreCase("Fall")){
                            termTypeId = accessTermTypes.getTermTypeIdByName("Fall");
                        }else{
                            dataIsValid = false;
                            Toast.makeText(CoursePlansActivity.this, R.string.error_invalid_term, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        dataIsValid = false;
                        Toast.makeText(CoursePlansActivity.this, R.string.error_no_term, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    dataIsValid = false;
                    Toast.makeText(CoursePlansActivity.this, R.string.error_no_year, Toast.LENGTH_SHORT).show();
                }

                // Add to course plan
                if(dataIsValid){
                    try{
                        accessCoursePlan.moveCourse(coursePlanId, termTypeId, year);
                        coursePlanAdapter.refreshList(accessCoursePlan.getCoursePlansAndHeaders(1));
                        Toast.makeText(CoursePlansActivity.this, R.string.course_plan_moved, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }catch(Exception e){
                        displayErrorMessage(e);
                    }
                }
            }
        });
        builder.setView(v);
        dialog = builder.create();
        dialog.show();
    }

    public void buttonCompSciCoursesOnClick(View v){
        Intent intent = new Intent(CoursePlansActivity.this, DegreesActivity.class);
        CoursePlansActivity.this.startActivity(intent);
        finish();
    }

    public void buttonFreeElectiveOnClick(View v){
        Intent intent = new Intent(CoursePlansActivity.this, ViewElectivesActivity.class);
        CoursePlansActivity.this.startActivity(intent);
        finish();
    }

    private boolean validate(String text){ return text.length() > 0; }

    private void displayErrorMessage(Exception e){
        if(e instanceof CourseAlreadyPlannedForTermException){
            Toast.makeText(this, R.string.error_duplicate_course, Toast.LENGTH_SHORT).show();
        }else if(e instanceof StudentDoesNotExistException){
            Toast.makeText(this, R.string.error_student_not_exist, Toast.LENGTH_SHORT).show();
        }else if(e instanceof CourseDoesNotExistException){
            Toast.makeText(this, R.string.error_course_not_exist, Toast.LENGTH_SHORT).show();
        }else if(e instanceof TermTypeDoesNotExistException){
            Toast.makeText(this, R.string.error_termtype_not_exist, Toast.LENGTH_SHORT).show();
        }else if(e instanceof CourseNotOfferedInTermException){
            Toast.makeText(this, R.string.error_course_not_offered, Toast.LENGTH_SHORT).show();
        }
    }

}
