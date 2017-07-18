package comp3350.degree_planner.acceptance;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import comp3350.degree_planner.R;
import comp3350.degree_planner.presentation.DegreeProgressActivity;
import comp3350.degree_planner.presentation.MainActivity;

import static org.junit.Assert.assertTrue;

/**
 * Created by Matthew Provencher on 7/17/2017.
 */

public class DegreeProgressTest {
    private static final String COURSE_TAKEN = "Courses Taken";
    private static final String COURSE_ELIGIBLE = "Eligible Required Courses";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private Solo solo;

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),
                activityTestRule.getActivity());
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    @Test
    public void testPickCSMajorDegree() throws Exception {

        //Navigate to Course Planning
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.progress));
        solo.assertCurrentActivity("Expected Degree Progress Activity", DegreeProgressActivity.class);

        assertTrue (COURSE_TAKEN + " was not found", solo.searchText(COURSE_TAKEN));
        assertTrue (COURSE_ELIGIBLE + " was not found", solo.searchText(COURSE_ELIGIBLE));
    }
}
