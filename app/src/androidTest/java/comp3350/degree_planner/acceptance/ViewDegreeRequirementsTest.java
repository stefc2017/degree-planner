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
import comp3350.degree_planner.presentation.DegreeInfoActivity;
import comp3350.degree_planner.presentation.DegreesActivity;
import comp3350.degree_planner.presentation.MainActivity;

/**
 * Created by Tiffany Jiang on 2017-07-08.
 */

@RunWith(AndroidJUnit4.class)
public class ViewDegreeRequirementsTest {
    private static final String COURSE_1 = "Introductory Computer Science I";
    private static final String COURSE_2 = "Introductory Computer Science II";
    private static final String COURSE_3 = "Object Orientation";
    private static final String COURSE_4 = "Software Engineering I";
    private static final String WRONG_COURSE = "Cultural Anthropology";

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
    public void testViewCSMajorRequirements() {
        final String CSM_DEGREE = "Computer Science Major";
        final double CSM_CREDIT_HOURS = 120.0;
        final double CSM_GPA = 2.0;

        //Navigate to degree information
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.degreeInfo));
        solo.assertCurrentActivity("Expected Degrees Activity", DegreesActivity.class);

        //Verify Computer Science Major is shown
        assertTrue (CSM_DEGREE + " degree was not found", solo.searchText(CSM_DEGREE));

        //Navigate to Computer Science Major
        solo.clickOnText(CSM_DEGREE);
        solo.assertCurrentActivity("Expected Degree Info Activity", DegreeInfoActivity.class);

        //Verify degree info is shown
        assertTrue (CSM_DEGREE + " degree name was not found", solo.searchText(CSM_DEGREE));
        assertTrue (CSM_DEGREE + " required credit hours was not found", solo.searchText("" + CSM_CREDIT_HOURS));
        assertTrue (CSM_DEGREE + " required GPA was not found", solo.searchText("" + CSM_GPA));

        //Verify required courses are shown - test a few
        assertTrue ("Required course " + COURSE_1 + " was not found", solo.searchText(COURSE_1));
        assertTrue ("Required course " + COURSE_2 + " was not found", solo.searchText(COURSE_2));
        assertTrue ("Required course " + COURSE_3 + " was not found", solo.searchText(COURSE_3));
        assertTrue ("Required course " + COURSE_4 + " was not found", solo.searchText(COURSE_4));
        assertFalse ("Non-required course " + WRONG_COURSE + " was found", solo.searchText(WRONG_COURSE));
    }

    @Test
    public void testViewCSHonoursRequirements() {
        final String CSH_DEGREE = "Computer Science Honours";
        final double CSH_CREDIT_HOURS = 120.0;
        final double CSH_GPA = 3.0;

        //Navigate to degree information
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.degreeInfo));
        solo.assertCurrentActivity("Expected Degrees Activity", DegreesActivity.class);

        //Verify Computer Science Honours is shown
        assertTrue (CSH_DEGREE + " degree was not found", solo.searchText(CSH_DEGREE));

        //Navigate to Computer Science Honours
        solo.clickOnText(CSH_DEGREE);
        solo.assertCurrentActivity("Expected Degree Info Activity", DegreeInfoActivity.class);

        //Verify degree info is shown
        assertTrue (CSH_DEGREE + " degree name was not found", solo.searchText(CSH_DEGREE));
        assertTrue (CSH_DEGREE + " required credit hours was not found", solo.searchText("" + CSH_CREDIT_HOURS));
        assertTrue (CSH_DEGREE + " required GPA was not found", solo.searchText("" + CSH_GPA));

        //Verify required courses are shown - test a few
        assertTrue ("Required course " + COURSE_1 + " was not found", solo.searchText(COURSE_1));
        assertTrue ("Required course " + COURSE_2 + " was not found", solo.searchText(COURSE_2));
        assertTrue ("Required course " + COURSE_3 + " was not found", solo.searchText(COURSE_3));
        assertTrue ("Required course " + COURSE_4 + " was not found", solo.searchText(COURSE_4));
        assertFalse ("Non-required course " + WRONG_COURSE + " was found", solo.searchText(WRONG_COURSE));
    }
}
