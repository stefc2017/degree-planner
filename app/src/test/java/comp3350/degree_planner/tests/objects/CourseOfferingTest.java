package comp3350.degree_planner.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.degree_planner.objects.CourseOffering;

/**
 * This class is used to test all of the behaviours of a
 * CourseOffering object.
 */

public class CourseOfferingTest extends TestCase {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a CourseOffering.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting CourseOffering Test: constructor");

        CourseOffering co = new CourseOffering(123, 3);
        assertNotNull(co);
        assertEquals("Course ID was assigned incorrectly", 123, co.getCourseId());
        assertEquals("Term Type ID was assigned incorrectly", 3, co.getTermTypeId());

        System.out.println("\nFinished CourseOffering Test: constructor");
    }
}
