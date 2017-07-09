package comp3350.degree_planner.tests.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.business.AccessDegrees;
import comp3350.degree_planner.business.CompletedCourses;
import comp3350.degree_planner.business.CreditHours;
import comp3350.degree_planner.business.Season;
import comp3350.degree_planner.business.exceptions.CourseAlreadyPlannedForTermException;
import comp3350.degree_planner.business.exceptions.CourseDoesNotExistException;
import comp3350.degree_planner.business.exceptions.CourseNotOfferedInTermException;
import comp3350.degree_planner.business.exceptions.CoursePlanDoesNotExistException;
import comp3350.degree_planner.business.exceptions.StudentDoesNotExistException;
import comp3350.degree_planner.business.exceptions.TermTypeDoesNotExistException;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.persistence.DataAccess;

/**
 * Created by Tiffany Jiang on 2017-07-07.
 *
 * Integration tests for business layer. Each method tests a business class/method using the real db.
 */

public class BusinessPersistenceSeamTest {
    private DataAccess dataAccess;

    @Before
    public void setUp() {
        Services.closeDataAccess();
        System.out.println();
        Services.createDataAccess(Main.dbName);
        dataAccess = Services.getDataAccess();
    }

    @After
    public void takeDown() {
        Services.closeDataAccess();
    }

    @Test
    public void testCompletedCourses() throws Exception {
        final int NB_COMPLETED_COURSES_FOR_STUDENT_1 = 2;

        System.out.println("Starting Integration test of CompletedCourses to persistence");

        CompletedCourses cc = new CompletedCourses(dataAccess);

        System.out.println("\nStarting Get Completed Courses Test: invalid student id");
        assertEquals("Completed courses list was not empty", cc.getCompletedCourses(-1).size(), 0);
        System.out.println("Finished Get Completed Courses Test: invalid student id");

        System.out.println("\nStarting Get Completed Courses Test: valid student id and empty course results");
        assertEquals("Completed courses list was not empty", cc.getCompletedCourses(2).size(), 0);
        System.out.println("Finished Get Completed Courses Test: valid student id and empty course results");

        System.out.println("\nStarted Get Completed Courses Test: valid student id and non-empty course results");
        List<CourseResult> courseResults = cc.getCompletedCourses(1);
        assertNotNull("Completed courses array is null", courseResults);
        assertEquals("Result length is not the same as expected result length", NB_COMPLETED_COURSES_FOR_STUDENT_1, courseResults.size());
        for (int i = 0; i < NB_COMPLETED_COURSES_FOR_STUDENT_1; i++) {
            assertEquals("Expected result and result are not equal at index " + i, courseResults.get(i).getCourse().getId(), i+1);
        }
        System.out.println("Finished Get Completed Courses Test: valid student id and non-empty course results");

        System.out.println("\nFinished Integration test of CompletedCourses to persistence");
    }

    @Test
    public void testAccessDegrees() {
        System.out.println("Starting Integration test of AccessDegrees to persistence");

        AccessDegrees degrees = new AccessDegrees(dataAccess);

        System.out.println("\nStarting Get Degrees Test: Get all available degree programs");
        assertNotNull("Degree list should not be null", degrees.getAllDegrees());
        assertEquals("Degree list should have size one", 1, degrees.getAllDegrees().size());
        assertEquals("Wrong degree in degree list", "Computer Science Major", degrees.getAllDegrees().get(0).getName());
        System.out.println("Finished Get Degrees Test: Get all available degree programs");

        System.out.println("\nStarting Get Degrees Test: Get degree by invalid degree Id");
        assertNull("Returned Degree should be null when degreeId is invalid", degrees.getDegreeById(-5));
        System.out.println("Finished Get Degrees Test: Get degree by invalid degree Id");

        System.out.println("\nStarting Get Degrees Test: Get degree by valid degree Id");
        assertNotNull("Returned Degree should not be null", degrees.getDegreeById(1));
        assertEquals("Wrong degree returned", "Computer Science Major", degrees.getDegreeById(1).getName());
        System.out.println("Finished Get Degrees Test: Get degree by valid degree Id");

        System.out.println("\nFinished Integration test of AccessDegrees to persistence");
    }

    @Test
    public void testCreditHours() {
        //*
        final int NB_NONREQ_CREDITHOURS = 6;
        final int NB_REQ_CREDITHOURS = 6;
        int result;

        System.out.println("Starting Integration test of CreditHours to persistence");
        CreditHours creditHours = new CreditHours(dataAccess);

        System.out.println("\nStarting Credit Hours Test: invalid student id");
        result = creditHours.getCreditHoursTaken(-1);
        assertEquals( 0 , result);
        System.out.println("Finished Credit Hours Test: invalid student id");

        System.out.println("\nStarting Credit Hours Test: invalid degree id");
        result = creditHours.getRequiredCreditHoursTaken( 1, -1 );
        assertEquals( 0 , result);
        System.out.println("Finished Credit Hours Test: invalid degree id");

        System.out.println("\nStarting Credit Hours Test: valid student id and empty course list");
        result = creditHours.getCreditHoursTaken(2);
        assertEquals( 0 , result);
        System.out.println("Finished Credit Hours Test: valid student id and empty course list");

        System.out.println("\nStarted Credit Hours Test: valid student id and has taken courses");
        result = creditHours.getCreditHoursTaken( 1 );
        assertEquals( "Credit hours taken calculation value was not expected", NB_NONREQ_CREDITHOURS, result );
        result = creditHours.getRequiredCreditHoursTaken( 1, 1 );
        assertEquals( "Required Credit hours taken calculation value was not expected", NB_REQ_CREDITHOURS, result );
        System.out.println("Finished Credit Hours Test: valid student id and has taken courses");

        System.out.println("\nFinished Integration test of CreditHours to persistence");
    }

    //AccessCourses tests

    @Test
    public void testGetCourseOfferingsByTerm() {
        System.out.println("Starting Integration test of GetCourseOfferingsByTerm to persistence");
        AccessCourses accessCourses = new AccessCourses(dataAccess);

        System.out.println("\nStarting Get All Course Offerings: Get all available course offerings");
        assertNotNull("Course Offerings list should not be null", accessCourses.getAllCourseOfferings());
        assertEquals("Course Offerings list should have size nine", 9, accessCourses.getAllCourseOfferings().size());
        System.out.println("Finished Get Course Offerings Test: Get all available course offerings");

        System.out.println("\nStarting Get Course Offerings Test: Get Course Offerings by invalid Term vals");
        assertEquals("Returned CourseOfferingsByTerm list should be empty when TermId is invalid", 0, accessCourses.getCourseOfferingsByTerm(new TermType(4,"ImaginativeTerm")).size());
        assertEquals("Returned CourseOfferingsByTerm list should be empty when Term is null", 0, accessCourses.getCourseOfferingsByTerm(null).size());
        System.out.println("Finished Get Degrees Test: Get Course Offerings by invalid Term vals");

        System.out.println("\nStarting Get Course Offerings Test: Get Course Offerings by valid Term");
        assertNotNull("Returned Course Offerings list should not be null", accessCourses.getCourseOfferingsByTerm(new TermType(1,"Fall")));
        System.out.println("Finished Get Course Offerings Test: Get Course Offerings by valid Term");

        System.out.println("\nFinished Integration test of GetCourseOfferingsByTerm to persistence");
    }

    @Test
    public void testGetDegreeCourses() {
        String[] expectedCourses = {"Introductory Computer Science I", "Introductory Computer Science II"};

        System.out.println("Starting Integration test of GetDegreeCourses to persistence");
        AccessCourses accessCourses = new AccessCourses(dataAccess);

        System.out.println("\nStarting Get Degree Courses Test: Invalid degree Id");
        assertEquals("Course list should be empty when degreeId is invalid", 0, accessCourses.getDegreeCourses(-5).size());
        System.out.println("Finished Get Degree Courses Test: Invalid degree Id");

        //*
        System.out.println("\nStarting Get Degree Courses Test: Valid degree Id");
        assertEquals("Course missing: Size of course list should be four", 4, accessCourses.getDegreeCourses(1).size());
        assertEquals("First course does not belong to selected degree", expectedCourses[0],accessCourses.getDegreeCourses(1).get(0).getName());
        assertEquals("Second course does not belong to selected degree", expectedCourses[1],accessCourses.getDegreeCourses(1).get(1).getName());
        System.out.println("Finished Get Degree Courses Test: Valid degree Id");

        System.out.println("\nFinished Integration test of GetDegreeCourses to persistence");
    }

    //AccessCoursePlan tests

    @Test
    public void testGetCoursePlanAndHeaders() throws Exception {
        final int NB_LIST_SIZE_FOR_STUDENT_1 = 5;
        CoursePlan tempCP;  // Used for checking the ID's of CoursePlans
        List coursePlansAndHeaders;

        System.out.println("Starting Integration test of GetCoursePlanAndHeaders to persistence");
        AccessCoursePlan acp = new AccessCoursePlan(dataAccess);

        System.out.println("\nStarting GetCoursePlansAndHeaders Test: invalid student id");
        assertEquals("Completed courses list was not empty", 0, acp.getCoursePlansAndHeaders(-1).size());
        System.out.println("Finished GetCoursePlansAndHeaders Test: invalid student id");

        System.out.println("\nStarting GetCoursePlansAndHeaders Test: valid student id and empty course results");
        assertEquals("Completed courses list was not empty", 0, acp.getCoursePlansAndHeaders(2).size());
        System.out.println("Finished GetCoursePlansAndHeaders Test: valid student id and empty course results");


        //Testing valid data
        System.out.println("\nStarted GetCoursePlansAndHeaders Test: valid student id and non-empty course results");
        coursePlansAndHeaders = acp.getCoursePlansAndHeaders(1);

        assertNotNull("Course Plans and Headers array is null", coursePlansAndHeaders);
        assertEquals("Result length is not the same as expected result length",
                NB_LIST_SIZE_FOR_STUDENT_1, coursePlansAndHeaders.size());

        assertTrue("No starting header", coursePlansAndHeaders.get(0) instanceof ArrayList);
        assertEquals("Header at index 0 is incorrect", Season.FALL.getValue(), ((ArrayList)coursePlansAndHeaders.get(0)).get(0));

        assertTrue("Value at index 1 is not a CoursePlan", coursePlansAndHeaders.get(1) instanceof CoursePlan);
        tempCP = (CoursePlan)(coursePlansAndHeaders.get(1));
        assertEquals("CoursePlan at index 1 is incorrect", 3, tempCP.getId());

        assertTrue("Value at index 2 is not a CoursePlan", coursePlansAndHeaders.get(2) instanceof CoursePlan);
        tempCP = (CoursePlan)(coursePlansAndHeaders.get(2));
        assertEquals("CoursePlan at index 2 is incorrect", 2, tempCP.getId());

        assertTrue("Value at index 3 is not a header", coursePlansAndHeaders.get(3) instanceof ArrayList);
        assertEquals("Header at index 3 is incorrect", Season.SUMMER.getValue(), ((ArrayList)coursePlansAndHeaders.get(3)).get(0));

        assertTrue("Value at index 4 is not a CoursePlan", coursePlansAndHeaders.get(4) instanceof CoursePlan);
        tempCP = (CoursePlan)(coursePlansAndHeaders.get(4));
        assertEquals("CoursePlan at index 4 is incorrect", 1, tempCP.getId());

        System.out.println("Finished GetCoursePlansAndHeaders Test: valid student id and non-empty course results");


        System.out.println("\nFinished Integration test of GetCoursePlanAndHeaders to persistence");
    }

    @Test
    public void testAddToCoursePlan() throws Exception {
        CoursePlan added;
        final int SCIENCE_COURSE_ID = 2;
        final int STUDENT_ID = 1;
        final int TERM_TYPE_ID = 1;
        final int YEAR = 2018;
        final int USER_DEFINED_COURSE_ID = 4;
        final int USER_DEFINED_TERM_TYPE_ID = 2;

        System.out.println("Starting Integration test of AddToCoursePlan to persistence");
        AccessCoursePlan acp = new AccessCoursePlan(dataAccess);

        System.out.println("\nStarting Add to Course Plan Test: invalid course id");
        try {
            acp.addToCoursePlan(-1, 1, 1, 2018);
            fail ("Add to Course Plan with invalid course id did not throw exception");
        } catch (CourseDoesNotExistException e) {
        }
        System.out.println("Finished Add to Course Plan Test: invalid course id");

        System.out.println("\nStarting Add to Course Plan Test: invalid student id");
        try {
            acp.addToCoursePlan(2, -1, 1, 2018);
            fail ("Add to Course Plan with invalid student id did not throw exception");
        } catch (StudentDoesNotExistException e) {
        }
        System.out.println("Finished Add to Course Plan Test: invalid student id");

        System.out.println("\nStarting Add to Course Plan Test: invalid term type id");
        try {
            acp.addToCoursePlan(2, 1, -1, 2018);
            fail ("Add to Course Plan with invalid term type id did not throw exception");
        } catch (TermTypeDoesNotExistException e) {
        }
        System.out.println("Finished Add to Course Plan Test: invalid term type id");

        System.out.println("\nStarting Add to Course Plan Test: course not offered in term");
        try {
            acp.addToCoursePlan(4, 1, 1, 2018);
            fail ("Add a Course Plan to a term the course isn't offered in did not throw exception");
        } catch (CourseNotOfferedInTermException e) {
        }
        System.out.println("Finished Add to Course Plan Test: course not offered in term");

        System.out.println("\nStarting Add to Course Plan Test: course already planned in term");
        try {
            acp.addToCoursePlan(3, 1, 2, 2018);
            fail ("Add a duplicate Course Plan did not throw exception");
        } catch (CourseAlreadyPlannedForTermException e) {
        }
        System.out.println("Finished Add to Course Plan Test: course already planned in term");


        //Testing valid data

        System.out.println("\nStarting Add to Course Plan Test: valid data");

        //Testing science course
        acp.addToCoursePlan(SCIENCE_COURSE_ID, STUDENT_ID, TERM_TYPE_ID, YEAR);

        added = dataAccess.getCoursePlan(SCIENCE_COURSE_ID, STUDENT_ID, TERM_TYPE_ID, YEAR);
        assertNotNull("Course plan was not added", added);
        assertEquals ("Course IDs weren't equal", added.getCourse().getId(), SCIENCE_COURSE_ID);
        assertEquals ("Student IDs weren't equal", added.getStudent().getId(), STUDENT_ID);
        assertEquals ("Term Type IDs weren't equal", added.getTermType().getId(), TERM_TYPE_ID);
        assertEquals ("Years weren't equal", added.getYear(), YEAR);

        //Restore db to before this test
        acp.removeFromCoursePlan(added.getId());

        //Testing user-defined course
        acp.addToCoursePlan(USER_DEFINED_COURSE_ID, STUDENT_ID, USER_DEFINED_TERM_TYPE_ID, YEAR);

        added = dataAccess.getCoursePlan(USER_DEFINED_COURSE_ID, STUDENT_ID, USER_DEFINED_TERM_TYPE_ID, YEAR);
        assertNotNull("Course plan was not added", added);
        assertEquals ("Course IDs weren't equal", added.getCourse().getId(), USER_DEFINED_COURSE_ID);
        assertEquals ("Student IDs weren't equal", added.getStudent().getId(), STUDENT_ID);
        assertEquals ("Term Type IDs weren't equal", added.getTermType().getId(), USER_DEFINED_TERM_TYPE_ID);
        assertEquals ("Years weren't equal", added.getYear(), YEAR);

        //Restore db to before this test
        acp.removeFromCoursePlan(added.getId());

        System.out.println("Finished Add to Course Plan Test: valid data");


        System.out.println("\nFinished Integration test of AddToCoursePlan to persistence");
    }

    @Test
    public void testMoveCourse() throws Exception {
        CoursePlan coursePlan;
        final int COURSE_PLAN_ID = 1;
        final int TERM_TYPE_ID = 2;
        final int YEAR = 2019;

        System.out.println("Starting Integration test of MoveCourse to persistence");
        AccessCoursePlan acp = new AccessCoursePlan(dataAccess);

        System.out.println("\nStarting Move Course Test: invalid course plan id");
        try {
            acp.moveCourse(-1, 2, 2019);
            fail ("Moving a Course Plan with an invalid id did not throw exception");
        } catch (CoursePlanDoesNotExistException e) {
        }
        System.out.println("Finished Move Course Test: invalid course plan id");

        System.out.println("\nStarting Move Course Test: invalid term type id");
        try {
            acp.moveCourse(1, -1, 2019);
            fail ("Moving a Course Plan with an invalid term type id did not throw exception");
        } catch (TermTypeDoesNotExistException e) {
        }
        System.out.println("Finished Move Course Test: invalid term type id");

        System.out.println("\nStarting Move Course Test: course not offered in term");
        try {
            acp.moveCourse(1, 3, 2019);
            fail ("Moving a Course Plan to a term the course isn't offered in did not throw exception");
        } catch (CourseNotOfferedInTermException e) {
        }
        System.out.println("Finished Move Course Test: course not offered in term");

        System.out.println("\nStarting Move Course Test: course already planned in term");
        try {
            acp.moveCourse(1, 2, 2018);
            fail ("Moving a Course Plan to a duplicate term did not throw exception");
        } catch (CourseAlreadyPlannedForTermException e) {
        }
        System.out.println("Finished Move Course Test: course already planned in term");

        System.out.println("\nStarting Move Course Test: valid data");
        acp.moveCourse(COURSE_PLAN_ID, TERM_TYPE_ID, YEAR);
        coursePlan = dataAccess.getCoursePlan(COURSE_PLAN_ID);
        assertNotNull("Error occurred with modification", coursePlan);
        assertEquals("Term Type IDs weren't equal", coursePlan.getTermType().getId(), TERM_TYPE_ID);
        assertEquals ("Years weren't equal", coursePlan.getYear(), YEAR);
        acp.moveCourse(COURSE_PLAN_ID, 2, 2018); //Restore db to before this test
        System.out.println("Finished Move Course Test: valid data");

        System.out.println("\nFinished Integration test of MoveCourse to persistence");
    }

    @Test
    public void testRemoveFromCoursePlan() throws Exception {
        final int COURSE_PLAN_ID = 4;
        System.out.println("Starting Integration test of RemoveFromCoursePlan to persistence");
        AccessCoursePlan acp = new AccessCoursePlan(dataAccess);

        System.out.println("\nStarting Remove From Course Plan Test: invalid course plan id");
        try {
            acp.removeFromCoursePlan(-1);
            fail ("Removing a Course Plan with an invalid id did not throw exception");
        } catch (CoursePlanDoesNotExistException e) {
        }
        System.out.println("Finished Remove From Course Plan Test: invalid course plan id");

        System.out.println("\nStarting Remove From Course Plan Test: valid data");
        acp.addToCoursePlan(5, 1, 3, 2019); //So can restore db to before this test
        acp.removeFromCoursePlan(dataAccess.getCoursePlan (5, 1, 3, 2019).getId());
        assertNull("Removing a course plan was not successful", dataAccess.getCoursePlan(COURSE_PLAN_ID));
        System.out.println("Finished Remove From Course Plan Test: valid data");

        System.out.println("\nFinished Integration test of RemoveFromCoursePlan to persistence");
    }
}
