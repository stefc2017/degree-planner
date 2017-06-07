package comp3350.degree_planner.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.degree_planner.objects.DegreeCourseType;

/**
 * This class is used to test all of the behaviours of a
 * DegreeCourseType object.
 */

public class DegreeCourseTypeTest extends TestCase {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a DegreeCourseType.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting DegreeCourseType Test: constructor");

        DegreeCourseType dct = new DegreeCourseType(12, "Test Type");
        assertNotNull(dct);
        assertEquals("ID was assigned incorrectly", 12, dct.getId());
        assertEquals("Name was assigned incorrectly", "Test Type", dct.getName());

        System.out.println("\nFinished DegreeCourseType Test: constructor");
    }
}
