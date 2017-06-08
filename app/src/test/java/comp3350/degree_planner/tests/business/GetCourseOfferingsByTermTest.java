package comp3350.degree_planner.tests.business;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.business.AccessDegrees;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.DegreeCourse;
import comp3350.degree_planner.objects.DegreeCourseType;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.DataAccessStub;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.TermType;
/**
 * Created by F.D. on 07/06/17.
 */


public class GetCourseOfferingsByTermTest {
    private AccessCourses courseOfferings;

            // Sets up test data with the entries we need for all tests below
            @Before
            public void setUp() {
                final DataAccess testData = new DataAccessStub() {
                    private ArrayList<CourseOffering> courseOfferings;

                    @Override
                    public void open() {
                        // Create Degrees

                        courseOfferings = new ArrayList<CourseOffering>();
                        courseOfferings.add(new CourseOffering(1, 1));
                        courseOfferings.add(new CourseOffering(1, 2));
                        courseOfferings.add(new CourseOffering(1, 3));
                        courseOfferings.add(new CourseOffering(2, 1));
                        courseOfferings.add(new CourseOffering(2, 2));
                        courseOfferings.add(new CourseOffering(2, 3));
                        courseOfferings.add(new CourseOffering(3, 2));
                        courseOfferings.add(new CourseOffering(4, 2));
                        courseOfferings.add(new CourseOffering(4, 3));
                    }


                    public ArrayList<CourseOffering> getAllCourseOfferings() {
                        return courseOfferings;
                    }

                    @Override
                    public ArrayList<CourseOffering> getCourseOfferingsByTerm(TermType term) {

                        ArrayList<CourseOffering> courseOfferingsByTermList = new ArrayList<CourseOffering>();

                        for (int i = 0; i < courseOfferings.size(); i++) {
                            if (term.getId() == courseOfferings.get(i).getTermTypeId()) {
                                //Adds course offering based on courseID from COurseOfferings and matching TermID
                                courseOfferingsByTermList.add(courseOfferings.get(i));
                            }
                        }


                        return courseOfferingsByTermList;
                    }
                };

                courseOfferings = new AccessCourses(testData);
                testData.open();
            }// end setUp// end setUp

    @Test
    public void testGetAllCourseOfferings() {
        System.out.println("\nStarting Get All Course Offerings: Get all available course offerings");
        assertNotNull("Course Offerings list should not be null", courseOfferings.getAll);
        assertEquals("Degree list should have size one", 1, degrees.getAllDegrees().size());
        assertEquals("Wrong degree in degree list", "Computer Science Major", degrees.getAllDegrees().get(0).getName());
        System.out.println("Finished Get Degrees Test: Get all available degree programs");
    }

    @Test
    public void testGetDegreeByInvalidDegreeId() {
        System.out.println("\nStarting Get Degrees Test: Get degree by invalid degree Id");
        assertNull("Returned Degree should be null when degreeId is invalid", degrees.getDegreeById(-5));
        System.out.println("Finished Get Degrees Test: Get degree by invalid degree Id");

    }

    @Test
    public void testGetDegreeByValidDegreeId(){
        System.out.println("\nStarting Get Degrees Test: Get degree by valid degree Id");
        assertNotNull("Returned Degree should not be null", degrees.getDegreeById(1));
        assertEquals("Wrong degree returned", "Computer Science Major", degrees.getDegreeById(1).getName());
        System.out.println("Finished Get Degrees Test: Get degree by valid degree Id");
    }
}
