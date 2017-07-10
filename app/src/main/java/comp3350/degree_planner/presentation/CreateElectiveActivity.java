package comp3350.degree_planner.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.business.Season;

/**
 * Created by Penny He on 7/9/2017.
 */

public class CreateElectiveActivity extends AppCompatActivity {
    private AccessCourses accessCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_elective);

        Toolbar toolbar = (Toolbar) findViewById(R.id.create_elective_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText("Create A Free Elective Course");

        EditText editName = (EditText) findViewById(R.id.name);
        // Hide keyboard when click outside of edit field
        editName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus){ hideKeyboard(v); }
            }
        });

        EditText editCredhrs = (EditText) findViewById(R.id.credithours);
        editCredhrs.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus){ hideKeyboard(v); }
            }
        });

        EditText editAbbrev = (EditText) findViewById(R.id.abbrev);
        editAbbrev.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus){ hideKeyboard(v); }
            }
        });

        accessCourses = new AccessCourses(Services.getDataAccess());
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void buttonAddOnClick(View v){
        String name = "", abbreviation = " ";
        double creditHrs = -1;
        boolean dataIsValid = true;

        EditText editName = (EditText)findViewById(R.id.name);
        EditText editCreditHours = (EditText)findViewById(R.id.credithours);
        EditText editAbbrev = (EditText)findViewById(R.id.abbrev);

        // Validate year entered by user
        if(validate(editName.getText().toString())){
            name = editName.getText().toString();
            // Validate term entered by user
            if(validate(editCreditHours.getText().toString())){
                creditHrs = Double.parseDouble(editCreditHours.getText().toString());
                if(validate(editAbbrev.getText().toString())){
                    abbreviation = editAbbrev.getText().toString();
                }else{
                    dataIsValid = false;
                    Toast.makeText(this, R.string.error_no_abbrev, Toast.LENGTH_SHORT).show();
                }
            }else{
                dataIsValid = false;
                Toast.makeText(this, R.string.error_no_credit_hours, Toast.LENGTH_SHORT).show();
            }
        }else{
            dataIsValid = false;
            Toast.makeText(this, R.string.error_no_name, Toast.LENGTH_SHORT).show();
        }

        // Add to electives list
        if(dataIsValid){
            try{
                accessCourses.addUserDefinedCourse(name,creditHrs,abbreviation);
                Toast.makeText(this, R.string.elective_added, Toast.LENGTH_SHORT).show();
                replaceButtons();
            }catch(Exception e){
            }
        }

    }

    public void buttonViewFreeElectivesOnClick(View v){
        Intent intent = new Intent(CreateElectiveActivity.this, ViewElectivesActivity.class);
        CreateElectiveActivity.this.startActivity(intent);
    }

    private boolean validate(String text){ return text.length() > 0; }

    private void replaceButtons(){
        Button add = (Button)findViewById(R.id.add_elective_button);
        Button toElectivesList = (Button)findViewById(R.id.view_free_electives);

        add.setVisibility(View.INVISIBLE);
        toElectivesList.setVisibility(View.VISIBLE);
    }
}