package comp3350.degree_planner.tests.objects;

import junit.framework.TestCase;
import org.junit.Test;

import comp3350.degree_planner.objects.ScienceCourse;

/**
 * This class is used to test all of the behaviours of a
 * ScienceCourse object.
 */

public class ScienceCourseTest extends TestCase {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a ScienceCourse. It checks both variables
     * in the ScienceCourse and those of its superclass, Course.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting ScienceCourse Test: constructor");

        ScienceCourse sc = new ScienceCourse(123, "Test Name", 3.0, 456, 1111,
                "This is a test course");
        assertNotNull(sc);
        assertEquals("ID was assigned incorrectly", 123, sc.getId());
        assertEquals("Name was assigned incorrectly", "Test Name", sc.getName());
        assertEquals("Credit Hours was assigned incorrectly", 3.0, sc.getCreditHours());
        assertEquals("Department ID was assigned incorrectly", 456, sc.getDepartmentId());
        assertEquals("Course Number was assigned incorrectly", 1111, sc.getCourseNumber());
        assertEquals("Description was assigned incorrectly",
                "This is a test course", sc.getDescription());

        System.out.println("\nFinished ScienceCourse Test: constructor");
    }
}
