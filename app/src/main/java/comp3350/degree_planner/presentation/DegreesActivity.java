package comp3350.degree_planner.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import comp3350.degree_planner.R;

/**
 * Created by Penny He on 6/4/2017.
 */

public class DegreesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degrees);
    }

    public void buttonDegreeInfoOnClick(View v){
        Intent intent = new Intent(DegreesActivity.this, DegreeInfoActivity.class);
        DegreesActivity.this.startActivity(intent);
    }
}
