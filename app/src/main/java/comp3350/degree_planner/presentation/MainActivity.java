package comp3350.degree_planner.presentation;

import comp3350.degree_planner.application.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import comp3350.degree_planner.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Main.shutDown();
    }

    public void buttonDegreesOnClick(View v){
        Intent intent = new Intent(MainActivity.this, DegreesActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void buttonCoursePlanOnClick(View v){
        Intent intent = new Intent(MainActivity.this, CoursePlansActivity.class);
        MainActivity.this.startActivity(intent);
    }
}
