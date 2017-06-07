package comp3350.degree_planner.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.degree_planner.objects.Degree;

/**
 * This class is used to test all of the behaviours of a
 * Degree object.
 */

public class DegreeTest extends TestCase {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a Degree.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting Degree Test: constructor");

        Degree d = new Degree(1, "Test Degree", 12.0, 9.0, 2.0);
        assertNotNull(d);
        assertEquals("ID was assigned incorrectly", 1, d.getId());
        assertEquals("Name was assigned incorrectly", "Test Degree", d.getName());
        assertEquals("Credit Hours was assigned incorrectly", 12.0, d.getCreditHours());
        assertEquals("Major Credit Hours was assigned incorrectly", 9.0, d.getMajorCreditHours());
        assertEquals("GPA Required was assigned incorrectly", 2.0, d.getGpaRequired());

        System.out.println("\nFinished Degree Test: constructor");
    }
}
