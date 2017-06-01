package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class RatingType {
    private String typeName;
    private int value;

    public RatingType(String typeName, int value) {
        this.typeName = typeName;
        this.value = value;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getValue() {
        return value;
    }
}
