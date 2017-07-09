package comp3350.degree_planner.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import comp3350.degree_planner.R;

/**
 * Created by Penny He on 7/9/2017.
 */

public class AddFreeElectiveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electives);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_electives_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView pageTitle = (TextView) findViewById(R.id.toolbar_title);
        pageTitle.setText("Add Free Elective To Course Plan");
    }

    public void buttonAddNewElectiveOnClick(View v){
        Intent intent = new Intent(AddFreeElectiveActivity.this, CreateElectiveActivity.class);
        AddFreeElectiveActivity.this.startActivity(intent);
    }
}
