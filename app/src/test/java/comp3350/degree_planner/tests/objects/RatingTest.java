package comp3350.degree_planner.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.degree_planner.objects.Rating;

/**
 * This class is used to test all of the behaviours of a
 * Rating object.
 */

public class RatingTest extends TestCase {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a Rating.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting Rating Test: constructor");

        Rating r = new Rating(1, 123, 35, 4, "Test Comment");
        assertNotNull(r);
        assertEquals("ID was assigned incorrectly", 1, r.getId());
        assertEquals("Student ID was assigned incorrectly", 123, r.getStudentId());
        assertEquals("Course ID was assigned incorrectly", 35, r.getCourseId());
        assertEquals("Rating Type ID was assigned incorrectly", 4, r.getRatingTypeId());
        assertEquals("Comment was assigned incorrectly", "Test Comment", r.getComment());

        System.out.println("\nFinished Rating Test: constructor");
    }
}
