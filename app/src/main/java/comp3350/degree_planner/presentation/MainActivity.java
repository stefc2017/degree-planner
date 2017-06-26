package comp3350.degree_planner.presentation;

import comp3350.degree_planner.application.Main;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import comp3350.degree_planner.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

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
}
