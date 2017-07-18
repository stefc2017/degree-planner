package comp3350.degree_planner.tests.business;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.business.AccessCourses;
import comp3350.degree_planner.objects.ScienceCourse;
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
    public void setUp() throws Exception {
        final DataAccess testData = new DataAccessStub() {
            private List<CourseOffering> courseOfferings;

            @Override
            public void open(String dbName) {
                // Create Course Offerings

                courseOfferings = new ArrayList<CourseOffering>();
                courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, null, 1010, "Basic programming concepts."), new TermType(1, "Fall")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, null, 1010, "Basic programming concepts."), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, null, 1010, "Basic programming concepts."), new TermType(3, "Summer")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        null, 1020, "More basic programming concepts."), new TermType(1, "Fall")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        null, 1020, "More basic programming concepts."), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        null, 1020, "More basic programming concepts."), new TermType(3, "Summer")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(3, "Object Orientation", 3.0, null,
                        2150, "Detailed look at proper object oriented programming."), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(4, "Software Engineering I", 3.0, null,
                        3350, "Good software development practices."), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(4, "Software Engineering I", 3.0, null,
                        3350, "Good software development practices."), new TermType(3, "Summer")));
            }

            @Override
            public List<CourseOffering> getAllCourseOfferings() {
                return courseOfferings;
            }

            @Override
            public ArrayList<CourseOffering> getCourseOfferingsByTerm(TermType term) {
                ArrayList<CourseOffering> courseOfferingsByTermList = new ArrayList<CourseOffering>();
                if (term != null) {
                    for (int i = 0; i < courseOfferings.size(); i++) {
                        try {
                            if (term.getId() == 1 || term.getId() == 2 || term.getId() == 3) {
                                if (term.getId() == courseOfferings.get(i).getTermType().getId()) {
                                    //Adds course offering based on courseID from CourseOfferings and matching TermID
                                    courseOfferingsByTermList.add(courseOfferings.get(i));
                                }
                            }
                        } catch (IllegalArgumentException e) {
                        }
                    }
                }
                return courseOfferingsByTermList;
            }
        };

        courseOfferings = new AccessCourses(testData);
        testData.open(Main.dbName);
    }// end setUp

    @Test
    public void testGetAllCourseOfferings() throws Exception {
        System.out.println("\nStarting Get All Course Offerings: Get all available course offerings");
        assertNotNull("Course Offerings list should not be null", courseOfferings.getAllCourseOfferings());
        assertEquals("Course Offerings list should have size nine", 9, courseOfferings.getAllCourseOfferings().size());
        System.out.println("Finished Get Course Offerings Test: Get all available course offerings");
    }

    @Test
    public void testGetCourseOfferingsByInvalidTerm() throws Exception {
        System.out.println("\nStarting Get Course Offerings Test: Get Course Offerings by invalid Term vals");
        assertEquals("Returned CourseOfferingsByTerm list should be empty when TermId is invalid", 0, courseOfferings.getCourseOfferingsByTerm(new TermType(4,"ImaginativeTerm")).size());
        assertEquals("Returned CourseOfferingsByTerm list should be empty when Term is null", 0, courseOfferings.getCourseOfferingsByTerm(null).size());
        System.out.println("Finished Get Degrees Test: Get Course Offerings by invalid Term vals");

    }

    @Test
    public void testGetCourseOfferingsByValidTerm() throws Exception {
        System.out.println("\nStarting Get Course Offerings Test: Get Course Offerings by valid Term");
        assertNotNull("Returned Course Offerings list should not be null", courseOfferings.getCourseOfferingsByTerm(new TermType(1,"Fall")));//A little bit of a hack, has to take from dataStub in the future
        System.out.println("Finished Get Course Offerings Test: Get Course Offerings by valid Term");
    }
}
