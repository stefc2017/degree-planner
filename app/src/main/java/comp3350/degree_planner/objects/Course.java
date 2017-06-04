package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public abstract class Course {
    private int id;
    private String name;            // e.g. Introductory Computer Science I
    private double creditHours;

    public Course(int id, String name, double creditHours) {
        this.id = id;
        this.name = name;
        this.creditHours = creditHours;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public double getCreditHours() {
        return creditHours;
    }
}
