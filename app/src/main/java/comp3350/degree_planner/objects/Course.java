package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class Course {
    private String name;
    private String creditHours;
    private CourseType type;

    public Course(String name, String creditHours, CourseType type) {
        this.name = name;
        this.creditHours = creditHours;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public CourseType getType() {
        return type;
    }
}
