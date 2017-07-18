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

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.business.AccessTermTypes;
import comp3350.degree_planner.business.exceptions.CourseAlreadyPlannedForTermException;
import comp3350.degree_planner.business.exceptions.CourseDoesNotExistException;
import comp3350.degree_planner.business.exceptions.CourseNotOfferedInTermException;
import comp3350.degree_planner.business.exceptions.StudentDoesNotExistException;
import comp3350.degree_planner.business.exceptions.TermTypeDoesNotExistException;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.hsqldb.DataAccessCourseOfferingsObject;
import comp3350.degree_planner.persistence.hsqldb.DataAccessCoursePlansObject;
import comp3350.degree_planner.persistence.hsqldb.DataAccessCoursesObject;
import comp3350.degree_planner.persistence.hsqldb.DataAccessStudentsObject;
import comp3350.degree_planner.persistence.hsqldb.DataAccessTermTypesObject;

/**
 * Created by Penny He on 7/9/2017.
 */

public class AddFreeElectiveActivity extends AppCompatActivity {
    private AccessCourses accessCourses;
    private AccessCoursePlan accessCoursePlan;
    private AccessTermTypes accessTermTypes;
    private int courseId = -1;
    private Course selectedElective;
    private String[] termsArray = null;
    private List<String> termsList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electives);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_elective_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText("Add Free Elective To Course Plan");

        Bundle b = getIntent().getExtras();
        courseId = b.getInt("courseId");

        accessCourses = new AccessCourses(Services.getDataAccess());
//        try {
            accessCoursePlan = new AccessCoursePlan(Services.getDataAccessCoursePlans(), Services.getDataAccessStudents(), Services.getDataAccessTermTypes(), Services.getDataAccessCourses(), Services.getDataAccessCourseOfferings());
//        } catch (Exception e) {
//            displayErrorMessage(e);
//        }
        accessTermTypes = new AccessTermTypes(Services.getDataAccess());
        try {
            selectedElective = accessCourses.getCourseById(courseId);
        } catch (SQLException e) {
            displayErrorMessage(e);
        }

        if (selectedElective != null) {
            TextView courseName = (TextView) findViewById(R.id.courseName);
            TextView abbrev = (TextView) findViewById(R.id.abbreviation);

            courseName.setText(selectedElective.getName());
            String identifier = ((UserDefinedCourse) selectedElective).getFullAbbreviation();
            abbrev.setText(identifier);
        }

        EditText editYear = (EditText) findViewById(R.id.year);
        // Hide keyboard when click outside of edit field
        editYear.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        AutoCompleteTextView autocompleteview = (AutoCompleteTextView) findViewById(R.id.term);
        // Hide keyboard when click outside of edit field
        autocompleteview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        // Set up term list for auto filling
        termsArray = getResources().getStringArray(R.array.season_list);
        termsList = Arrays.asList(termsArray);

        ArrayAdapter<String> termAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, termsList);

        autocompleteview.setAdapter(termAdapter);
    }

    public void buttonAddOnClick(View v) {
        final int studentId = 1; // Assume only one user
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        int year = calendar.get(Calendar.YEAR);
        int termTypeId = 0;
        boolean dataIsValid = true;

        EditText editYear = (EditText) findViewById(R.id.year);
        AutoCompleteTextView editTerm = (AutoCompleteTextView) findViewById(R.id.term);

        // Validate year entered by user
        if (validate(editYear.getText().toString())) {
            year = Integer.parseInt(editYear.getText().toString());
            // Validate term entered by user
            if (validate(editTerm.getText().toString())) {
                String termSelected = editTerm.getText().toString();
                if (termSelected.equalsIgnoreCase("Winter")) {
                    termTypeId = accessTermTypes.getTermTypeIdByName("Winter");
                } else if (termSelected.equalsIgnoreCase("Summer")) {
                    termTypeId = accessTermTypes.getTermTypeIdByName("Summer");
                } else if (termSelected.equalsIgnoreCase("Fall")) {
                    termTypeId = accessTermTypes.getTermTypeIdByName("Fall");
                } else {
                    dataIsValid = false;
                    Toast.makeText(this, R.string.error_invalid_term, Toast.LENGTH_SHORT).show();
                }
            } else {
                dataIsValid = false;
                Toast.makeText(this, R.string.error_no_term, Toast.LENGTH_SHORT).show();
            }
        } else {
            dataIsValid = false;
            Toast.makeText(this, R.string.error_no_year, Toast.LENGTH_SHORT).show();
        }

        // Add to course plan
        if (dataIsValid) {
            try {
                accessCoursePlan.addToCoursePlan(selectedElective.getId(), studentId, termTypeId, year);
                Toast.makeText(this, R.string.required_course_added, Toast.LENGTH_SHORT).show();
                replaceButtons();
            } catch (Exception e) {
                displayErrorMessage(e);
            }
        }

    }

    public void buttonViewCoursePlanOnClick(View v) {
        Intent intent = new Intent(AddFreeElectiveActivity.this, CoursePlansActivity.class);
        AddFreeElectiveActivity.this.startActivity(intent);
        finish();
    }

    public void buttonCancelOnClick(View v) {
        Intent intent = new Intent(AddFreeElectiveActivity.this, ViewElectivesActivity.class);
        AddFreeElectiveActivity.this.startActivity(intent);
        finish();
    }


    private boolean validate(String text) {
        return text.length() > 0;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void replaceButtons() {
        Button add = (Button) findViewById(R.id.add_course_button);
        Button toCoursePlan = (Button) findViewById(R.id.view_course_plan);

        add.setVisibility(View.INVISIBLE);
        toCoursePlan.setVisibility(View.VISIBLE);
    }

    private void displayErrorMessage(Exception e) {
        if (e instanceof CourseAlreadyPlannedForTermException) {
            Toast.makeText(this, R.string.error_duplicate_course, Toast.LENGTH_SHORT).show();
        } else if (e instanceof StudentDoesNotExistException) {
            Toast.makeText(this, R.string.error_student_not_exist, Toast.LENGTH_SHORT).show();
        } else if (e instanceof CourseDoesNotExistException) {
            Toast.makeText(this, R.string.error_course_not_exist, Toast.LENGTH_SHORT).show();
        } else if (e instanceof TermTypeDoesNotExistException) {
            Toast.makeText(this, R.string.error_termtype_not_exist, Toast.LENGTH_SHORT).show();
        } else if (e instanceof CourseNotOfferedInTermException) {
            Toast.makeText(this, R.string.error_course_not_offered, Toast.LENGTH_SHORT).show();
        } else if (e instanceof SQLException ){
            Toast.makeText(this, R.string.error_sql_exception, Toast.LENGTH_SHORT).show();
        }
    }
}
