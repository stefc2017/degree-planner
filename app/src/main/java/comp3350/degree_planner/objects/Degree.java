package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class Degree {
    private String name;
    private String creditHours;
    private ScienceCourse[] requiredCourses;
    private double gpaRequired;

    public Degree(String name, String creditHours, ScienceCourse[] requiredCourses, double gpaRequired) {
        this.name = name;
        this.creditHours = creditHours;
        this.requiredCourses = requiredCourses;
        this.gpaRequired = gpaRequired;
    }

    public String getName() {
        return name;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public ScienceCourse[] getRequiredCourses() {
        return requiredCourses;
    }

    public double getGpaRequired() {
        return gpaRequired;
    }
}
