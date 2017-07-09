package comp3350.degree_planner.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import comp3350.degree_planner.R;

/**
 * Created by Penny He on 7/9/2017.
 */

public class CreateElectiveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_elective);

        Toolbar toolbar = (Toolbar) findViewById(R.id.create_elective_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText("Create A Free Elective Course");
    }
}