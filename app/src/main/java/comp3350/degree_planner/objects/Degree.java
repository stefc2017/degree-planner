package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class Degree {
    private int id;
    private String name;
    private double creditHours;
    private double majorCreditHours;
    private double gpaRequired;

    public Degree(int id, String name, double creditHours, double majorCreditHours, double gpaRequired) {
        this.id = id;
        this.name = name;
        this.creditHours = creditHours;
        this.majorCreditHours = majorCreditHours;
        this.gpaRequired = gpaRequired;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public double getCreditHours() {
        return creditHours;
    }

    public double getMajorCreditHours() { return majorCreditHours; }

    public double getGpaRequired() {
        return gpaRequired;
    }
}
