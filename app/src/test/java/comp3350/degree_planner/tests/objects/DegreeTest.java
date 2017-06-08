package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.Degree;

/**
 * This class is used to test all of the behaviours of a
 * Degree object.
 */

public class DegreeTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a Degree.
     */

    @Test
    public void testConstructor() {
        final double DELTA = 0; //Used for stating precision of assertEquals for 2 doubles
        //Currently it's set to 0 which means the 2 doubles have to be the exact same

        System.out.println("\nStarting Degree Test: constructor");

        Degree d = new Degree(1, "Test Degree", 12.0, 9.0, 2.0);
        assertNotNull(d);
        assertEquals("ID was assigned incorrectly", 1, d.getId());
        assertEquals("Name was assigned incorrectly", "Test Degree", d.getName());
        assertEquals("Credit Hours was assigned incorrectly", 12.0, d.getCreditHours(), DELTA);
        assertEquals("Major Credit Hours was assigned incorrectly", 9.0, d.getMajorCreditHours(), DELTA);
        assertEquals("GPA Required was assigned incorrectly", 2.0, d.getGpaRequired(), DELTA);

        System.out.println("Finished Degree Test: constructor");
    }
}
