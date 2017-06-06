package comp3350.degree_planner.tests.business;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.business.AccessCourseResult;

/**
 * Created by Tiffany Jiang on 2017-06-05.
 */

public class ChangeCoursePlanTest extends TestCase {
    private AccessCoursePlan testAccessCoursePlan;

    @Before
    public void setUp() {
        Services.createDataAccess(); //?
        testAccessCoursePlan = new AccessCoursePlan();
    }

    @Test
    public void testAddInvalidData() {
        assertFalse ("Adding to course plan did not fail", testAccessCoursePlan.addToCoursePlan(-1, -1, -1, -1));
    }

    public void testAddValidData() {
        assertTrue ("Adding to course plan did not pass", testAccessCoursePlan.addToCoursePlan(1, 1, 1, 2018));
    }

    public void testMoveInvalidData() {
        assertFalse ("Moving to a different term did not fail", testAccessCoursePlan.moveCourse(-1, -1, -1));
    }

    public void testMoveToInvalidTerm() {
        assertFalse (testAccessCoursePlan.moveCourse(1, 3, 2018));
    }

    public void testMoveValidData() {
        assertTrue (testAccessCoursePlan.moveCourse(1, 1, 2018));
    }

    public void testRemoveInvalidData() {
        assertTrue (testAccessCoursePlan.removeFromCoursePlan(-1));
    }

    public void testRemoveValidData() {
        assertTrue(testAccessCoursePlan.removeFromCoursePlan(1));
    }
}
