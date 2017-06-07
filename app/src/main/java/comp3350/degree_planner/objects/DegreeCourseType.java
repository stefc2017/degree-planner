package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A DegreeCourseType shows if a course is required for a degree, or
 * if it is an "elective for major". A course is considered an
 * "elective for major" if a student must take a certain number of
 * credit hours of courses in a department, but the course's
 * name is not specified.
 */

public class DegreeCourseType {
    private int id;
    private String name;

    public DegreeCourseType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
