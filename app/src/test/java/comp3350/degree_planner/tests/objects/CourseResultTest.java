package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.CourseResult;

/**
 * This class is used to test all of the behaviours of a
 * CourseResult object.
 */

public class CourseResultTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a CourseResult.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting CourseOffering Test: constructor");

        CourseResult cr = new CourseResult(1, 123, 321, 7);
        assertNotNull(cr);
        assertEquals("ID was assigned incorrectly", 1, cr.getId());
        assertEquals("Course ID was assigned incorrectly", 123, cr.getCourseId());
        assertEquals("Student ID was assigned incorrectly", 321, cr.getStudentId());
        assertEquals("Grade ID was assigned incorrectly", 7, cr.getGradeId());

        System.out.println("Finished CourseOffering Test: constructor");
    }
}
