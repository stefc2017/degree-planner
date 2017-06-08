package comp3350.degree_planner.tests.business;

//import junit.framework.TestCase;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.degree_planner.business.AccessCourses;
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
                        // Create Course Offerings

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

                    @Override
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
        assertNotNull("Course Offerings list should not be null", courseOfferings.getAllCourseOfferings());
        assertEquals("Course Offerings list should have size nine", 9, courseOfferings.getAllCourseOfferings().size());
        System.out.println("Finished Get Course Offerings Test: Get all available course offerings");
    }

    @Test
    public void testGetCourseOfferingsByInvalidTerm() {
        System.out.println("\nStarting Get Course Offerings Test: Get degree by invalid Term vals");
        assertNull("Returned CourseOfferingsByTerm list should be null when TermId is invalid", courseOfferings.getCourseOfferingsByTerm(new TermType(4,"ImaginativeTerm")));
        assertNull("Returned CourseOfferingsByTerm list should be null when Term is null", courseOfferings.getCourseOfferingsByTerm(null));

        System.out.println("Finished Get Degrees Test: Get degree by invalid Term vals");

    }

    @Test
    public void testGetDegreeByValidTerm(){
        System.out.println("\nStarting Get Course Offerings Test: Get degree by valid Term");
        assertNotNull("Returned Course Offerings list should not be null", courseOfferings.getCourseOfferingsByTerm(new TermType(1,"Fall")));//A little bit of a hack, has to take from dataStub in the future
        System.out.println("Finished Get Course Offerings Test: Get Course Offerings by valid Term");
    }
}
