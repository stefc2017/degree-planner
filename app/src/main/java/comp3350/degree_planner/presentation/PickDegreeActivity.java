package comp3350.degree_planner.presentation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import comp3350.degree_planner.R;

public class PickDegreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_degree);
        Toolbar toolbar = (Toolbar) findViewById(R.id.pick_degree_toolbar);
        setSupportActionBar(toolbar);
    }


}
