package comp3350.degree_planner.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.degree_planner.objects.GradeType;

/**
 * This class is used to test all of the behaviours of a
 * GradeType object.
 */

public class GradeTypeTest extends TestCase {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a GradeType.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting GradeType Test: constructor");

        GradeType gt = new GradeType(1, "A+", 4.5);
        assertNotNull(gt);
        assertEquals("ID was assigned incorrectly", 1, gt.getId());
        assertEquals("Name was assigned incorrectly", "A+", gt.getName());
        assertEquals("Points was assigned incorrectly", 4.5, gt.getPoints());

        System.out.println("\nFinished GradeType Test: constructor");
    }
}
