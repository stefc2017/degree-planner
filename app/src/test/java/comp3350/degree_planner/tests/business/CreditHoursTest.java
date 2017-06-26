package comp3350.degree_planner.tests.business;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import comp3350.degree_planner.business.CreditHours;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.DegreeCourse;
import comp3350.degree_planner.objects.DegreeCourseType;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.GradeType;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.DataAccessStub;

import static org.junit.Assert.assertEquals;

/**
 * Created by Matt on 6/7/2017.
 */

public class CreditHoursTest {
    private CreditHours creditHours;

    //Sets up test data with the entries we need for all tests below
    @Before
    public void setUp() {

        /*
         * Most of the stuff here is copied from DataAccessStub.java, see there for comments etc.
         * Except changed some of the data stored
         * Just here so we can have a separate data source for testing
         * Overriding (copied) only the methods needed for the testing
        */
        final DataAccess testData = new DataAccessStub() {
            private ArrayList<Course> courses;
            private ArrayList<CourseResult> courseResults;
            private ArrayList<DegreeCourseType> degreeCourseTypes;
            private ArrayList<Degree> degrees;
            private ArrayList<DegreeCourse> degreeCourses;
            private ArrayList<Department> departments;
            private ArrayList<GradeType> gradeTypes;
            private ArrayList<ScienceCourse> scienceCourses;
            private ArrayList<Student> students;
            private ArrayList<TermType> termTypes;
            private ArrayList<UserDefinedCourse> userDefinedCourses;

            @Override
            public void open() {
                ScienceCourse tempScienceCourse;
                UserDefinedCourse tempUserDefinedCourse;

                degreeCourseTypes = new ArrayList<DegreeCourseType>();
                degreeCourseTypes.add(new DegreeCourseType(1, "Required"));
                degreeCourseTypes.add(new DegreeCourseType(2, "Elective for Major"));

                termTypes = new ArrayList<TermType>();
                termTypes.add(new TermType(1, "Fall"));
                termTypes.add(new TermType(2, "Winter"));
                termTypes.add(new TermType(3, "Summer"));

                gradeTypes = new ArrayList<GradeType>();
                gradeTypes.add(new GradeType(1, "A+", 4.5));
                gradeTypes.add(new GradeType(2, "A", 4.0));
                gradeTypes.add(new GradeType(3, "B+", 3.5));
                gradeTypes.add(new GradeType(4, "B", 3.0));
                gradeTypes.add(new GradeType(5, "C+", 2.5));
                gradeTypes.add(new GradeType(6, "C", 2.0));
                gradeTypes.add(new GradeType(7, "D", 1.0));
                gradeTypes.add(new GradeType(8, "F", 0.0));

                // Create Departments

                departments = new ArrayList<Department>();
                departments.add(new Department(1, "Computer Science", "COMP"));

                // Create Courses

                courses = new ArrayList<Course>();
                scienceCourses = new ArrayList<ScienceCourse>();
                userDefinedCourses = new ArrayList<UserDefinedCourse>();

                tempScienceCourse = new ScienceCourse(1, "Introductory Computer Science I", 3.0, 1,
                        1010, "Basic programming concepts.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempScienceCourse = new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempUserDefinedCourse = new UserDefinedCourse(3, "Cultural Anthropology", 3.0, "ANTH 1220");
                courses.add(tempUserDefinedCourse);
                userDefinedCourses.add(tempUserDefinedCourse);

                // Create Degrees

                degrees = new ArrayList<Degree>();
                Degree degree = new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0);
                degrees.add(new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0));

                // Map courses to degrees

                degreeCourses = new ArrayList<DegreeCourse>();
                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new DegreeCourseType(1, "Required")));
                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new DegreeCourseType(1, "Required")));
                // Create Students

                students = new ArrayList<Student>();
                students.add(new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1));
                students.add(new Student(2, 9999999, "DK", "dk@myumanitoba.ca", "password1", 1));

                // Create Course Results

                courseResults = new ArrayList<CourseResult>();

                //Student 1 required degree course passed
                courseResults.add(new CourseResult(1, new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                        "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(1, "A+", 4.5)));

                //Student 1 required degree course passed
                courseResults.add(new CourseResult(2, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                        "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(1, "A+", 4.5)));

                //Student 1 user-defined course passed
                courseResults.add(new CourseResult(4, new UserDefinedCourse(3, "Cultural Anthropology", 3.0, "ANTH 1220"),
                        new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(3, "B+", 3.5)));
            }

            @Override
            public ArrayList<CourseResult> getCourseResultsByStudentId (int studentId) {
                ArrayList<CourseResult> crByStudentId = new ArrayList<CourseResult>();
                Iterator<CourseResult> crIterator = courseResults.iterator();
                CourseResult currCR;

                while (crIterator.hasNext()) {
                    currCR = crIterator.next();

                    if (currCR.getStudent().getId() == studentId) {
                        crByStudentId.add (currCR);
                    }
                }

                return crByStudentId;
            }

            @Override
            public ArrayList<Course> getCoursesTaken(int studentId) {
                ArrayList<Course> coursesTaken = new ArrayList<Course>();
                ArrayList<CourseResult> crByStudentId = getCourseResultsByStudentId(studentId);

                for (CourseResult result : crByStudentId) {
                    coursesTaken.add(findCourse(result.getCourse().getId()));
                }

                return coursesTaken;
            }

            @Override
            public ArrayList<Course> getDegreeCourses( int degreeId ) {
                final int REQUIRED_COURSE = 1;
                ArrayList<Course> reqCourseList = new ArrayList<Course>();

                for( DegreeCourse course : degreeCourses ){
                    if( course.getDegree().getId() == degreeId && course.getDegreeCourseType().getId() == REQUIRED_COURSE){
                        reqCourseList.add( findCourse( course.getCourse().getId() ) );
                    }
                }

                return reqCourseList;
            }

            @Override
            public ArrayList<Course> getDegreeCoursesTaken(int studentId, int degreeId) {
                ArrayList<Course> coursesTaken = getCoursesTaken(studentId);
                ArrayList<Course> degreeCourses = getDegreeCourses(degreeId);
                ArrayList<Course> takenDegreeCourses = new ArrayList<Course>();

                for (Course degreeCourse : degreeCourses) {
                    if (coursesTaken.contains(degreeCourse)) {
                        takenDegreeCourses.add(degreeCourse);
                    }
                }

                return takenDegreeCourses;
            }

            @Override
            public Course findCourse(int courseId) {
                int numberOfCourses = courses.size(); //the number of all courses
                Course course = null; //the course we will return
                int index = 0; //index for searching

                while (index < numberOfCourses && (courses.get(index)).getId() != courseId) {
                    index++;
                }//end while

                if (index < numberOfCourses && (courses.get(index)).getId() == courseId) { //we found the course
                    course = courses.get(index);
                }//end if

                return course;
            }//end findCourse

        };

        testData.open();
        creditHours = new CreditHours( testData );
    }

    @Test
    public void testInvalidStudentId() {
        System.out.println("\nStarting Credit Hours Test: invalid student id");

        int result = creditHours.getCreditHoursTaken(-1);
        assertEquals( 0 , result);

        System.out.println("Finished Credit Hours Test: invalid student id");
    }

    @Test
    public void testInvalidDegreeId() {
        System.out.println("\nStarting Credit Hours Test: invalid degree id");

        int result = creditHours.getRequiredCreditHoursTaken( 1, -1 );
        assertEquals( 0 , result);

        System.out.println("Finished Credit Hours Test: invalid degree id");
    }

    @Test
    public void testEmptyCourseResults() {
        System.out.println("\nStarting Credit Hours Test: valid student id and empty course list");

        int result = creditHours.getCreditHoursTaken(2);
        assertEquals( 0 , result);

        System.out.println("Finished Credit Hours Test: valid student id and empty course list");
    }

    @Test
    public void testValidData() {
        System.out.println("\nStarted Credit Hours Test: valid student id and has taken courses");
        final int NB_NONREQ_CREDITHOURS = 9;
        final int NB_REQ_CREDITHOURS = 6;

        int result = creditHours.getCreditHoursTaken( 1 );

        assertEquals( "Credit hours taken calculation value was not expected", NB_NONREQ_CREDITHOURS, result );

        result = creditHours.getRequiredCreditHoursTaken( 1, 1 );

        assertEquals( "Required Credit hours taken calculation value was not expected", NB_REQ_CREDITHOURS, result );

        System.out.println("Finished Credit Hours Test: valid student id and has taken courses");
    }
}
