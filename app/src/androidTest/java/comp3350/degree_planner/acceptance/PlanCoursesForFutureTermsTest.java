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
import comp3350.degree_planner.presentation.AddCourseActivity;
import comp3350.degree_planner.presentation.AddFreeElectiveActivity;
import comp3350.degree_planner.presentation.CoursePlansActivity;
import comp3350.degree_planner.presentation.CreateElectiveActivity;
import comp3350.degree_planner.presentation.DegreeInfoActivity;
import comp3350.degree_planner.presentation.MainActivity;
import comp3350.degree_planner.presentation.ViewElectivesActivity;

/**
 * For removing courses, solo.clickOnButton(0); will click the remove course button.
 * Therefore, if you need to change it, the number in the () has to be a even number.
 * Example:
 * Row 1: 0
 * Row 2: 2
 * Row 3: 4 and so on..
 * Odd number for move button!~
 */
@RunWith(AndroidJUnit4.class)
public class PlanCoursesForFutureTermsTest {
    private static final String COURSE_1 = "Cultural Anthropology";
    private static final String COURSE_2 = "Introductory Computer Science I";
    private static final String COURSE_3 = "Introductory Computer Science II";
    private static final String CSM_DEGREE = "Computer Science Major";
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
    public void testRemoveCourse1() throws Exception { // Removes cultural Anthropology from course plan
        final String COURSE_1_YEAR = "2017";
        final String COURSE_1_TERM = "Fall";

        //Navigate to Course Planning
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.coursePlan));
        solo.assertCurrentActivity("Expected Course Planning Activity", CoursePlansActivity.class);

        assertTrue (COURSE_1 + " course was not found", solo.searchText(COURSE_1));
        assertTrue (COURSE_2 + " course was not found", solo.searchText(COURSE_2));
        assertTrue (COURSE_3 + " course was not found", solo.searchText(COURSE_3));

        solo.clickOnView(solo.getView(R.id.move_toolbar));

        solo.clickOnButton(0); // Click on first course remove button (Cultural Anthropology)
        assertTrue (COURSE_1 + " remove course confirmation dialog did not appear", solo.searchText(COURSE_1));
        solo.clickOnButton(solo.getString(R.string.remove));

        assertFalse (COURSE_1 + " course was found", solo.searchText(COURSE_1));
        assertTrue (COURSE_2 + " course was not found", solo.searchText(COURSE_2));
        assertTrue (COURSE_3 + " course was not found", solo.searchText(COURSE_3));

        // Clean up, add Cultural Anthropology back to course plan
        solo.clickOnImageButton(0); // Click on float menu add course
        solo.clickOnView(solo.getView(R.id.add_free_elective_to_plan));
        solo.clickOnText(COURSE_1);
        solo.enterText(0, COURSE_1_YEAR);
        solo.enterText(1, COURSE_1_TERM);
        solo.clickOnButton(solo.getString(R.string.add));
    }

    @Test
    public void testRemoveCourse2() throws Exception { // Removes Introductory Computer Science II from course plan
        final String COURSE_3_YEAR = "2018";
        final String COURSE_3_TERM = "Summer";

        // Navigate to Course Planning
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.coursePlan));
        solo.assertCurrentActivity("Expected Course Planning Activity", CoursePlansActivity.class);

        // Course plans should exist before removal
        assertTrue (COURSE_1 + " course was not found", solo.searchText(COURSE_1));
        assertTrue (COURSE_2 + " course was not found", solo.searchText(COURSE_2));
        assertTrue (COURSE_3 + " course was not found", solo.searchText(COURSE_3));

        solo.clickOnView(solo.getView(R.id.move_toolbar));

        solo.clickOnButton(4); //click on course remove button in Introductory Computer Science II row
        assertTrue (COURSE_3 + " remove course confirmation dialog did not appear", solo.searchText(COURSE_3));
        solo.clickOnButton(solo.getString(R.string.remove));

        assertTrue (COURSE_1 + " course was not found", solo.searchText(COURSE_1));
        assertTrue (COURSE_2 + " course was not found", solo.searchText(COURSE_2));
        assertFalse (COURSE_3 + " course was found", solo.searchText(COURSE_3));

        // Clean up, add Comp 1020 back to course plan
        solo.clickOnImageButton(0); // Click on float menu add course
        solo.clickOnView(solo.getView(R.id.add_compsci_course_to_plan));
        solo.clickOnText(CSM_DEGREE);
        solo.clickOnText(COURSE_3);
        solo.enterText(0, COURSE_3_YEAR);
        solo.enterText(1, COURSE_3_TERM);
        solo.clickOnButton(solo.getString(R.string.add));
    }

    @Test
    public void testAddDegreeCourseToCoursePlan() throws Exception {
        final String COURSE_TO_ADD = "Software Engineering I";
        final String COURSE_TO_ADD_ABBREV = "COMP 3350";
        final String ADD_TO_YEAR = "2018";
        final String ADD_TO_TERM = "Winter";
        final String NEW_HEADER = ADD_TO_TERM + " " + ADD_TO_YEAR;

        // Navigate to Course Planning
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.coursePlan));
        solo.assertCurrentActivity("Expected Course Planning Activity", CoursePlansActivity.class);

        // Softeng I should not exist before adding to course plann
        assertFalse(COURSE_TO_ADD + " already in course plan before adding", solo.searchText(COURSE_TO_ADD));

        // Add SoftEng to course plan
        solo.clickOnImageButton(0); // Click on float menu add course
        solo.clickOnView(solo.getView(R.id.add_compsci_course_to_plan));
        solo.clickOnText(CSM_DEGREE);
        solo.clickOnText(COURSE_TO_ADD);

        // Navigate to add course page
        solo.assertCurrentActivity("Expect AddCourseActivity", AddCourseActivity.class);

        // Test if the blue back button goes back to course list for comp sci major
        solo.clickOnButton(solo.getString(R.string.cancel));
        solo.assertCurrentActivity("Expected DegreeInfoActivity", DegreeInfoActivity.class);
        solo.clickOnText(COURSE_TO_ADD); // Select softeng I again

        // Check basic page content
        assertTrue("Course name " + COURSE_TO_ADD + " not found", solo.searchText(COURSE_TO_ADD));
        assertTrue("Course abbreviation not found", solo.searchText(COURSE_TO_ADD_ABBREV));

        // Check empty year
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display warning when year field is empty", solo.searchText(solo.getString(R.string.error_no_year)));

        // Enter year and then check empty term
        solo.enterText(0, ADD_TO_YEAR);
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display warning when term field is empty", solo.searchText(solo.getString(R.string.error_no_term)));

        // Enter invalid term
        solo.enterText(1, "gibberish");
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display warning on invalid term", solo.searchText(solo.getString(R.string.error_invalid_term)));

        // Enter valid term and add course to plan
        solo.clearEditText(1);
        solo.enterText(1, ADD_TO_TERM);
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display success message when course is added", solo.searchText(solo.getString(R.string.required_course_added)));

        // Test if go to course plan button shows up on add success
        assertTrue("Expected button for navigating to courseplan activity", solo.searchText(solo.getString(R.string.viewcourseplan)));

        // Navigate back to view course plan
        solo.clickOnButton(solo.getString(R.string.viewcourseplan));
        solo.waitForActivity("CoursePlansActivity");

        // Test if Softeng I and Winter 2018 term show up in course plan
        assertTrue(COURSE_TO_ADD + " should appear in course plan", solo.searchText(COURSE_TO_ADD));
        assertTrue(NEW_HEADER + " should appear in course plan", solo.searchText(NEW_HEADER));

        // Clean up, remove Softeng I from course plan
        solo.clickOnView(solo.getView(R.id.move_toolbar));
        solo.clickOnButton(4); // Remove softeng I
        solo.clickOnButton(solo.getString(R.string.remove));
    }

    @Test
    public void testMoveCoursePlanToAnotherTerm() throws Exception {
        final String FROM_YEAR = "2017";
        final String FROM_TERM = "Fall";
        final String MOVE_TO_YEAR = "2019";
        final String MOVE_TO_TERM = "Fall";
        final String NEW_HEADER = MOVE_TO_TERM + " " + MOVE_TO_YEAR;

        // Navigate to Course Planning
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.coursePlan));
        solo.assertCurrentActivity("Expected Course Planning Activity", CoursePlansActivity.class);

        // Test move course plan Introductory Computer Science I
        solo.clickOnView(solo.getView(R.id.move_toolbar));
        solo.clickOnButton(3); // Move course plan comp 1010
        assertTrue("Expected input dialog for entering new term", solo.searchText(solo.getString(R.string.move_plan_to)));
        solo.enterText(0, MOVE_TO_YEAR);
        solo.enterText(1, MOVE_TO_TERM);
        solo.clickOnButton(solo.getString(R.string.confirm));
        assertTrue("Expect course to appear under " + NEW_HEADER, solo.searchText(NEW_HEADER));

        // Clean up move Comp 1010 back to Fall 2017
        solo.clickOnButton(5); // Click move button on Comp1010 row
        solo.enterText(0, FROM_YEAR);
        solo.enterText(1, FROM_TERM);
        solo.clickOnButton(solo.getString(R.string.confirm));
    }

    @Test
    public void testAddFreeElectiveToCoursePlan() throws Exception {
        final String COURSE_TO_ADD = "Language and Culture";
        final String ADD_TO_YEAR = "2018";
        final String ADD_TO_TERM = "Summer";
        final String HEADER = ADD_TO_TERM + " " + ADD_TO_YEAR;

        // Navigate to Course Planning
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.coursePlan));
        solo.assertCurrentActivity("Expected Course Planning Activity", CoursePlansActivity.class);

        //  should not exist before adding to course plan
        assertFalse(COURSE_TO_ADD + " already in course plan before adding", solo.searchText(COURSE_TO_ADD));

        // Add Language and Culture to course plan
        solo.clickOnImageButton(0); // Click on float menu add course
        solo.clickOnView(solo.getView(R.id.add_free_elective_to_plan));

        // Navigate to view electives page
        solo.assertCurrentActivity("Expect View Electives page", ViewElectivesActivity.class);
        solo.clickOnText(COURSE_TO_ADD);

        // Navigate to add elective page
        solo.assertCurrentActivity("Expect Add Free Elective page", AddFreeElectiveActivity.class);

        // Test if the blue back button goes back to elective list
        solo.clickOnButton(solo.getString(R.string.cancel));
        solo.assertCurrentActivity("Expect View Electives page", ViewElectivesActivity.class);
        solo.clickOnText(COURSE_TO_ADD); // Select elective again

        // Check basic page content
        assertTrue("Course name " + COURSE_TO_ADD + " not found", solo.searchText(COURSE_TO_ADD));

        // Check empty year
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display warning when year field is empty", solo.searchText(solo.getString(R.string.error_no_year)));

        // Enter year and then check empty term
        solo.enterText(0, ADD_TO_YEAR);
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display warning when term field is empty", solo.searchText(solo.getString(R.string.error_no_term)));

        // Enter invalid term
        solo.enterText(1, "gibberish");
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display warning on invalid term", solo.searchText(solo.getString(R.string.error_invalid_term)));

        // Enter valid term and add course to plan
        solo.clearEditText(1);
        solo.enterText(1, ADD_TO_TERM);
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display success message when course is added", solo.searchText(solo.getString(R.string.required_course_added)));

        // Test if go to course plan button shows up on add success
        assertTrue("Expected button for navigating to courseplan activity", solo.searchText(solo.getString(R.string.viewcourseplan)));

        // Navigate back to view course plan
        solo.clickOnButton(solo.getString(R.string.viewcourseplan));
        solo.waitForActivity("CoursePlansActivity");

        // Test if new elective and header show up in course plan
        assertTrue(COURSE_TO_ADD + " should appear in course plan", solo.searchText(COURSE_TO_ADD));
        assertTrue(HEADER + " should appear in course plan", solo.searchText(HEADER));

        // Clean up, remove the elective from course plan
        solo.clickOnView(solo.getView(R.id.move_toolbar));
        solo.clickOnButton(4); // Remove elective
        solo.clickOnButton(solo.getString(R.string.remove));
    }

    @Test
    public void testCreateFreeElective() throws Exception {
        final String COURSE_TO_ADD = "Introduction to Economics";
        final String CREDIT_HOURS = "3";
        final String ABBREVIATION = "ECON 1010";

        // Navigate to View Electives
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.coursePlan));
        solo.clickOnImageButton(0);
        solo.clickOnView(solo.getView(R.id.add_free_elective_to_plan));

        //  New elective should not exist before creating one
        assertFalse(COURSE_TO_ADD + " already before adding", solo.searchText(COURSE_TO_ADD));

        // Navigate to create elective page
        solo.clickOnImageButton(0);
        solo.assertCurrentActivity("Expect create elective page", CreateElectiveActivity.class);

        // Check empty course name
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display warning when name field is empty", solo.searchText(solo.getString(R.string.error_no_name)));

        // Check negative credit hours
        solo.enterText(0, COURSE_TO_ADD);
        solo.enterText(1, "-5");
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display warning on invalid credit hours", solo.searchText(solo.getString(R.string.error_invalid_credit_hours)));

        // Check empty abbreviation
        solo.clearEditText(1);
        solo.enterText(1, CREDIT_HOURS);
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display warning when abbrev field is empty", solo.searchText(solo.getString(R.string.error_no_abbrev)));

        // Enter valid abbrev
        solo.enterText(2, ABBREVIATION);
        solo.clickOnButton(solo.getString(R.string.add));
        assertTrue("Expected toast to display success message when new elective is added", solo.searchText(solo.getString(R.string.elective_added)));

        // Navigate to create elective page
        solo.clickOnButton(solo.getString(R.string.viewelectives));
        solo.assertCurrentActivity("Expect free electives list", ViewElectivesActivity.class);
        assertTrue("Expect new elective to show up in list", solo.searchText(COURSE_TO_ADD));

        // Clean up, remove the new elective
        solo.clickOnView(solo.getView(R.id.garbagebin_toolbar));
        solo.clickOnButton(2);
        solo.clickOnButton(solo.getString(R.string.remove));
    }

    @Test
    public void testRemoveElective() throws Exception {
        final String COURSE_TO_ADD = "Introduction to Economics";
        final String CREDIT_HOURS = "3";
        final String ABBREVIATION = "ECON 1010";
        final String ADD_TO_YEAR = "2019";
        final String ADD_TO_TERM = "Winter";
        final String HEADER = ADD_TO_TERM + " " + ADD_TO_YEAR;

        // Navigate to View Electives
        solo.unlockScreen();
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.coursePlan));
        solo.clickOnImageButton(0);
        solo.clickOnView(solo.getView(R.id.add_free_elective_to_plan));

        // Navigate to create elective page
        solo.clickOnImageButton(0);
        solo.assertCurrentActivity("Expect create elective page", CreateElectiveActivity.class);

        solo.enterText(0, COURSE_TO_ADD);
        solo.enterText(1, CREDIT_HOURS);
        solo.enterText(2, ABBREVIATION);
        solo.clickOnButton(solo.getString(R.string.add));

        // Create new elective
        solo.clickOnButton(solo.getString(R.string.viewelectives));
        solo.assertCurrentActivity("Expect free electives list", ViewElectivesActivity.class);
        assertTrue("Expect new elective to show up in list", solo.searchText(COURSE_TO_ADD));

        // Add it to course plan
        solo.clickOnText(COURSE_TO_ADD);
        solo.enterText(0, ADD_TO_YEAR);
        solo.enterText(1, ADD_TO_TERM);
        solo.clickOnButton(solo.getString(R.string.add));
        solo.clickOnButton(solo.getString(R.string.viewcourseplan));

        solo.waitForActivity("CoursePlansActivity");
        // Check if elective is added to course plan
        assertTrue("Expect elective to appear in " + HEADER, solo.searchText(COURSE_TO_ADD));
        assertTrue(HEADER + " not found", solo.searchText(HEADER));

        // Remove elective
        solo.clickOnImageButton(0);
        solo.clickOnView(solo.getView(R.id.add_free_elective_to_plan));
        solo.clickOnView(solo.getView(R.id.garbagebin_toolbar));
        solo.clickOnButton(2);
        solo.clickOnButton(solo.getString(R.string.remove));

        // Press Home button
        solo.clickOnView(solo.getView(R.id.home_toolbar));
        solo.waitForActivity("MainActivity");
        solo.clickOnButton(solo.getString(R.string.coursePlan));
        // Check if the removed elective and the term it's in are still in course plan
        assertFalse("Expect elective to no longer appear in " + HEADER, solo.searchText(COURSE_TO_ADD));
        assertFalse(HEADER + " shoud not exist", solo.searchText(HEADER));
    }

}
