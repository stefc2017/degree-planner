package comp3350.degree_planner.tests.business;

/**
 * Created by tiffanyjiang on 2017-06-07.
 */

public class RemoveFromCoursePlanTest {
    public void testRemoveInvalidData() {
        assertTrue (testAccessCoursePlan.removeFromCoursePlan(-1));
    }

    public void testRemoveValidData() {
        assertTrue(testAccessCoursePlan.removeFromCoursePlan(1));
    }
}
