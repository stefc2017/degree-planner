package comp3350.degree_planner.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCoursePlan;

/**
 * Created by Penny He on 6/20/2017.
 */

public class CoursePlansActivity extends Activity {
    private AccessCoursePlan accessCoursePlan;
    private ArrayList coursePlansAndHeaders;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_courseplans);

         ListView coursePlanList = (ListView)findViewById(R.id.coursePlans);
         accessCoursePlan = new AccessCoursePlan(Services.getDataAccess());
         coursePlansAndHeaders = accessCoursePlan.getCoursePlansAndHeaders();

         coursePlanList.setAdapter(new CoursePlanAdapter(this, coursePlansAndHeaders));
     }

}
