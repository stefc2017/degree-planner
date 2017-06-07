package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A Rating Type is used to give a numeric value to a rating.
 * A user might give a rating "Very Good", which is stored numerically
 * as 4, for example.
 */

public class RatingType {
    private int id;
    private String name;
    private int value;

    public RatingType(int id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
