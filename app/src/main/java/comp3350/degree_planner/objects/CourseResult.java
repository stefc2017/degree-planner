package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 * Modified by Tiffany on 2017-06-04.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A CourseResult is a simple object that holds a grade for a course completed as
 * well as the student who completed it, as IDs.
 */

public class CourseResult {
    private int id;         // Id of the object, since a student may take a course more than once
    private Course course;
    private Student student;
    private GradeType gradeType;

    public CourseResult(int id, Course course, Student student, GradeType gradeType) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.gradeType = gradeType;
    }

    public int getId() { return id; }

    public Course getCourse(){
        return course;
    }

    public Student getStudent(){
        return student;
    }

    public GradeType getGrade() { return gradeType; }
}
