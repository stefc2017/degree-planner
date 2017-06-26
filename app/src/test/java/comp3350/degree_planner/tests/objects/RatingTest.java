package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.Rating;
import comp3350.degree_planner.objects.RatingType;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Student;

/**
 * This class is used to test all of the behaviours of a
 * Rating object.
 */

public class RatingTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a Rating.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting Rating Test: constructor");

        Rating r = new Rating(1, new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1),
                new ScienceCourse(2, "Introductory Computer Science II", 3.0, 1, 1020, "More basic programming concepts."),
                new RatingType(4, "Very Good", 4), "Test Comment");
        assertNotNull(r);
        assertEquals("ID was assigned incorrectly", 1, r.getId());
        assertEquals("Student ID was assigned incorrectly", 1, r.getStudent().getId());
        assertEquals("Course ID was assigned incorrectly", 2, r.getCourse().getId());
        assertEquals("Rating Type ID was assigned incorrectly", 4, r.getRatingType().getId());
        assertEquals("Comment was assigned incorrectly", "Test Comment", r.getComment());

        System.out.println("Finished Rating Test: constructor");
    }
}
