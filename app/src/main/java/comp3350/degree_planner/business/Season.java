package comp3350.degree_planner.business;

/**
 * Created by pennyhe on 2017-06-29.
 */

public enum Season {
    WINTER(1), SUMMER(2), FALL(3);

    private int id;

    Season(int id){ this.id = id; }

    public int getValue(){ return this.id; }
}
