package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class RatingType {
    private int id;
    private String typeName;
    private int value;

    public RatingType(int id, String typeName, int value) {
        this.id = id;
        this.typeName = typeName;
        this.value = value;
    }

    public int getId() { return id; }

    public String getTypeName() {
        return typeName;
    }

    public int getValue() {
        return value;
    }
}
