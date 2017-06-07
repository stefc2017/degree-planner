package comp3350.degree_planner.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.degree_planner.objects.Department;

/**
 * This class is used to test all of the behaviours of a
 * Department object.
 */

public class DepartmentTest extends TestCase {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a Department.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting Department Test: constructor");

        Department d = new Department(1, "Test Department", "TEST");
        assertNotNull(d);
        assertEquals("ID was assigned incorrectly", 1, d.getId());
        assertEquals("Name was assigned incorrectly", "Test Department", d.getName());
        assertEquals("Abbreviation was assigned incorrectly", "TEST", d.getAbbreviation());

        System.out.println("\nFinished Department Test: constructor");
    }
}
