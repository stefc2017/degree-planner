package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;
import org.junit.Test;

import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Department;

/**
 * This class is used to test all of the behaviours of a
 * ScienceCourse object.
 */

public class ScienceCourseTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a ScienceCourse. It checks both variables
     * in the ScienceCourse and those of its superclass, Course.
     */

    @Test
    public void testConstructor() {
        final double DELTA = 0;

        System.out.println("\nStarting ScienceCourse Test: constructor");

        ScienceCourse sc = new ScienceCourse(123, "Test Name", 3.0, new Department(456, "Test Dept", "DEPT"), 1111,
                "This is a test course");
        assertNotNull(sc);
        assertEquals("ID was assigned incorrectly", 123, sc.getId());
        assertEquals("Name was assigned incorrectly", "Test Name", sc.getName());
        assertEquals("Credit Hours was assigned incorrectly", 3.0, sc.getCreditHours(), DELTA);
        assertEquals("Department was assigned incorrectly", 456, sc.getDepartment().getId());
        assertEquals("Course Number was assigned incorrectly", 1111, sc.getCourseNumber());
        assertEquals("Description was assigned incorrectly",
                "This is a test course", sc.getDescription());

        System.out.println("Finished ScienceCourse Test: constructor");
    }
}
