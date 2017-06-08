package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.UserDefinedCourse;

/**
 * This class is used to test all of the behaviours of a
 * UserDefinedCourse object.
 */

public class UserDefinedCourseTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a UserDefinedCourse. It checks both variables
     * in the UserDefinedCourse and those of its superclass, Course.
     */

    @Test
    public void testConstructor() {
        final double DELTA = 0;

        System.out.println("\nStarting UserDefinedCourse Test: constructor");

        UserDefinedCourse udc = new UserDefinedCourse(123, "Test Name", 3.0, "TEST 1111");
        assertNotNull(udc);
        assertEquals("ID was assigned incorrectly", 123, udc.getId());
        assertEquals("Name was assigned incorrectly", "Test Name", udc.getName());
        assertEquals("Credit Hours was assigned incorrectly", 3.0, udc.getCreditHours(), DELTA);
        assertEquals("Full Abbreviation was assigned incorrectly",
                "TEST 1111", udc.getFullAbbreviation());

        System.out.println("Finished UserDefinedCourse Test: constructor");
    }
}
