package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.CoursePlan;

/**
 * This class is used to test all of the behaviours of a
 * CoursePlan object.
 */

public class CoursePlanTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a CoursePlan.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting CoursePlan Test: constructor");

        CoursePlan cp = new CoursePlan(1, 123, 456, 2, 2018);
        assertNotNull(cp);
        assertEquals("Course ID was assigned incorrectly", 123, cp.getCourseId());
        assertEquals("Student ID was assigned incorrectly", 456, cp.getStudentId());
        assertEquals("Term Type ID was assigned incorrectly", 2, cp.getTermTypeId());
        assertEquals("Year was assigned incorrectly", 2018, cp.getYear());

        System.out.println("Finished CoursePlan Test: constructor");
    }
}
