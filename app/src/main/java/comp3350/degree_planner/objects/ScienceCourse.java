package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class ScienceCourse extends Course {
    private int courseNumber;
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
}
