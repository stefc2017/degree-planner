package comp3350.degree_planner.application;

/**
 * Created by Kaleigh on 2017-06-01.
 */

public class Main {

    public static void main(String[] args)
    {
        Services.createDataAccess();
        Services.closeDataAccess();

        System.out.println("All done");
    }
}
