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
import comp3350.degree_planner.presentation.CoursePlansActivity;
import comp3350.degree_planner.presentation.DegreesActivity;
import comp3350.degree_planner.presentation.MainActivity;

/**
 * For removing courses, solo.clickOnButton(0); will click the remove course button.
 * Therefore, if you need to change it, the number in the () has to be a even number.
 * Example:
 * Row 1: 0
 * Row 2: 2
 * Row 3: 4 and so on..
 */
@RunWith(AndroidJUnit4.class)
public class PlanCoursesForFutureTermsTest {
    private static final String COURSE_1 = "Cultural Anthropology";
    private static final String COURSE_2 = "Introductory Computer Science I";
    private static final String COURSE_3 = "Introductory Computer Science II";

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
    public void testRemoveCourse1() throws Exception { //removes cultural anthropology from course plan

        //Navigate to Course Planning
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.coursePlan));
        solo.assertCurrentActivity("Expected Course Planning Activity", CoursePlansActivity.class);

        assertTrue (COURSE_1 + " course was not found", solo.searchText(COURSE_1));
        assertTrue (COURSE_2 + " course was not found", solo.searchText(COURSE_2));
        assertTrue (COURSE_3 + " course was not found", solo.searchText(COURSE_3));

        solo.clickOnView(solo.getView(R.id.move_toolbar));

        solo.clickOnButton(0); //click on first course remove button (Cultural anthropology)
        assertTrue (COURSE_1 + " remove course confirmation dialog did not appear", solo.searchText(COURSE_1));
        solo.clickOnButton(solo.getString(R.string.remove));

        assertFalse (COURSE_1 + " course was found", solo.searchText(COURSE_1));
        assertTrue (COURSE_2 + " course was not found", solo.searchText(COURSE_2));
        assertTrue (COURSE_3 + " course was not found", solo.searchText(COURSE_3));
    }

    @Test
    public void testRemoveCourse2() throws Exception { //removes Introductory Computer Science I from course plan

        //Navigate to Course Planning
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.coursePlan));
        solo.assertCurrentActivity("Expected Course Planning Activity", CoursePlansActivity.class);

        assertFalse (COURSE_1 + " course was found", solo.searchText(COURSE_1));
        assertTrue (COURSE_2 + " course was not found", solo.searchText(COURSE_2));
        assertTrue (COURSE_3 + " course was not found", solo.searchText(COURSE_3));

        solo.clickOnView(solo.getView(R.id.move_toolbar));

        solo.clickOnButton(2); //click on second course remove button (Introductory Computer Science II)
        assertTrue (COURSE_2 + " remove course confirmation dialog did not appear", solo.searchText(COURSE_2));
        solo.clickOnButton(solo.getString(R.string.remove));

        assertFalse (COURSE_1 + " course was found", solo.searchText(COURSE_1));
        assertTrue (COURSE_2 + " course was not found", solo.searchText(COURSE_2));
        assertFalse (COURSE_3 + " course was found", solo.searchText(COURSE_3));

        solo.assertCurrentActivity("Expected Course Planning Activity", CoursePlansActivity.class);
    }
}
