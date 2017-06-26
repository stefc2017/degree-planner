package comp3350.degree_planner.tests.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.CoursePlan;
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
 * Created by Tiffany Jiang on 2017-06-05.
 *
 * Unit tests for adding a course plan
 */

public class AddToCoursePlanTest {
    private AccessCoursePlan acp;
    private DataAccess testData;

    @Before
    public void setUp() {
        //Setting up test data for the add
        //Mostly copied over from DataAccessStub.java with a few changes to data
        testData = new DataAccessStub() {
            private ArrayList<Course> courses;
            private ArrayList<CourseOffering> courseOfferings;
            private ArrayList<CoursePlan> coursePlans;
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

                tempUserDefinedCourse = new UserDefinedCourse(3, "Cultural Anthropology", 3.0, "ANTH 1220");
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
                // Create Students

                students = new ArrayList<Student>();
                students.add(new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1));

                // Create Course Results

                courseResults = new ArrayList<CourseResult>();
                courseResults.add(new CourseResult(1, new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                        "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(1, "A+", 4.5)));

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

                // Create Course Plans

                coursePlans = new ArrayList<CoursePlan>();
            }

            @Override
            public int addToCoursePlan (int courseId, int studentId, int termTypeId, int year) {
                int id = -1;
                CoursePlan newCoursePlan;

                if (isValidStudentId(studentId) && isValidCourseId(courseId) && isValidTermTypeId(termTypeId)
                        && courseOffered(courseId, termTypeId)) {
                    id = getMaxCoursePlanId()+1;
                    newCoursePlan = new CoursePlan(id, getCourseById(courseId), getStudentById(studentId), getTermTypeById(termTypeId), year);
                    coursePlans.add(newCoursePlan);
                }

                return id;
            }

            private Student getStudentById (int studentId){
                Student student = null;

                for (Student s : students) {
                    if (s.getId() == studentId) {
                        student = s;
                        break;
                    }
                }
                return student;
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

            private int getMaxCoursePlanId() {
                int max = 1;

                for (int i = 0; i<coursePlans.size(); i++) {
                    if (coursePlans.get(i).getId() > max) {
                        max = coursePlans.get(i).getId();
                    }
                }

                return max;
            }

            private boolean isValidStudentId (int studentId) {
                boolean validStudentId = false;

                //Does a student with the entered studentId exist?
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId() == studentId) {
                        validStudentId = true;
                        break;
                    }
                }

                return validStudentId;
            }

            private boolean isValidCourseId (int courseId) {
                boolean validCourseId = false;

                //Does a course with the entered courseId exist?
                for (int i = 0; i<courses.size(); i++) {
                    if (courses.get(i).getId() == courseId) {
                        validCourseId = true;
                        break;
                    }
                }

                return validCourseId;
            }

            private boolean isValidTermTypeId (int termTypeId) {
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

            private boolean courseOffered (int courseId, int termTypeId) {
                boolean validTerm = false;
                Course course = getCourseById(courseId);

                if (course instanceof ScienceCourse) {
                    //Is the course historically offered in this term?
                    for (int i = 0; i < courseOfferings.size(); i++) {
                        if (courseOfferings.get(i).getCourse().getId() == courseId && courseOfferings.get(i).getTermType().getId() == termTypeId) {
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
            public CoursePlan getCoursePlanById (int coursePlanId) {
                CoursePlan result = null;

                for (int i = 0; i<coursePlans.size(); i++) {
                    if (coursePlans.get(i).getId() == coursePlanId) {
                        result = coursePlans.get(i);
                    }
                }

                return result;
            }
        };

        acp = new AccessCoursePlan(testData);
        testData.open();
    }

    @Test
    public void testInvalidCourseId() {
        System.out.println("\nStarting Add to Course Plan Test: invalid course id");
        assertEquals ("Adding course plan with an invalid course ID did not fail", acp.addToCoursePlan(-1, 1, 1, 2018), -1);
        System.out.println("Finished Add to Course Plan Test: invalid course id");
    }

    @Test
    public void testInvalidStudentId() {
        System.out.println("\nStarting Add to Course Plan Test: invalid student id");
        assertEquals ("Adding course plan for invalid student ID did not fail", acp.addToCoursePlan(2, -1, 1, 2018), -1);
        System.out.println("Finished Add to Course Plan Test: invalid student id");
    }

    @Test
    public void testInvalidTermTypeId() {
        System.out.println("\nStarting Add to Course Plan Test: invalid term type id");
        assertEquals ("Adding course plan with an invalid term type ID did not fail", acp.addToCoursePlan(2, 1, -1, 2018), -1);
        System.out.println("Finished Add to Course Plan Test: invalid term type id");
    }

    @Test
    public void testNonExistentCourseId() {
        System.out.println("\nStarting Add to Course Plan Test: non-existent course id");
        assertEquals ("Adding course plan with a non-existent course ID did not fail", acp.addToCoursePlan(5, 1, 1, 2018), -1);
        System.out.println("Finished Add to Course Plan Test: non-existent course id");
    }

    @Test
    public void testNonExistentStudentId() {
        System.out.println("\nStarting Add to Course Plan Test: non-existent student id");
        assertEquals ("Adding course plan for a non-existent student ID did not fail", acp.addToCoursePlan(2, 5, 1, 2018), -1);
        System.out.println("Finished Add to Course Plan Test: non-existent student id");
    }

    @Test
    public void testNonExistentTermTypeId() {
        System.out.println("\nStarting Add to Course Plan Test: non-existent term type id");
        assertEquals ("Adding course plan with a non-existent term type ID did not fail", acp.addToCoursePlan(2, 1, 5, 2018), -1);
        System.out.println("Finished Add to Course Plan Test: non-existent term type id");
    }

    @Test
    public void testWrongTerm() {
        System.out.println("\nStarting Add to Course Plan Test: course not offered in term");
        assertEquals ("Adding course plan with a term the course is not offered in did not fail", acp.addToCoursePlan(2, 1, 3, 2018), -1);
        System.out.println("Finished Add to Course Plan Test: course not offered in term");
    }

    @Test
    public void testValidData() {
        int idAdded;
        CoursePlan added;

        final int SCIENCE_COURSE_ID = 2;
        final int STUDENT_ID = 1;
        final int TERM_TYPE_ID = 1;
        final int YEAR = 2018;
        final int USER_DEFINED_COURSE_ID = 3;
        final int USER_DEFINED_TERM_TYPE_ID = 2;

        System.out.println("\nStarting Add to Course Plan Test: valid data");

        //Testing science course
        idAdded = acp.addToCoursePlan(SCIENCE_COURSE_ID, STUDENT_ID, TERM_TYPE_ID, YEAR);
        assertNotEquals ("Adding course plan with valid data failed", idAdded, -1);

        added = testData.getCoursePlanById(idAdded);
        assertNotNull("Course plan was not added", added);
        assertEquals ("Course IDs weren't equal", added.getCourse().getId(), SCIENCE_COURSE_ID);
        assertEquals ("Student IDs weren't equal", added.getStudent().getId(), STUDENT_ID);
        assertEquals ("Term Type IDs weren't equal", added.getTermType().getId(), TERM_TYPE_ID);
        assertEquals ("Years weren't equal", added.getYear(), YEAR);

        //Testing user-defined course
        idAdded = acp.addToCoursePlan(USER_DEFINED_COURSE_ID, STUDENT_ID, USER_DEFINED_TERM_TYPE_ID, YEAR);
        assertNotEquals ("Adding course plan with valid data failed", idAdded, -1);

        added = testData.getCoursePlanById(idAdded);
        assertNotNull("Course plan was not added", added);
        assertEquals ("Course IDs weren't equal", added.getCourse().getId(), USER_DEFINED_COURSE_ID);
        assertEquals ("Student IDs weren't equal", added.getStudent().getId(), STUDENT_ID);
        assertEquals ("Term Type IDs weren't equal", added.getTermType().getId(), USER_DEFINED_TERM_TYPE_ID);
        assertEquals ("Years weren't equal", added.getYear(), YEAR);

        System.out.println("Finished Add to Course Plan Test: valid data");
    }
}
