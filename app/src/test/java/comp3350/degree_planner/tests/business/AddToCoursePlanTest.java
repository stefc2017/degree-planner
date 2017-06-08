package comp3350.degree_planner.tests.business;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCoursePlan;

/**
 * Created by Tiffany Jiang on 2017-06-05.
 */

public class AddToCoursePlanTest {
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

    @Test
    public void testAddValidData() {
        assertTrue ("Adding to course plan did not pass", testAccessCoursePlan.addToCoursePlan(1, 1, 1, 2018));
    }
}
