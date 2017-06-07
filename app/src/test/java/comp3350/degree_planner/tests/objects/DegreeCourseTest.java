package comp3350.degree_planner.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.degree_planner.objects.DegreeCourse;

/**
 * This class is used to test all of the behaviours of a
 * DegreeCourse object.
 */

public class DegreeCourseTest extends TestCase {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a DegreeCourse.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting DegreeCourse Test: constructor");

        DegreeCourse dc = new DegreeCourse(1, 2, 3);
        assertNotNull(dc);
        assertEquals("Degree ID was assigned incorrectly", 1, dc.getDegreeId());
        assertEquals("Course ID was assigned incorrectly", 2, dc.getCourseId());
        assertEquals("Degree Course Type ID was assigned incorrectly", 3, dc.getDegreeCourseTypeId());

        System.out.println("\nFinished DegreeCourse Test: constructor");
    }
}
