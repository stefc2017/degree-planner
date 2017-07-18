package comp3350.degree_planner.acceptance;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.degree_planner.R;
import comp3350.degree_planner.presentation.CoursePlansActivity;
import comp3350.degree_planner.presentation.DegreeProgressActivity;
import comp3350.degree_planner.presentation.MainActivity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class PickDegreeTest {
    private static final String PICK_DEGREE_TITLE = "Pick a Degree";
    private static final String DEGREE_1 = "Computer Science Major";
    private static final String DEGREE_2 = "Computer Science Honours";

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

        solo.clickOnButton(solo.getString(R.string.pick_degree));
        assertTrue ("Pick Degree screen has not appeared", solo.searchText(PICK_DEGREE_TITLE));
        assertTrue (DEGREE_1 + " degree not was found", solo.searchText(DEGREE_1));
        assertTrue (DEGREE_2 + " degree not was found", solo.searchText(DEGREE_2));

        solo.clickOnText(DEGREE_1);
        solo.assertCurrentActivity("Expected Degree Progress Activity", DegreeProgressActivity.class);
    }

    @Test
    public void testPickCSHonourDegree() throws Exception {

        //Navigate to Course Planning
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.progress));
        solo.assertCurrentActivity("Expected Degree Progress Activity", DegreeProgressActivity.class);

        solo.clickOnButton(solo.getString(R.string.pick_degree));
        assertTrue ("Pick Degree screen has not appeared", solo.searchText(PICK_DEGREE_TITLE));
        assertTrue (DEGREE_1 + " degree was not found", solo.searchText(DEGREE_1));
        assertTrue (DEGREE_2 + " degree was not found", solo.searchText(DEGREE_2));

        solo.clickOnText(DEGREE_2);
        solo.assertCurrentActivity("Expected Degree Progress Activity", DegreeProgressActivity.class);
    }
}
