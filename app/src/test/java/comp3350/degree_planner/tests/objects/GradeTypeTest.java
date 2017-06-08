package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.GradeType;

/**
 * This class is used to test all of the behaviours of a
 * GradeType object.
 */

public class GradeTypeTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a GradeType.
     */

    @Test
    public void testConstructor() {
        final double DELTA = 0; //Used for stating precision of assertEquals for 2 doubles
        //Currently it's set to 0 which means the 2 doubles have to be the exact same

        System.out.println("\nStarting GradeType Test: constructor");

        GradeType gt = new GradeType(1, "A+", 4.5);
        assertNotNull(gt);
        assertEquals("ID was assigned incorrectly", 1, gt.getId());
        assertEquals("Name was assigned incorrectly", "A+", gt.getName());
        assertEquals("Points was assigned incorrectly", 4.5, gt.getPoints(), DELTA);

        System.out.println("Finished GradeType Test: constructor");
    }
}
