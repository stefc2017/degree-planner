package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.RatingType;

/**
 * This class is used to test all of the behaviours of a
 * RatingType object.
 */

public class RatingTypeTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a RatingType.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting RatingType Test: constructor");

        RatingType rt = new RatingType(12, "Test Rating Type", 3);
        assertNotNull(rt);
        assertEquals("ID was assigned incorrectly", 12, rt.getId());
        assertEquals("Name was assigned incorrectly", "Test Rating Type", rt.getName());
        assertEquals("Value was assigned incorrectly", 3, rt.getValue());

        System.out.println("Finished RatingType Test: constructor");
    }
}
