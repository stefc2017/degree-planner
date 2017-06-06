package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class GradeType {
    private int id;
    private String name;        // B, C+, A, F, for example
    private double points;      // Used when determining GPA; how much a grade is worth

    public GradeType(int id, String name, double points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public double getPoints() { return points; }
}
