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
import static org.junit.Assert.*;

import comp3350.degree_planner.R;
import comp3350.degree_planner.presentation.DegreesActivity;
import comp3350.degree_planner.presentation.MainActivity;

/**
 * Created by Tiffany Jiang on 2017-07-08.
 */

@RunWith(AndroidJUnit4.class)
public class ViewDegreesTest {
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
    public void testViewDegrees() throws Exception {
        final String DEGREE_1 = "Computer Science Major";
        final String DEGREE_2 = "Computer Science Honours";

        //Navigate to Degree Information
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.degreeInfo));
        solo.assertCurrentActivity("Expected Degrees Activity", DegreesActivity.class);

        //Verify list of Computer Science degrees are shown
        assertTrue (DEGREE_1 + " degree was not found", solo.searchText(DEGREE_1));
        assertTrue (DEGREE_2 + " degree was not found", solo.searchText(DEGREE_2));
    }
}
