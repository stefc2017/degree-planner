package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 * Modified by Kaleigh on 2017-06-06.
 *
 * A ScienceCourse is a course offered by the faculty of Science
 * at the University of Manitoba. It contains additional information,
 * such as the department it is associated with and a course number.
 * It also contains a brief description of the course for users.
 */

public class ScienceCourse extends Course {
    private int courseNumber;       // e.g. 3350, uniquely identifies a course within a department
    private String description;
    private int departmentId;

    public ScienceCourse(int id, String name, double creditHours, int departmentId,
                         int courseNumber, String description) {
        super(id, name, creditHours);
        this.departmentId = departmentId;
        this.courseNumber = courseNumber;
        this.description = description;
    }

    public int getCourseNumber() { return courseNumber; }

    public String getDescription() {
        return description;
    }

    public int getDepartmentId() { return departmentId; }
}
