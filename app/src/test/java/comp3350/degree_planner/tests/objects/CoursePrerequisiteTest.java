package comp3350.degree_planner.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.degree_planner.objects.CoursePrerequisite;

/**
 * This class is used to test all of the behaviours of a
 * CoursePrerequisite object.
 */

public class CoursePrerequisiteTest extends TestCase {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a CoursePrerequisite.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting CoursePrerequisite Test: constructor");

        CoursePrerequisite cp = new CoursePrerequisite(2, 1);
        assertNotNull(cp);
        assertEquals("Course ID was assigned incorrectly", 2, cp.getCourseId());
        assertEquals("Prerequisite Course ID was assigned incorrectly", 1, cp.getPrereqCourseId());

        System.out.println("\nFinished CoursePrerequisite Test: constructor");
    }
}
