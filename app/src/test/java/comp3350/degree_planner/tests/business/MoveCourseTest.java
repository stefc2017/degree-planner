package comp3350.degree_planner.tests.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.business.exceptions.CourseAlreadyPlannedForTermException;
import comp3350.degree_planner.business.exceptions.CourseNotOfferedInTermException;
import comp3350.degree_planner.business.exceptions.CoursePlanDoesNotExistException;
import comp3350.degree_planner.business.exceptions.TermTypeDoesNotExistException;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.CoursePrerequisite;
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

/**
 * Created by Tiffany Jiang on 2017-06-07.
 *
 * Unit tests for moving a course in a course plan to a different term/year
 */

public class MoveCourseTest {
    private AccessCoursePlan acp;
    private DataAccess testData;

    @Before
    public void setUp() {
        //Setting up test data for the move
        //Mostly copied over from DataAccessStub.java
        testData = new DataAccessStub() {
            private List<Course> courses;
            private List<CourseOffering> courseOfferings;
            private List<CoursePlan> coursePlans;
            private List<CourseResult> courseResults;
            private List<DegreeCourseType> degreeCourseTypes;
            private List<Degree> degrees;
            private List<DegreeCourse> degreeCourses;
            private List<Department> departments;
            private List<GradeType> gradeTypes;
            private List<ScienceCourse> scienceCourses;
            private List<Student> students;
            private List<TermType> termTypes;
            private List<UserDefinedCourse> userDefinedCourses;
            private List<CoursePrerequisite> coursePrerequisites;

            private String dbName;
            private String dbType = "stub";

            @Override
            public void open(String dbName) {
                ScienceCourse tempScienceCourse;
                UserDefinedCourse tempUserDefinedCourse;

                // Create Types

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

                tempScienceCourse = new ScienceCourse(3, "Object Orientation", 3.0, 1,
                        2150, "Detailed look at proper object oriented programming.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempScienceCourse = new ScienceCourse(4, "Software Engineering I", 3.0, 1,
                        3350, "Good software development practices.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempUserDefinedCourse = new UserDefinedCourse(5, "Cultural Anthropology", 3.0, "ANTH 1220");
                courses.add(tempUserDefinedCourse);
                userDefinedCourses.add(tempUserDefinedCourse);

                tempUserDefinedCourse = new UserDefinedCourse(6, "Language and Culture", 3.0, "ANTH 2370");
                courses.add(tempUserDefinedCourse);
                userDefinedCourses.add(tempUserDefinedCourse);

                // Create Degrees

                degrees = new ArrayList<Degree>();
                Degree degree = new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0);
                degrees.add(degree);

                // Map courses to degrees

                degreeCourses = new ArrayList<DegreeCourse>();
                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new DegreeCourseType(1, "Required")));
                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new DegreeCourseType(1, "Required")));
                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(3, "Object Orientation", 3.0, 1,
                        2150, "Detailed look at proper object oriented programming."), new DegreeCourseType(1, "Required")));
                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(4, "Software Engineering I", 3.0, 1,
                        3350, "Good software development practices."), new DegreeCourseType(1, "Required")));

                // Create Students

                students = new ArrayList<Student>();
                students.add(new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1));

                // Create Course Results

                courseResults = new ArrayList<CourseResult>();
                courseResults.add(new CourseResult(1, new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                        "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(1, "A+", 4.5)));
                courseResults.add(new CourseResult(2, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                        "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(2, "A", 4.0)));

                // Create Course Offerings

                courseOfferings = new ArrayList<CourseOffering>();
                courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new TermType(1, "Fall")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new TermType(3, "Summer")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new TermType(1, "Fall")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new TermType(3, "Summer")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(3, "Object Orientation", 3.0, 1,
                        2150, "Detailed look at proper object oriented programming."), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(4, "Software Engineering I", 3.0, 1,
                        3350, "Good software development practices."), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(new ScienceCourse(4, "Software Engineering I", 3.0, 1,
                        3350, "Good software development practices."), new TermType(3, "Summer")));

                // Create Course Plans

                coursePlans = new ArrayList<CoursePlan>();
                coursePlans.add(new CoursePlan(1, new ScienceCourse(3, "Object Orientation", 3.0, 1, 2150,
                        "Detailed look at proper object oriented programming."), new Student(1,
                        1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1), new TermType(2, "Winter"), 2018));
                coursePlans.add(new CoursePlan(2, new ScienceCourse(1, "Introductory Computer Science I", 3.0, 1, 1010,
                        "Basic programming concepts."), new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1),
                        new TermType(1, "Fall"), 2017));
                coursePlans.add(new CoursePlan(3, new UserDefinedCourse(5, "Cultural Anthropology", 3.0, "ANTH 1220"),
                        new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1), new TermType(1, "Fall"), 2017));

                // Create Course Prerequisites

                coursePrerequisites = new ArrayList<CoursePrerequisite>();
                coursePrerequisites.add(new CoursePrerequisite(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts.")));
                coursePrerequisites.add(new CoursePrerequisite(new ScienceCourse(3, "Object Orientation", 3.0, 1,
                        2150, "Detailed look at proper object oriented programming."), new ScienceCourse(2,
                        "Introductory Computer Science II", 3.0, 1, 1020, "More basic programming concepts.")));
                coursePrerequisites.add(new CoursePrerequisite(new ScienceCourse(4, "Software Engineering I", 3.0, 1,
                        3350, "Good software development practices."), new ScienceCourse(3, "Object Orientation", 3.0, 1,
                        2150, "Detailed look at proper object oriented programming.")));
            }

            @Override
            public void moveCourse (int coursePlanId, int newTermTypeId, int newYear) {
                CoursePlan coursePlan;

                for (int i = 0; i<coursePlans.size(); i++) {
                    coursePlan = coursePlans.get(i);
                    if (coursePlan.getId() == coursePlanId) {
                        coursePlan.setTermType(getTermTypeById(newTermTypeId));
                        coursePlan.setYear(newYear);
                    }
                }
            }

            private TermType getTermTypeById (int termTypeId){
                TermType termType = null;

                for (TermType type : termTypes) {
                    if (type.getId() == termTypeId) {
                        termType = type;
                        break;
                    }
                }
                return termType;
            }

            @Override
            public Course getCourseById(int courseId) {
                Course result = null;

                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getId() == courseId) {
                        result = courses.get(i);
                        break;
                    }
                }

                return result;
            }

            @Override
            public boolean isValidTermTypeId (int termTypeId) {
                boolean validTermTypeId = false;

                //Does a term type with the entered termTypeId exist?
                for (int i = 0; i<termTypes.size(); i++) {
                    if (termTypes.get(i).getId() == termTypeId) {
                        validTermTypeId = true;
                        break;
                    }
                }

                return validTermTypeId;
            }

            @Override
            public boolean courseOffered (int courseId, int termTypeId) {
                boolean validTerm = false;
                Course course = getCourseById(courseId);

                if (course instanceof ScienceCourse) {
                    //Is the course historically offered in this term?
                    for (int i = 0; i < courseOfferings.size(); i++) {
                        if (courseOfferings.get(i).getCourse().getId() == courseId &&
                                courseOfferings.get(i).getTermType().getId() == termTypeId) {
                            validTerm = true;
                            break;
                        }
                    }
                } else if (course instanceof UserDefinedCourse) {
                    //For user-defined courses, let the user freely enter the the term and year
                    validTerm = true;
                }

                return validTerm;
            }

            @Override
            public boolean coursePlanExists(int courseId, int studentId, int termTypeId, int year) {
                boolean coursePlanExists = false;
                CoursePlan currCoursePlan;

                //Does a course plan for the specified course in the specified term already exist?
                for (int i = 0; i<coursePlans.size(); i++) {
                    currCoursePlan = coursePlans.get(i);

                    if (currCoursePlan.getCourse().getId() == courseId && currCoursePlan.getStudent().getId() == studentId
                            && currCoursePlan.getTermType().getId() == termTypeId && currCoursePlan.getYear() == year) {
                        coursePlanExists = true;
                        break;
                    }
                }

                return coursePlanExists;
            }

            @Override
            public CoursePlan getCoursePlan (int courseId, int studentId, int termTypeId, int year) {
                CoursePlan result = null;
                CoursePlan currCoursePlan;

                for (int i = 0; i<coursePlans.size(); i++) {
                    currCoursePlan = coursePlans.get(i);

                    if (currCoursePlan.getCourse().getId() == courseId && currCoursePlan.getStudent().getId() == studentId
                            && currCoursePlan.getTermType().getId() == termTypeId && currCoursePlan.getYear() == year) {
                        result = currCoursePlan;
                        break;
                    }
                }

                return result;
            }

            @Override
            public CoursePlan getCoursePlan (int coursePlanId) {
                CoursePlan result = null;
                CoursePlan currCoursePlan;

                for (int i = 0; i<coursePlans.size(); i++) {
                    currCoursePlan = coursePlans.get(i);

                    if (currCoursePlan.getId() == coursePlanId) {
                        result = currCoursePlan;
                        break;
                    }
                }

                return result;
            }
        };

        acp = new AccessCoursePlan(testData);
        testData.open(Main.dbName);
    }

    @Test(expected = CoursePlanDoesNotExistException.class)
    public void testInvalidCoursePlanId() throws Exception {
        System.out.println("\nStarting Move Course Test: invalid course plan id");
        acp.moveCourse(-1, 2, 2019);
        System.out.println("Finished Move Course Test: invalid course plan id");
    }

    @Test(expected = TermTypeDoesNotExistException.class)
    public void testInvalidTermTypeId() throws Exception {
        System.out.println("\nStarting Move Course Test: invalid term type id");
        acp.moveCourse(1, -1, 2019);
        System.out.println("Finished Move Course Test: invalid term type id");
    }

    @Test(expected = CoursePlanDoesNotExistException.class)
    public void testNonExistentCoursePlanId() throws Exception {
        System.out.println("\nStarting Move Course Test: non-existent course plan id");
        acp.moveCourse(5, 2, 2019);
        System.out.println("Finished Move Course Test: non-existent course plan id");
    }

    @Test(expected = TermTypeDoesNotExistException.class)
    public void testNonExistentTermTypeId() throws Exception {
        System.out.println("\nStarting Move Course Test: non-existent term type id");
        acp.moveCourse(1, 5, 2019);
        System.out.println("Finished Move Course Test: non-existent term type id");
    }

    @Test(expected = CourseNotOfferedInTermException.class)
    public void testWrongTerm() throws Exception {
        System.out.println("\nStarting Move Course Test: course not offered in term");
        acp.moveCourse(1, 3, 2019);
        System.out.println("Finished Move Course Test: course not offered in term");
    }

    @Test(expected = CourseAlreadyPlannedForTermException.class)
    public void testCourseAlreadyPlanned() throws Exception {
        System.out.println("\nStarting Move Course Test: course already planned in term");
        acp.moveCourse(1, 2, 2018);
        System.out.println("Finished Move Course Test: course already planned in term");
    }

    @Test
    public void testValidData() throws Exception {
        CoursePlan coursePlan;

        final int COURSE_PLAN_ID = 1;
        final int TERM_TYPE_ID = 2;
        final int YEAR = 2019;

        System.out.println("\nStarting Move Course Test: valid data");

        acp.moveCourse(COURSE_PLAN_ID, TERM_TYPE_ID, YEAR);

        coursePlan = testData.getCoursePlan(COURSE_PLAN_ID);
        assertNotNull("Error occurred with modification", coursePlan);
        assertEquals("Term Type IDs weren't equal", coursePlan.getTermType().getId(), TERM_TYPE_ID);
        assertEquals ("Years weren't equal", coursePlan.getYear(), YEAR);

        System.out.println("Finished Move Course Test: valid data");
    }
}
