package comp3350.degree_planner.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.degree_planner.objects.TermType;

/**
 * This class is used to test all of the behaviours of a
 * TermType object.
 */

public class TermTypeTest extends TestCase {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a TermType.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting TermType Test: constructor");

        TermType tt = new TermType(1, "Fall");
        assertNotNull(tt);
        assertEquals("ID was assigned incorrectly", 1, tt.getId());
        assertEquals("Season was assigned incorrectly", "Fall", tt.getSeason());

        System.out.println("\nFinished TermType Test: constructor");
    }
}
