package comp3350.degree_planner.tests.business;

/**
 * Created by tiffanyjiang on 2017-06-07.
 */

public class MoveCourseTest {


    public void testMoveInvalidData() {
        assertFalse ("Moving to a different term did not fail", testAccessCoursePlan.moveCourse(-1, -1, -1));
    }

    public void testMoveToInvalidTerm() {
        assertFalse (testAccessCoursePlan.moveCourse(1, 3, 2018));
    }

    public void testMoveValidData() {
        assertTrue (testAccessCoursePlan.moveCourse(1, 1, 2018));
    }
}
