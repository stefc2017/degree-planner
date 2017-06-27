package comp3350.degree_planner.persistence;

import java.util.List;

import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.TermType;

/**
 * Created by Tiffany Jiang on 2017-06-06.
 *
 * Interface for data access
 */

public interface DataAccess {
    void open(String dbName);

    void close();

    List<Course> getCoursesNotTaken(int studentNumber);

    Course getCourse(CourseResult courseResult, List<Course> allCourses);
    //By F.D.
    List<Course> getAllCourses();
    //List<ScienceCourse> getAllCourses();

    List<Course> getCoursesCanTake(int studentNumber);

    boolean hasPrerequisites(int studentNumber, String courseName);

    List<Course> getAllPrerequisites(Course course);

    Course findCourse(int courseId);

    Course findCourse(String courseName);

    List<Degree> getAllDegrees();

    Degree getDegreeByName(String degreeName);

    Degree getDegreeById(int degreeId);

    List<CourseResult> getCourseResultsByStudentId(int studentId);

    List<CourseOffering> getAllCourseOfferings();

    int getFailingGradeId();

    Course getCourseById(int courseId);

    List<CourseOffering> getCourseOfferingsByTerm(TermType type);

    Department getDepartmentById(int departmentId);

    List<Course> getCoursesTaken(int studentId);

    List<Course> getDegreeCoursesTaken(int studentId, int degreeId);

    List<Course> getDegreeCourses(int degreeId);

    List<Course> getEligibleRequiredCourse(int studentNum, int degreeId);

    int addToCoursePlan (int courseId, int studentId, int termTypeId, int year);

    boolean moveCourse (int coursePlanId, int newTermTypeId, int newYear);

    boolean removeFromCoursePlan (int coursePlanId);

    CoursePlan getCoursePlanById (int coursePlanId);
}
