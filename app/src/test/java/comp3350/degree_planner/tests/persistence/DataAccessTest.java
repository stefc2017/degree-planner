package comp3350.degree_planner.tests.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.GradeType;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.persistence.DataAccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by pennyhe on 2017-06-28.
 * Modified by Matthew Provencher on 2017-06-28
 * Modified by Tiffany Jiang on 2017-07-07
 */

public class DataAccessTest {
    private static String dbName = Main.dbName;
    private DataAccess dataAccess;

    public DataAccessTest() {
        dataAccess = new DataAccessStub(dbName);
    }

    public void setDataAccess (final DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Before
    public void setUp(){
        Services.closeDataAccess();

        System.out.println("\nStart testing Persistence interface");
        Services.createDataAccess(dataAccess);
    }

    @Test
    public void testGetCourse() {
        List<Course> courses = dataAccess.getAllCourses();
        List<Course> noCourses = new ArrayList<Course>();
        CourseResult courseResult = new CourseResult(1, new ScienceCourse(1, "Introductory Computer Science I",
                3.0, null, 1010, "Basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                "jimbob@myumanitoba.ca", "helloworld1", null), new GradeType(1, "A+", 4.5));
        Course result;

        System.out.println("Beginning getCourse tests");

        // Valid courses test
        result = dataAccess.getCourse( courseResult, courses );
        assertNotNull( result );;
        assertEquals( "Introductory Computer Science I", result.getName() );
        result = null;

        // Invalid courses test
        result = dataAccess.getCourse( courseResult, noCourses );
        assertNull( result );

        System.out.println("getCourse tests complete");
    }

    @Test
    public void testGetAllCourses() {
        final int NUM_COURSE = 6;

        List<Course> courses = dataAccess.getAllCourses();
        assertEquals(NUM_COURSE, courses.size());
    }

    @Test
    public void testGetAllDegrees() {
        final int NUM_DEGREES = 2;

        List<Degree> degrees = dataAccess.getAllDegrees();
        assertEquals(NUM_DEGREES, degrees.size());
    }

    @Test
    public void testGetAllPrerequisite() {
        Course course = new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                null, 1020, "More basic programming concepts.");

        // course has prerequisites
        List<Course> courses = dataAccess.getAllPrerequisites( course );
        assertNotNull( courses );
        course = courses.get( 0 );
        assertEquals( "Introductory Computer Science I", course.getName() );

        //course has no prerequisite
        course = new ScienceCourse(1, "Introductory Computer Science I", 3.0, null,
                1010, "Basic programming concepts.");
        courses = dataAccess.getAllPrerequisites( course );
        assertEquals( 0, courses.size() );
    }

    @Test
    public void testGetAllCourseOfferings() {
        final int NUM_OFFERED = 9;

        List<CourseOffering> courseOfferings = dataAccess.getAllCourseOfferings();
        assertEquals( NUM_OFFERED, courseOfferings.size() );
    }

    @Test
    public void testGetCoursesTaken() {
        final int COURSES_TAKEN = 2;
        final int STUD_NUM = 1;
        final int INVALID_STUD_NUM = -1;

        // test valid student id
        List<Course> coursesTaken = dataAccess.getCoursesTaken( STUD_NUM );
        assertEquals( COURSES_TAKEN, coursesTaken.size() );

        // test invalid student id
        coursesTaken = dataAccess.getCoursesTaken( INVALID_STUD_NUM );
        assertNotNull( coursesTaken );
    }

    @Test
    public void testGetCoursesNotTaken() {
        final int NOT_TAKEN = 4;
        final int STUD_NUM = 1;
        final int INVALID_STUD_NUM = -1;

        // test valid student id
        List<Course> coursesNotTaken = dataAccess.getCoursesNotTaken( STUD_NUM );
        assertEquals( NOT_TAKEN, coursesNotTaken.size() );

        // test invalid student id
        coursesNotTaken = dataAccess.getCoursesNotTaken( INVALID_STUD_NUM );
        assertNotNull( coursesNotTaken );
    }

    @Test
    public void testGetCourseCanTake() {
        final int CAN_TAKE = 3;
        final int STUD_NUM = 1;
        final int INVALID_STUD_NUM = -1;

        List<Course> coursesCanTake = dataAccess.getCoursesCanTake( STUD_NUM );
        assertEquals( CAN_TAKE, coursesCanTake.size() );

        coursesCanTake = dataAccess.getCoursesCanTake( INVALID_STUD_NUM );
        assertNotNull( coursesCanTake );
    }

    @Test
    public void testGetEligibleRequiredCourse() {
        final int CAN_TAKE = 1;
        final int STUD_NUM = 1;
        final int INVALID_STUD_NUM = -1;
        final int DEGREE_ID = 1;

        List<Course> eligibleCourses = dataAccess.getEligibleRequiredCourse( STUD_NUM, DEGREE_ID );
        assertEquals( CAN_TAKE, eligibleCourses.size() );

        eligibleCourses = dataAccess.getEligibleRequiredCourse( INVALID_STUD_NUM, DEGREE_ID );
        assertNotNull( eligibleCourses );
    }

    @Test
    public void testHasPrerequisite() {
        final int STUD_NUM = 1;
        final String COURSE_NAME = "Introductory Computer Science II";
        final String PREREQS_NEEDED_COURSE = "Software Engineering I";
        boolean result;

        // has prereq ( ret true )
        result = dataAccess.hasPrerequisites( STUD_NUM, COURSE_NAME );
        assertTrue( result );

        // does not have prereq ( ret false )
        result = dataAccess.hasPrerequisites( STUD_NUM, PREREQS_NEEDED_COURSE );
        assertFalse( result );
    }

    @Test
    public void testGetCourseByName() {
        final String COURSE_NAME = "Introductory Computer Science II";
        final String INVALID_COURSE = "I DO NOT EXIST";
        Course course;

        // valid course name
        course = dataAccess.getCourseByName( COURSE_NAME );
        assertEquals( COURSE_NAME, course.getName() );

        // invalid course name
        course = dataAccess.getCourseByName( INVALID_COURSE );
        assertNull( course );
    }

    @Test
    public void testGetDegreeByName() {
        final String DEGREE_NAME = "Computer Science Major";
        final String INVALID_DEGREE = "I DO NOT EXIST";
        Degree degree;

        // valid degree name
        degree = dataAccess.getDegreeByName( DEGREE_NAME );
        assertEquals( DEGREE_NAME, degree.getName() );

        // invalid degree name
        degree = dataAccess.getDegreeByName( INVALID_DEGREE );
        assertNull( degree );
    }

    @Test
    public void testGetCourseById() {
        final int COURSE_ID = 1;
        final int INVALID_ID = -1;
        final String COURSE_NAME = "Introductory Computer Science I";
        Course course;

        // valid course id
        course = dataAccess.getCourseById( COURSE_ID );
        assertEquals( COURSE_NAME, course.getName() );

        // invalid course id
        course = dataAccess.getCourseById( INVALID_ID );
        assertNull( course );
    }

    @Test
    public void testDegreeById() {
        final int DEGREE_ID = 1;
        final int INVALID_ID = -1;
        final String DEGREE_NAME = "Computer Science Major";
        Degree degree;

        // valid degree id
        degree = dataAccess.getDegreeById( DEGREE_ID );
        assertEquals( DEGREE_NAME, degree.getName() );

        // invalid degree id
        degree = dataAccess.getDegreeById( INVALID_ID );
        assertNull( degree );
    }

    @Test
    public void testGetDepartmentById() {
        final int DEPT_ID = 1;
        final int INVALID_ID = -1;
        final String DEPT_NAME = "Computer Science";
        Department dept;

        // valid department id
        dept = dataAccess.getDepartmentById( DEPT_ID );
        assertEquals( DEPT_NAME, dept.getName() );

        // invalid department id
        dept = dataAccess.getDepartmentById( INVALID_ID );
        assertNull( dept );
    }

    @Test
    public void testCourseResultsByStudentId() throws Exception {
        final int STUD_NUM = 1;
        final int INVALID_STUD_NUM = -1;
        final int NUM_RESULTS = 2;

        // valid student number
        List<CourseResult> courseResults = dataAccess.getCourseResultsByStudentId( STUD_NUM );
        assertEquals( NUM_RESULTS, courseResults.size() );

        //invalid student number
        courseResults = dataAccess.getCourseResultsByStudentId( INVALID_STUD_NUM );
        assertEquals( 0, courseResults.size() );
    }

    @Test
    public void testGetCoursePlanById() throws Exception {
        final int PLAN_ID = 1;
        final int INVALID_PLAN = -1;
        final String COURSE_NAME = "Object Orientation";

        // valid plan
        CoursePlan plan = dataAccess.getCoursePlan( PLAN_ID );
        assertEquals( COURSE_NAME, plan.getCourse().getName() );

        //invalid plan
        plan = dataAccess.getCoursePlan( INVALID_PLAN );
        assertNull( plan );
    }

    @Test
    public void testGetFailingGradeId() throws SQLException {
        // if gradetype was not found, -1 is returned
        int result = dataAccess.getFailingGradeId();
        assertNotEquals( -1, result );
    }

    @Test
    public void testGetTermTypeIdByName() throws Exception {
        int termId = dataAccess.getTermTypeIdByName("Winter");
        assertEquals("Winter id does not equal 1", 1, termId);
        termId = dataAccess.getTermTypeIdByName("Summer");
        assertEquals("Summer id does not equal 2", 2, termId);
        termId = dataAccess.getTermTypeIdByName("Fall");
        assertEquals("Fall id does not equal 3", 3, termId);
        // Test invalid term name
        termId = dataAccess.getTermTypeIdByName("InvalidName");
        assertEquals("Invalid term name does not return -1", -1, termId);
    }

    @Test
    public void testGetCourseOfferingsByTerm() {
        final int NUM_OFFERINGS = 2;
        TermType FALL_TERM = new TermType(1, "Fall");

        List<CourseOffering> courses = dataAccess.getCourseOfferingsByTerm( FALL_TERM );
        assertEquals( NUM_OFFERINGS, courses.size() );
    }

    @Test
    public void testGetDegreeCoursesTaken() {
        final int NUM_TAKEN = 2;
        final int STUD_NUM = 1;
        final int INVALID_STUD_NUM = -1;
        final int DEGREE_NUM = 1;

        // valid student
        List<Course> degreeCourses = dataAccess.getDegreeCoursesTaken( STUD_NUM, DEGREE_NUM );
        assertEquals( NUM_TAKEN, degreeCourses.size() );

        // invalid student
        degreeCourses = dataAccess.getDegreeCoursesTaken( INVALID_STUD_NUM, DEGREE_NUM );
        assertEquals( 0, degreeCourses.size() );

        // invalid degree
        degreeCourses = dataAccess.getDegreeCoursesTaken( STUD_NUM, INVALID_STUD_NUM );
        assertEquals( 0, degreeCourses.size() );
    }

    @Test
    public void testGetDegreeCourses() {
        final int NUM_DEGREE_COURSES = 4;
        final int DEGREE_ID = 1;
        final int INVALID_DEGREE = -1;

        // valid degree
        List<Course> degreeCourses = dataAccess.getDegreeCourses( DEGREE_ID );
        assertEquals( NUM_DEGREE_COURSES, degreeCourses.size() );

        //invalid degree
        degreeCourses = dataAccess.getDegreeCourses( INVALID_DEGREE );
        assertEquals( 0, degreeCourses.size() );
    }


    @Test
    public void testIsValidStudentId() throws Exception {
        final int STUD_NUM = 1;
        final int INVALID_STUD_NUM = -1;
        boolean result;

        result = dataAccess.isValidStudentId( STUD_NUM );
        assertTrue( result );

        result = dataAccess.isValidStudentId( INVALID_STUD_NUM );
        assertFalse( result );
    }

    @Test
    public void testIsValidCourseId() throws Exception {
        final int COURSE_NUM = 1;
        final int INVALID_COURSE_NUM = -1;
        boolean result;

        result = dataAccess.isValidCourseId( COURSE_NUM );
        assertTrue( result );

        result = dataAccess.isValidCourseId( INVALID_COURSE_NUM );
        assertFalse( result );
    }

    @Test
    public void testIsValidTermTypeId() throws Exception {
        int VALID_TERM = 1;
        int INVALID_TERM = -1;
        boolean result;

        result = dataAccess.isValidTermTypeId( VALID_TERM );
        assertTrue( result );

        result = dataAccess.isValidTermTypeId( INVALID_TERM );
        assertFalse( result );
    }

    @Test
    public void testCourseOffered() throws Exception {
        boolean result;
        final int COURSE_ID = 3;
        final int TERM_ID = 2;
        final int FAIL_TERM_ID = 3;

        result = dataAccess.courseOffered( COURSE_ID, TERM_ID );
        assertTrue( result );

        result = dataAccess.courseOffered( COURSE_ID, FAIL_TERM_ID );
        assertFalse( result );
    }

    @Test
    public void testCoursePlanExists() throws Exception {
        // int courseId, int studentId, int termTypeId, int year
        boolean result;

        result = dataAccess.coursePlanExists( 3, 1, 2, 2018 );
        assertTrue( result );

        result = dataAccess.coursePlanExists( 3, 1, 2, 2045 );
        assertFalse( result );
    }

    @Test
    public void testAddToCoursePlan() throws Exception {
        final int COURSE_ID = 1;
        final int TERM_ID = 2;
        final int YEAR = 2018;
        final int STUD_NUM = 1;
        CoursePlan result;

        dataAccess.addToCoursePlan( COURSE_ID, STUD_NUM, TERM_ID, YEAR );
        result = dataAccess.getCoursePlan( COURSE_ID, STUD_NUM, TERM_ID, YEAR );
        assertNotNull( result );

        //Restore data access to before this test
        dataAccess.removeFromCoursePlan(result.getId());
    }

    @Test
    public void testMoveCourse() throws Exception {
        CoursePlan coursePlan = dataAccess.getCoursePlan( 3, 1, 2, 2018 );
        final int COURSE_PLAN_ID = coursePlan.getId();

        dataAccess.moveCourse( COURSE_PLAN_ID, 1, 2017 );
        CoursePlan modifiedCoursePlan = dataAccess.getCoursePlan(COURSE_PLAN_ID);
        assertEquals( 2017, modifiedCoursePlan.getYear() );
        assertEquals( 1, modifiedCoursePlan.getTermType().getId() );

        //Restore data access to before this test
        dataAccess.moveCourse( COURSE_PLAN_ID, 2, 2018 );
    }

    @Test
    public void testRemoveFromCoursePlan() throws Exception {
        int coursePlanId;
        CoursePlan result;

        dataAccess.addToCoursePlan( 5, 1, 3, 2019 ); //So can restore data access to before this test
        coursePlanId = dataAccess.getCoursePlan( 5, 1, 3, 2019 ).getId();

        dataAccess.removeFromCoursePlan( coursePlanId );
        result = dataAccess.getCoursePlan( coursePlanId );
        assertNull( result );
    }

    @Test
    public void testGetCoursePlansByStudentId() throws Exception {
        final int NUM_COURSE_PLANS = 3;
        final int STUDENT_ID = 1;
        final int INVALID_STUDENT = -1;

        // valid student
        List<CoursePlan> coursePlans = dataAccess.getCoursePlansByStudentId( STUDENT_ID );
        assertEquals( NUM_COURSE_PLANS, coursePlans.size() );

        //invalid student
        coursePlans = dataAccess.getCoursePlansByStudentId( INVALID_STUDENT );
        assertEquals( 0, coursePlans.size() );
    }

    @After
    public void cleanUp(){
        Services.closeDataAccess();
        System.out.println("Persistence interface tests complete");
    }
}
