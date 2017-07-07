package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.GradeType;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Student;

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

        CourseResult cr = new CourseResult(1, new ScienceCourse(1, "Introductory Computer Science I",
                3.0, null, 1010, "Basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                "jimbob@myumanitoba.ca", "helloworld1", null), new GradeType(1, "A+", 4.5));
        assertNotNull(cr);
        assertEquals("ID was assigned incorrectly", 1, cr.getCourse().getId());
        assertEquals("Course ID was assigned incorrectly", 1, cr.getCourse().getId());
        assertEquals("Student ID was assigned incorrectly", 1, cr.getStudent().getId());
        assertEquals("Grade ID was assigned incorrectly", 1, cr.getGrade().getId());

        System.out.println("Finished CourseOffering Test: constructor");
    }
}
