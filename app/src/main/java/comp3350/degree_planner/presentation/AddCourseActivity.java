package comp3350.degree_planner.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.business.Season;
import comp3350.degree_planner.business.exceptions.CourseAlreadyPlannedForTermException;
import comp3350.degree_planner.business.exceptions.CourseDoesNotExistException;
import comp3350.degree_planner.business.exceptions.CourseNotOfferedInTermException;
import comp3350.degree_planner.business.exceptions.StudentDoesNotExistException;
import comp3350.degree_planner.business.exceptions.TermTypeDoesNotExistException;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.ScienceCourse;

/**
 * Created by Penny He on 7/8/2017.
 */

public class AddCourseActivity extends AppCompatActivity {
    private AccessCourses accessCourses;
    private AccessCoursePlan accessCoursePlan;
    private Course selectedRequiredCourse = null;
    private int courseId = -1;
    private int degreeId = -1;
    private String[] termsArray = null;
    private List<String> termsList = null;

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
        degreeId = b.getInt("degreeId");

        accessCourses = new AccessCourses(Services.getDataAccess());
        accessCoursePlan = new AccessCoursePlan(Services.getDataAccess());
        selectedRequiredCourse = accessCourses.getCourseById(courseId);

        if(selectedRequiredCourse != null){
            TextView courseName = (TextView)findViewById(R.id.courseName);
            TextView courseId = (TextView)findViewById(R.id.courseId);

            courseName.setText(selectedRequiredCourse.getName());
            String identifier = ((ScienceCourse)selectedRequiredCourse).getDepartment().getAbbreviation() + " "
                    + ((ScienceCourse)selectedRequiredCourse).getCourseNumber();
            courseId.setText(identifier);
        }

        EditText editYear = (EditText) findViewById(R.id.year);
        // Hide keyboard when click outside of edit field
        editYear.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus){ hideKeyboard(v); }
            }
        });

        AutoCompleteTextView autocompleteview = (AutoCompleteTextView)findViewById(R.id.term);
        // Hide keyboard when click outside of edit field
        autocompleteview.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus){ hideKeyboard(v); }
            }
        });

        // Set up term list for auto filling
        termsArray = getResources().getStringArray(R.array.season_list);
        termsList = Arrays.asList(termsArray);

        ArrayAdapter<String> termAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, termsList);

        autocompleteview.setAdapter(termAdapter);
    }

    public void buttonAddOnClick(View v){
        final int studentId = 1; // Assume only one user
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        int year = calendar.get(Calendar.YEAR);
        int termTypeId = 0;
        boolean dataIsValid = true;

        EditText editYear = (EditText)findViewById(R.id.year);
        AutoCompleteTextView editTerm = (AutoCompleteTextView)findViewById(R.id.term);

        // Validate year entered by user
        if(validate(editYear.getText().toString())){
            year = Integer.parseInt(editYear.getText().toString());
            // Validate term entered by user
            if(validate(editTerm.getText().toString())){
                String termSelected = editTerm.getText().toString();
                if(termSelected.equalsIgnoreCase("Winter")){
                    termTypeId = Season.WINTER.getValue();
                }else if(termSelected.equalsIgnoreCase("Summer")){
                    termTypeId = Season.SUMMER.getValue();
                }else if(termSelected.equalsIgnoreCase("Fall")){
                    termTypeId = Season.FALL.getValue();
                }else{
                    dataIsValid = false;
                    Toast.makeText(this, R.string.error_invalid_term, Toast.LENGTH_SHORT).show();
                }
            }else{
                dataIsValid = false;
                Toast.makeText(this, R.string.error_no_term, Toast.LENGTH_SHORT).show();
            }
        }else{
            dataIsValid = false;
            Toast.makeText(this, R.string.error_no_year, Toast.LENGTH_SHORT).show();
        }

        // Add to course plan
        if(dataIsValid){
            try{
                accessCoursePlan.addToCoursePlan(selectedRequiredCourse.getId(), studentId, termTypeId, year);
                Toast.makeText(this, R.string.required_course_added, Toast.LENGTH_SHORT).show();
                replaceButtons();
            }catch(Exception e){
                displayErrorMessage(e);
            }
        }

    }

    public void buttonViewCoursePlanOnClick(View v){
        Intent intent = new Intent(AddCourseActivity.this, CoursePlansActivity.class);
        AddCourseActivity.this.startActivity(intent);
    }

    public void buttonCancelOnClick(View v){
        Intent intent = new Intent(AddCourseActivity.this, DegreeInfoActivity.class);
        Bundle b = new Bundle();
        b.putInt("degreeId", degreeId);
        intent.putExtras(b);
        AddCourseActivity.this.startActivity(intent);
    }


    private boolean validate(String text){ return text.length() > 0; }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void replaceButtons(){
        Button add = (Button)findViewById(R.id.add_course_button);
        Button toCoursePlan = (Button)findViewById(R.id.view_course_plan);

        add.setVisibility(View.GONE);
        toCoursePlan.setVisibility(View.VISIBLE);
    }

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