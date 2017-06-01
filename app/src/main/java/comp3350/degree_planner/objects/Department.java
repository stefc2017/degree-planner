package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-06-01.
 */

public class Department {
    private int id;
    private String name;
    private String abbreviation;

    public Department(int id, String name, String abbreviation) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getAbbreviation() { return abbreviation; }
}
